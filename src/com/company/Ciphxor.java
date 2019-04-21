package com.company;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

@SuppressWarnings("WeakerAccess")
 class Ciphxor {
    private static void recode(FileInputStream inputStream, String outputFileName, String key) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        File outFile = new File(outputFileName);
        FileWriter fw = new FileWriter(outFile, false);
        int ien = Integer.parseInt(key,16);
        String strLine;

        while ((strLine = br.readLine()) != null) {
            byte[] txt = strLine.getBytes();
            byte[] ekey = hexToBytes(ien);
            byte[] res = new byte[0];
           if(ien != 0)
               res = xor(txt, ekey, strLine.length());
            fw.append(new String(res));
           fw.write("\r\n");
        }
        fw.flush();
        fw.close();
        br.close();
    }

    private static void recode(FileInputStream inputStream, String outputFileName, String keyEnc, String keyDec)
            throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        File outFile = new File(outputFileName);
        FileWriter fw = new FileWriter(outFile, false);
        int ien = Integer.parseInt(keyEnc,16);
        int ide = Integer.parseInt(keyDec,16);
        String strLine;

        while ((strLine = br.readLine()) != null) {
            byte[] txt = strLine.getBytes();
            byte[] ekey = hexToBytes(ien);
            byte[] dkey = hexToBytes(ide);
            byte[] res = xor(txt, ekey, strLine.length());
            res = xor(res, dkey,strLine.length());
            fw.append(new String(res));
            fw.write("\r\n");
        }
        fw.flush();
        fw.close();
        br.close();
    }

    private static byte[] xor(byte[] txt, byte[]key, int len){
        byte[] res = new byte[len];
        for (int i = 0; i < len; i++){
            res[i]= (byte)(txt[i]^key[i%key.length]);
        }
        return res;
    }

    private static byte[] hexToBytes(int key){
        int numOfBytes;
        if(key < Math.pow(2, 8)) numOfBytes = 1;
        else if(key >= Math.pow(2, 8) && key < Math.pow(2, 16)) numOfBytes = 2;
        else if(key >= Math.pow(2, 16) && key < Math.pow(2, 24)) numOfBytes = 3;
        else numOfBytes = 4;
        ByteBuffer bos = ByteBuffer.allocate(4);
        bos.putInt(key);
        byte[] res = new byte[numOfBytes];
        byte[] temp = bos.array();
        System.arraycopy(temp, temp.length - res.length, res, 0, res.length);
        return res;
    }

    public static void recode(String inputFileName, String outputFileName, String key)
            throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputFileName)) {
            Ciphxor.recode(inputStream, outputFileName, key);
        }
    }
    public static void recode(String inputFileName, String outputFileName, String keyFirst, String keySec)
            throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputFileName)) {
            Ciphxor.recode(inputStream, outputFileName, keyFirst, keySec);
        }
    }

}
