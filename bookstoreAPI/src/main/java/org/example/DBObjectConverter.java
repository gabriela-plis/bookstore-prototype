package org.example;

public interface DBObjectConverter <T extends DBObject> {

    String parseToFileLine(T t);
    T parseFromFileLine(String line);
}
