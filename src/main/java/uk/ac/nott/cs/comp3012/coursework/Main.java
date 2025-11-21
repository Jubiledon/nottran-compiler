package uk.ac.nott.cs.comp3012.coursework;

import uk.ac.nott.cs.comp3012.coursework.backend.Backend;
import uk.ac.nott.cs.comp3012.coursework.backend.NottranBackend;
import uk.ac.nott.cs.comp3012.coursework.frontend.Frontend;
import uk.ac.nott.cs.comp3012.coursework.frontend.NottranFrontend;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Must provide both source file and target file");
            System.exit(1);
        }

        String inputFile = args[0];
        String outputFile = args[1];

//        Frontend frontend = (programText -> null);
//        Backend backend = (program -> new byte[]{});
        Compiler compiler = new Compiler(new NottranFrontend(), new NottranBackend());
        compiler.runCompiler(inputFile, outputFile);
    }
}
