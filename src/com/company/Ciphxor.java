package com.company;

import java.io.*;

@SuppressWarnings("WeakerAccess")

 class Ciphxor {
    private static byte[] keyToByteArray(String key) {
        String temp;
        byte[] res = new byte[(int)Math.ceil((float)key.length()/2)];
        int pos = res.length - 1;
        for(int i = key.length() - 1; i >= 0; i -= 2){
            if(i == 0 )
                temp = Character.toString(key.charAt(0));
            else{
                temp = key.substring(i - 1, i + 1);
            }
            res[pos] = (byte)Integer.parseInt(temp, 16);
            pos--;
        }
        return res;
    }

    private static void recode(FileInputStream inputStream, String outputFileName, String ekey, String dkey)
            throws IOException {
        FileOutputStream out = new FileOutputStream(outputFileName);
        byte[] keyarr;

        if (ekey != "" && dkey != "") {
            throw new IOException("Задайте только один ключ");
        }
        if (ekey != "" || dkey != "") {
            if(ekey!="") keyarr = keyToByteArray(ekey);
            else keyarr = keyToByteArray(dkey);
            byte temp;
            byte res;
            int i = 0;
            while ((temp = (byte)inputStream.read()) != -1) {
                res = (byte)(temp^keyarr[i]);
                i++;
                i = i % keyarr.length;
                out.write(res);
            }
        } else {
            throw new IOException("нет ключа");
        }
        out.flush();
        out.close();
        inputStream.close();
}

    public static void recode(String inputFileName, String outputFileName, String ekey, String dkey)
            throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputFileName)) {
            Ciphxor.recode(inputStream, outputFileName, ekey, dkey);
        }
    }
}
