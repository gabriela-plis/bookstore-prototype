package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TxtDatabase {

    private final Path path;

    public TxtDatabase(String path) {
        this.path = Path.of(path);

        if (!Files.isRegularFile(this.path)) {
            throw new IllegalArgumentException("Path not found");
        }

    }

    public ArrayList<String> read () {

        //co z return i jak zasymulowac problem z otwarciem pliku
        try {
            return Files.lines(path).collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            System.out.println("Error - cannot read file");
        }

        return null;
    }


    public void writeRecord(Readable record) {
        // przypadki - null/niepożądany typ klasy
        if ( record == null ) {
            return;
        }

        String text = record.read() + "\n";

        try (FileWriter writer = new FileWriter(path.toFile(), true)) {
            writer.append(text);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(ArrayList<String> databaseToString) {
        //nawiasy w try - zasoby, które chcę zamknąć (nie ma potrzeby pisania writer.close())
        try (FileWriter writer = new FileWriter(path.toFile())) {

            for (String line : databaseToString) {
                writer.write(line + "\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
