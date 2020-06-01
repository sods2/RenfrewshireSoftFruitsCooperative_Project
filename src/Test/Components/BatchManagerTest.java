package Test.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.BatchManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BatchManagerTest {

    BatchManager batchManager = new BatchManager();

    @Before
    public void before(){

    }

    @Test
    public void checkBatchWeight() {

        Assert.assertFalse (batchManager.checkBatchWeight(0));
        Assert.assertTrue (batchManager.checkBatchWeight(100));
        Assert.assertTrue (batchManager.checkBatchWeight(50));
        Assert.assertFalse (batchManager.checkBatchWeight(101));

    }

    @Test
    public void checkFarmN() {

        Assert.assertEquals ("001", batchManager.checkFarmN("1"));
        Assert.assertEquals ("001", batchManager.checkFarmN("01"));
        Assert.assertEquals("999", batchManager.checkFarmN("999"));
        Assert.assertNotEquals("1", batchManager.checkFarmN("1"));

    }

    @After
    public void after(){

    }
}