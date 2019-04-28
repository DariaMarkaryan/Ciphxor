package com.company;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


class CiphxorLauncherTest {

    @Test
    void xorandxor()throws IOException{
        Ciphxor.recode("Text.txt","Youth.txt", "1", "");
        Ciphxor.recode("Youth.txt","Youth.txt.txt", "", "1");
        byte[] res = Files.readAllBytes(Paths.get("Test.txt"));
        byte[] exp = Files.readAllBytes(Paths.get("Youth.txt.txt"));
        assertEquals(new String(exp), new String(res));
    }

    @Test
    void withC() throws IOException {
        Ciphxor.recode("Text.txt","Test3.txt", "3F", "");
        byte[] res = Files.readAllBytes(Paths.get("Test3.txt"));
        assertEquals("^]\\\\[ZYX\\u001FЯЮЭ", new String(res));
    }

    @Test
    void bigKey() throws IOException {
        Ciphxor.recode("Text.txt","Test4.txt", "113FA1EE", "");
        Ciphxor.recode("Text4.txt","Test4.txt.txt", "", "113FA1EE");
        byte[] res = Files.readAllBytes(Paths.get("Test.txt"));
        byte[] exp = Files.readAllBytes(Paths.get("Youth.txt.txt"));
        assertEquals(new String(exp), new String(res));
    }

    @Test
    void dkeyNotAsEkey() throws IOException {
        Ciphxor.recode("Text.txt","Test5.txt", "F3", "");
        Ciphxor.recode("Text5.txt","Test5.txt.txt", "", "3F");
        byte[] res = Files.readAllBytes(Paths.get("Test.txt"));
        byte[] exp = Files.readAllBytes(Paths.get("Youth.txt.txt"));

        assertNotEquals(exp, res);
    }

    @Test
    void withoutAnyKeys() throws IOException {
        IOException e = assertThrows(IOException.class, () ->
                 Ciphxor.recode("Text3.txt","Text3.txt.txt", "", ""));
        assertEquals(e.getMessage(),"нет ключа");
    }

    @Test
    void withTwoKeys() throws IOException {
        IOException e = assertThrows(IOException.class, () ->
                Ciphxor.recode("Text3.txt","Text3.txt.txt", "3F", "3F"));
        assertEquals(e.getMessage(),"Задайте только один ключ");
    }
}
