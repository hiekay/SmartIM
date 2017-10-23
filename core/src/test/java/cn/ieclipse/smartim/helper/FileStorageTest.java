package cn.ieclipse.smartim.helper;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileStorageTest {
    int limit = 3;
    FileStorage fs;
    
    @Before
    public void setUp() throws Exception {
        fs = new FileStorage(limit, "test.txt");
    }
    
    @After
    public void tearDown() throws Exception {
        fs.release();
    }
    
    @Test
    public void test() {
        try {
            String input = "line1";
            fs.append(input);
            int lines = fs.getLines();
            String read = fs.read(lines - 1);
            assertEquals(input, read);
            
            input = "中文abc";
            fs.append(input);
            lines = fs.getLines();
            assertEquals(2, lines);
            read = fs.read(lines - 1);
            assertEquals(input, read);
            
            input = "中文abc1";
            fs.append(input);
            input = "中文abc2";
            fs.append(input);
            input = "中文abc3";
            fs.append(input);
            lines = fs.getLines();
            
            assertEquals(this.limit, lines);
            read = fs.read(this.limit - 1);
            System.out.println(read);
            assertEquals("中文abc3", read);
            
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
}
