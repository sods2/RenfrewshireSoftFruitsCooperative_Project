package Test.Data;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DateManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Common.Constants.JSON_EXERTION;
import static org.junit.Assert.*;

/**
 * @author Alessandro Spano (Student N. rmb19196)
 */
public class MyJSONTest {

    FileManagement fileManagement;
    Batch batch;
    Data data;

    @Before
    public void before(){
        fileManagement = new MyJSON();
        data = new Data();
        batch = new Batch(50.0, "005", "ST", new HashMap<>());
        data.getData().put(batch.getId(), batch);
    }

    @Test
    public void createNewFileTest() throws IOException {
        //Test JSON for batch
        Assert.assertTrue(fileManagement.createNewFile(PathFile.TESTPATH.toString() + "/" + PathFile.JSON_TEST.toString() + "_" + batch.getId(), data));
        //Test JSON for Pricing
        Assert.assertTrue(fileManagement.createNewFile(PathFile.TESTPATH.toString() + "/" + PathFile.PRICING_TEST.toString() + "_" + new DateManager().getDateForID(), data));
    }

    //createNewFile needs to run first otherwise will return error
    @Test
    public void readTest() {
        //Test JSON for batch
        Assert.assertNotNull(fileManagement.read(PathFile.TESTPATH.toString() + "/" + PathFile.JSON_TEST.toString() + "_" + batch.getId()));
    }

    @Test
    public void readAllTest() {
        //getting list of file names
        List<String> files = fileManagement.getFileList(PathFile.TESTPATH.toString());

        //Test JSON for batch
        Assert.assertNotNull(fileManagement.readAll(PathFile.TESTPATH.toString(), files));

        //Test all files are retrieved
        Assert.assertEquals(fileManagement.readAll(PathFile.TESTPATH.toString(), files).getData().size(), files.size());
    }

    @After
    public void after(){
        fileManagement = null;
        data = null;
        batch = null;
    }
}