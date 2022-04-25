package springIntro.exporter.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class FileUtils {

    private ClassLoader classLoader = getClass().getClassLoader();

    public BufferedReader fileBufferReader(String filePath)
            throws IOException, FileNotFoundException {

        if (classLoader.getResource(filePath) == null) {
            throw new FileNotFoundException("" + filePath + " file not found");
        }

        File file = newFile(classLoader.getResource(filePath).getFile());
        return newBufferReader(new FileReader(file));
    }

    private File newFile(String filePath) {
        return new File(filePath);
    }

    private BufferedReader newBufferReader(Reader in) {
        return new BufferedReader(in);
    }
    
    private BufferedWriter newBufferWriter(Writer in) {
        return new BufferedWriter(in);
    }
    
    public BufferedWriter fileBufferWriter(String filePath) throws IOException, FileNotFoundException {
        
        File file = newFile(filePath);
        return newBufferWriter(new FileWriter(file));
    }
}
