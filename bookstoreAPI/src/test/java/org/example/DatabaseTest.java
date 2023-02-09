package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

//    String path;
//    Database<StuffTestClass> stuffsDatabase;
//
//    @BeforeEach
//    void initEach () {
//        path = getResourceFile("stuffs.txt");
//        stuffsDatabase = new Database<>(path, new StuffTestDBObjectConverter());
//    }
//
//    @Test
//    void load_stuffsTxtFile_getAllReturnsTwoElementArrayList() {
//        //given
//        List<StuffTestClass> expected = new ArrayList<>();
//        expected.add(new StuffTestClass(1, "Scissors", 2.5, 10));
//        expected.add(new StuffTestClass(2, "Book", 5, 15));
//
//        //when
//        stuffsDatabase.load();
//
//        //then
//        assertIterableEquals(expected,stuffsDatabase.getAll());
//    }
//
//    @Test
//    void add_NullParameter_ReturnsException() {
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
//                stuffsDatabase.add(null)
//        );
//
//        assertEquals("Operation failed - object is null", ex.getMessage());
//    }
//
//    private String getResourceFile(String file) {
//        return new File(getClass().getClassLoader().getResource(file).getFile()).getAbsolutePath();
//    }
}