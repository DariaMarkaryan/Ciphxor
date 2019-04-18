package com.company;

import com.company.Ciphxor;
import org.kohsuke.args4j.*;
import java.io.IOException;

public class CiphxorLauncher {

    @Option(name = "-c", metaVar = "inputEncryption", usage = "Input file encryption key")
    private String inputEncryption = "0";                                            //если не задано,ничего не изменится

    @Option(name = "-d", metaVar = "inputDecryption", usage = "Input file decryption key")
    private String inputDecryption = "0";                                                                        //аналогично

    @Argument(required = true, usage = "input file name")
    private String inputFileName;

    @Option(name = "-o", usage = "output file name")
    private String outputFileName;

    public static void main(String[] args) {
        new CiphxorLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if(outputFileName == null)
                outputFileName = inputFileName + ".txt";
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Сiphxor.jar [-c Encryption] [-d Decryption] InputName [-o OutputName]");
            parser.printUsage(System.err);
            return;
        }

            Ciphxor ciphxor = new Ciphxor();
            try {
                Ciphxor.recode(inputEncryption, inputDecryption, inputFileName, outputFileName);
                System.out.println("Done");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
    }
}


