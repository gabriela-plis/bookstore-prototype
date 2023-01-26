package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

//MethodName_StateUnderTest_ExpectedBehavior naming convention

class TxtDatabaseTest {

    @Test
    void constructor_emptyPathParameter_throwsException() {
        String path = "";

        RuntimeException ex = assertThrows(IllegalArgumentException.class, () ->
                new TxtDatabase(path)
        );

        assertEquals("Path not found", ex.getMessage());
    }

    @Test
    void constructor_notRealPathParameter_throwsException() {
        String path = "abc";

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new TxtDatabase(path)
        );

        assertEquals("Path not found", ex.getMessage());
    }

    @Test
    void read_stuffsInTxtFile_returnsTwoElementArrayList() {
        //given
        String path = getResourceFile("stuffs.txt");

        TxtDatabase newFile = new TxtDatabase(path);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("1,Scissors,2.50,10");
        expected.add("2,Book,5,15");


        //then
        assertIterableEquals(expected, newFile.read());
    }

    @Test
    void read_emptyTxtFile_returnsEmptyArrayList() {
        //given
        String path = getResourceFile("empty.txt");
        TxtDatabase newFile = new TxtDatabase(path);
        ArrayList<String> expected = new ArrayList<>();


        //then
        assertIterableEquals(expected, newFile.read());
    }

    @Test
    void write_emptyTxtFile_oneLineTxtFile () throws FileNotFoundException {
        //given

        StuffTestClass stuff = new StuffTestClass(1,"Book",3,10);

        String path = getResourceFile("writeTest.txt");
        TxtDatabase file = new TxtDatabase(path);

        //when
        file.writeRecord(stuff);

        File testFile = new File(path);

        Scanner reader = new Scanner(testFile);

        String line = "";
        while (reader.hasNextLine()) {
            line = reader.nextLine();
        }

        //then
        Assertions.assertEquals("1,Book,3.0,10", line);
    }

    @Test
    void testUpdateFile_moreThanOneObject_MultipleLinesInTxtFile() {
        //given
        ArrayList<String> expected = new ArrayList<>();
        expected.add("First line");
        expected.add("Second line");
        expected.add("Third line");

        String path = getResourceFile("fileToUpdate.txt");
        TxtDatabase file = new TxtDatabase(path);

        //when
        file.update(expected);

        ArrayList<String> textLines = new ArrayList<>();

        try {
            File fileToRead = new File(path);
            Scanner reader = new Scanner(fileToRead);

            while (reader.hasNextLine()) {
                textLines.add(reader.nextLine());
            }

            reader.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //then

        assertIterableEquals(expected,textLines);
    }

    private String getResourceFile(String file) {
        return new File(getClass().getClassLoader().getResource(file).getFile()).getAbsolutePath();
    }
}