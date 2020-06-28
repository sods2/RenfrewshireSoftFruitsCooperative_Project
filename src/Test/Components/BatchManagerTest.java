package Test.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.BatchManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Alessandro Spano (Student N. rmb19196)
 */
public class BatchManagerTest {

    BatchManager batchManager = new BatchManager();

    @Before
    public void before(){

    }

    @Test
    public void checkBatchWeight() {

        Assert.assertTrue (batchManager.checkBatchWeight("0").isEmpty());
        Assert.assertFalse (batchManager.checkBatchWeight("100").isEmpty());
        Assert.assertFalse (batchManager.checkBatchWeight("50").isEmpty());
        Assert.assertTrue (batchManager.checkBatchWeight("101").isEmpty());

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