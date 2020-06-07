package Test.Data;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

public class MyJSONTest {

    FileManagement fileManagement;
    Batch batch;
    Data data;

    @Before
    public void before(){
        fileManagement = new MyJSON();
        data = new Data();
        batch = new Batch(50, "005", "ST", new HashMap<>());
        data.getData().put(batch.getId(), batch);
    }

    @Test
    public void createNewFile() throws IOException {//TODO: keep adding different file types.
        //Test JSON for batch
        Assert.assertTrue(fileManagement.createNewFile(PathFile.TESTPATH.toString() + "/" + PathFile.JSON_TEST.toString() + "_" + batch.getId(), data));
    }

    @Test
    public void read() {
        //Test JSON for batch
        Assert.assertNotNull(fileManagement.read(PathFile.TESTPATH.toString() + "/" + PathFile.JSON_TEST.toString() + "_" + batch.getId()));
    }

    @After
    public void after(){
        fileManagement = null;
        data = null;
        batch = null;
    }
}