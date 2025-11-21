package uk.ac.nott.cs.comp3012.coursework;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import uk.ac.nott.cs.comp3012.coursework.ast.Ast;
import uk.ac.nott.cs.comp3012.coursework.backend.Backend;
import uk.ac.nott.cs.comp3012.coursework.frontend.Frontend;

/**
 * Base class for a compiler.
 */
public class Compiler {

    // frontend to use
    private final Frontend frontend;
    // backend to use
    private final Backend backend;

    /**
     * Construct a new compiler.
     *
     * @param frontend frontend to use
     * @param backend  backend to use
     */
    public Compiler(Frontend frontend, Backend backend) {
        this.frontend = frontend;
        this.backend = backend;
    }

    /**
     * Compile the source program in {@code inputFile} and emit the compiled code to
     * {@code outputFile}.
     *
     * @param inputFile  file to read source from
     * @param outputFile file to write target code to
     * @throws IOException if there is an error reading or writing
     */
    public void runCompiler(String inputFile, String outputFile) throws IOException {
        StringBuilder programText = new StringBuilder();
        Files.readAllLines(Path.of(inputFile)).forEach(programText::append);

        Ast program = frontend.runFrontend(programText.toString());
        byte[] code = backend.runBackend(program);

        try (BufferedOutputStream out = new BufferedOutputStream(
            new FileOutputStream(outputFile))) {
            for (byte b : code) {
                out.write(b);
            }
        }
    }
}
