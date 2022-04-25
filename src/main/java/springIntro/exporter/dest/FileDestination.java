package springIntro.exporter.dest;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springIntro.exporter.stack.ICollection;
import springIntro.exporter.utils.FileUtils;

@Component("fileDest")
public class FileDestination implements IDestination {

    @Autowired
    private FileUtils   fileUtils;
    @Autowired
    private ICollection dataCollection;

    private String      outputFilePath;

    @Autowired
    public FileDestination(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    @Override
    public void write() {

        int counter = 0;
        BufferedWriter bufferWriter = null;

        try {
            bufferWriter = fileUtils.fileBufferWriter(outputFilePath);
            while (true) {

                String data = dataCollection.get();
                if (data == null) {
                    counter++;
                    if (counter == 5) {
                        break;
                    }
                } else {
                    bufferWriter.write(data + "\n");
                }

            }
            bufferWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "FileDestination [" + outputFilePath + "]";
    }

}
