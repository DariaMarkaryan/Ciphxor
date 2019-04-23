package com.company;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.io.*;


class CiphxorLauncherTest {
    private String read (String file) {
        StringBuilder builder = new StringBuilder();
        try{
            FileInputStream fileo = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileo));
            String strLine;
            String sep = "";
            while ((strLine = br.readLine()) != null){
                builder.append(sep);
                sep = "\r\n";
                builder.append(strLine);
            }
        }catch (IOException e){
            System.out.println("Ошибка с чтением файла");
        }
        return builder.toString();
    }

    @Test
    void wihoutKeys()throws IOException{
        Ciphxor.recode("Text.txt","Youth.txt", "", "");
        assertEquals(read("Text.txt"), read("Youth.txt"));
    }

    @Test
    void withC() throws IOException {
        Ciphxor.recode("Text.txt","Youth.txt", "3F", "");
        assertEquals("5e5d5c5b5a59581f0e0d0c0b0a090807060f", read("Youth.txt"));
    }

    @Test
    void withDasC() throws IOException {
        Ciphxor.recode("Text.txt","Youth.txt", "3A1EF", "3A1EF");
        assertEquals(read("Text.txt"), read("Youth.txt"));
    }

    @Test
    void withDnotAsC() throws IOException {
        Ciphxor.recode("Text.txt","Youth.txt", "F3", "3F");
        assertNotEquals(read("Text.txt"), read("Youth.txt"));
    }

    @Test
    void withD() throws IOException {
        Ciphxor.recode("Text3.txt","Text3.txt.txt", "", "3F");
        assertEquals("abcdefg 1234567890", read("Test3.txt"));
    }
}
