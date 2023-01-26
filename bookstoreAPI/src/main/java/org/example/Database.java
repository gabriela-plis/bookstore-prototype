package org.example;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//dla tych co mogą być jako baza interfejs
public class Database<V extends DBObject> {

    private final TxtDatabase file;
    private final HashMap<Integer, V> database;
    private final DBObjectConverter<V> converter;
    private int ID;

    public Database(String path, DBObjectConverter<V> dbObjectConverter) {
        this.file = new TxtDatabase(path);
        this.database = new HashMap<>();
        this.converter = dbObjectConverter;
        this.ID=0;
    }

    public List<V> getAll() {
        ArrayList<String> textLines = file.read();
        List<V> items = new ArrayList<>();
        for(String line : textLines) {
            items.add(converter.parseFromFileLine(line));
        }
        return items;
    }

    public V get(int ID) {
        return database.get(ID);
    }

    public boolean isContains(int ID) {
        return database.containsKey(ID);
    }

    public void add (V object) {

        if (object == null) {
            throw new IllegalArgumentException("Operation failed - object is null");
        }

        database.put(ID, object);
        ID++;

    }

    public int getAssignID () {
        return ID;
    }

    public void delete (int ID) {
        database.remove(ID);
    }

    public void load() {
        ArrayList<String> textLines = file.read();

        for (String line : textLines) {
            V DBObject = converter.parseFromFileLine(line);

            database.put(DBObject.getID(), DBObject);
            ID = DBObject.getID() + 1;
        }
    }

    public void update () {
        ArrayList<String> convertDBToString = new ArrayList<>();

        for (V databaseObject : database.values()) {
            convertDBToString.add(converter.parseToFileLine(databaseObject));
        }

        file.update(convertDBToString);
    }


}
