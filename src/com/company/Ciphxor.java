package com.company;

import java.io.*;

@SuppressWarnings("WeakerAccess")
 class Ciphxor {

     private  int encodeTo = 0;

     private  int encodeFrom = 0;

     private String inputFileName = "";

     private String outputFileName = inputFileName;

    public Ciphxor(String inputFileName,String outputFileName, int encodeTo, int encodeFrom) {
        this.encodeTo = encodeTo;
        this.encodeFrom = encodeFrom;
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    private static char charRecode(int code, int ch) {
        return  ch == ' ' ? ' ' : (char)(((int)ch)^(code));                                  //если пробел то не трогаем
    }

     public void recode() throws IOException {
         try (FileInputStream inputStream = new FileInputStream(inputFileName)) {
              BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));                   //WTF
              File outFile = new File(outputFileName);
              FileWriter fw = new FileWriter(outFile, true);
                 String strLine;
              while((strLine = br.readLine()) != null) {
                  for(char ch: strLine.toCharArray()) {
                      char temp = Ciphxor.charRecode(encodeTo,ch);            //вдруг мы и кодируем, и
                      temp = Ciphxor.charRecode(encodeFrom, ch);                              // декодируем одновременно
                      fw.append(temp);
                  }
                  fw.write("\n\r");
              }
             fw.flush();
             fw.close();
             br.close();
         }
     }
}
