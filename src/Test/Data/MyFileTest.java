package Test.Data;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

public class MyFileTest {

    MyFile myFile = new MyFile();
    Data data = new Data();
    Batch batch;

    @Before
    public void before(){
        batch = new Batch(10, "001", "ST", new HashMap<>());
        data.getData().put(batch.getId(), batch);
    }

    @Test
    public void read() throws IOException {
        Assert.assertNotNull(myFile.write(PathFile.TESTPATH.toString() + "/" + PathFile.TEST.toString(), data));//TODO
    }

    @After
    public void after(){

    }
}