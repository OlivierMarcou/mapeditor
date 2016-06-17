package net.arkaine.pc.mapeditor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.READ;

/**
 * Created by olivier on 17/06/16.
 */
public class Utils {

    public static String openFile(Path path){
        // Convert the string to a
        // byte array.
        String contenu = "";
        try (InputStreamReader in = new InputStreamReader(
                Files.newInputStream(path, READ))) {
            in.read(CharBuffer.wrap(contenu));
        } catch (IOException x) {
            System.err.println(x);
        }
        return contenu;
    }
}
