package Test.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DataManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Price;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Pricing;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * @author Alessandro Spano (Student N. rmb19196)
 */
public class DataManagerTest {

    FileManagement fileManagement = new MyJSON();
    DataManager dataManager = new DataManager();

    Data data;
    Batch batch;

    Pricing pricing = new Pricing();
    Price price = new Price();
    Data dataForPricing = new Data();

    Data dataForProcessBatch = new Data();

    @Before
    public void before(){
        batch = new Batch(Double.parseDouble("50"), "001", "ST", new HashMap<>());

        price.getPrice().put("GRADE A", 12.0);
        pricing.getPricingList().put("ST", price);
        dataForPricing = dataManager.processData(pricing,"TestPricing");

        //Preparing for processBatchData
        //getting list of file names
        List<String> files = fileManagement.getFileList(PathFile.BATCH.toString());

        //getting data from all files
        dataForProcessBatch = fileManagement.readAll(PathFile.BATCH.toString(), files);
    }

    @Test
    public void processData() {

        Assert.assertNotNull(data = new DataManager().processData(batch, batch.getId()));
        Assert.assertEquals(data.getData().size(), 1);

    }

    @Test
    public void processBatchData() {
        Assert.assertNotNull(dataManager.processBatchData(dataForProcessBatch));
    }

    @Test
    public void processPricingData() {

        Assert.assertEquals(dataManager.processPricingData(dataForPricing,"ST").size(), 1);

        Assert.assertNull(dataManager.processPricingData(dataForPricing, "BL"));
        Assert.assertNull(dataManager.processPricingData(dataForPricing, "GO"));
        Assert.assertNull(dataManager.processPricingData(dataForPricing, "RA"));
    }

    @After
    public void after(){
        batch = null;
        data = null;
    }
}