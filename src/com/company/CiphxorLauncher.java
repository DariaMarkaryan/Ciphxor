package com.company;

import com.company.Ciphxor;
import org.kohsuke.args4j.Argument;
        import org.kohsuke.args4j.CmdLineException;
        import org.kohsuke.args4j.CmdLineParser;
        import org.kohsuke.args4j.Option;

        import java.io.IOException;

public class CiphxorLauncher {

    @Option(name = "-c", metaVar = "Encryption", usage = "Input file encryption key")
    private int inputEncryption = 0;                                            //даже если незадано,ничего не изменится

    @Option(name = "-d", metaVar = "Decryption", usage = "Input file decryption key")
    private int inputDecryption = 0;                                                                        //аналогично

    @Argument(required = true, metaVar = "InputName", usage = "Input file name")
    private String inputFileName;

    @Argument(metaVar = "OutputName", index = 1, usage = "Output file name")
    private String outputFileName;

    public static void main(String[] args) {

        new CiphxorLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Сiphxor.jar [-c Encryption] [-d Decryption] InputName [OutputName]");
            parser.printUsage(System.err);
            return;
        }

            Ciphxor ciphxor = new Ciphxor(inputFileName, outputFileName, inputEncryption, inputDecryption);
            try {
                ciphxor.recode();
                System.out.println("Done");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
    }
}


