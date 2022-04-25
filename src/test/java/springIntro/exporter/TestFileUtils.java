package springIntro.exporter;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import springIntro.exporter.utils.FileUtils;

public class TestFileUtils {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Test
    public void testFileBufferReaderTrue() {
        String filePath = "resources/Info.txt";
        FileUtils fileUtils = new FileUtils();
        boolean result = false;
        try {
            BufferedReader br = fileUtils.fileBufferReader(filePath);
            result = (br !=null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        assertTrue(result);
    }

    // It throws FileNotFoundException
    @Test
    public void testFileBufferReaderFalse() {
        String filePath = "resources/Info2.txt";
        FileUtils fileUtils = new FileUtils();
        boolean result = false;
        try {
            BufferedReader br = fileUtils.fileBufferReader(filePath);
            result = (br !=null);
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        
        assertFalse(result);
    }
}
