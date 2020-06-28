package Test.Data;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * @author Alessandro Spano (Student N. rmb19196)
 */
public class MyFileTest {

    FileManagement fileManagement;
    Object obj;

    @Before
    public void before(){
        fileManagement = new MyFile();
    }

    @Test
    public void read() throws IOException {
        Assert.assertNotNull(obj = fileManagement.read(PathFile.TESTPATH.toString() + "/" + PathFile.TEST.toString()));
    }

    @After
    public void after(){
        fileManagement = null;
    }
}