package Test.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DataManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class DataManagerTest {

    Data data;
    Batch batch;

    @Before
    public void before(){
        batch = new Batch(Integer.parseInt("50"), "001", "ST", new HashMap<>());
    }

    @Test
    public void processData() {

        Assert.assertNotNull(data = new DataManager().processData(batch, batch.getId()));
        Assert.assertEquals(data.getData().size(), 1);

    }

    @After
    public void after(){
        batch = null;
        data = null;
    }
}