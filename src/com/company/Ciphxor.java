package com.company;

import java.io.*;
import java.nio.ByteBuffer;

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

    private static String decryption(String msg, byte[] key ) {
        String hexToPairs = "";
        int i = 0;
        while (i < msg.length() - 1) {
            String output = msg.substring(i, i + 2);
            int decimal = Integer.parseInt(output, 16);
            hexToPairs += (char)decimal;
            i += 2;
        }
        char[] hexToPairsArr = hexToPairs.toCharArray();
        String decryptedText = "";
        int keyItr = 0;
        for (int j = 0; j < hexToPairsArr.length; j++) {
            byte temp =(byte)((byte)hexToPairsArr[j] ^ key[keyItr]);
            decryptedText += (char)temp;
            keyItr++;
            if (keyItr >= key.length) {
                keyItr = 0;
            }
        }
        return decryptedText;
    }

    private static String encryption(String msg, byte[] key){
        int Itr = 0;
        String res = "";

        for (int j = 0; j < msg.length(); j++) {
            int temp = msg.charAt(j) ^ key[Itr];
            res += String.format("%02x", (byte) temp);
            Itr++;
                if (Itr >= key.length)
                    Itr = 0;
        }
    return res;
    }

    private static void recode(FileInputStream inputStream, String outputFileName, String ekey, String dkey)
            throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        File outFile = new File(outputFileName);
        FileWriter fw = new FileWriter(outFile, false);
        String encrypHexa = "";
        byte[] ekeyarr = keyToByteArray(ekey);
        byte[] dkeyarr = keyToByteArray(dkey);
        String msg;
        String rawMsg = "";

    while((rawMsg = br.readLine()) != null){
        if (ekey != "") {
            encrypHexa = encryption(rawMsg,ekeyarr);
            if (dkey != "") encrypHexa = decryption(encrypHexa,dkeyarr);

        } else if (dkey != "") {
            encrypHexa = decryption(rawMsg,dkeyarr);
        } else {
            encrypHexa = rawMsg;
        }
        fw.append(encrypHexa);
        fw.write("\r\n");
    }
        fw.flush();
        fw.close();
        br.close();
}

    public static void recode(String inputFileName, String outputFileName, String ekey, String dkey)
            throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputFileName)) {
            Ciphxor.recode(inputStream, outputFileName, ekey, dkey);
        }
    }
}
