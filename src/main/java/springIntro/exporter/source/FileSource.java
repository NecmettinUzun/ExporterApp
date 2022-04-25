package springIntro.exporter.source;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springIntro.exporter.stack.ICollection;
import springIntro.exporter.utils.FileUtils;

@Component("fileSource")
public class FileSource implements ISource {

    @Autowired
    private FileUtils fileUtils;
    @Autowired
    private ICollection dataCollection;
    
    private String inputFilePath;
    
    @Autowired
    public FileSource(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }
    
    @Override
    public boolean read() {
       
        boolean result = false;
        try(BufferedReader bufferReader = fileUtils.fileBufferReader(this.inputFilePath)) {

            String line;
            while((line = bufferReader.readLine()) != null) {
               this.dataCollection.put(line);
            }
            
            result = true;
        } catch (FileNotFoundException e) {
            System.out.println(""+e);
            result = false;
        } catch (IOException e) {
            System.out.println(""+e);
            result = false;
        } 

        return result;
    }

    @Override
    public String toString() {
        return "FileSource [filePath "+inputFilePath+"]";
    }

}
