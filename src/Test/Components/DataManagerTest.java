package Test.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DataManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DateManager;
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
import java.util.Objects;

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
        batch = new Batch(Integer.parseInt("50"), "001", "ST", new HashMap<>());

        price.getPrice().put("TestPrice", 12.0);
        pricing.getPricingList().put(new DateManager().getDateForID(), price);
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

//        Assert.assertNotNull(dataManager.processPricingData(dataForPricing, Price.class));
//        Assert.assertEquals(dataManager.processPricingData(dataForPricing, Price.class).size(), 1);
//
//        Assert.assertNotNull(dataManager.processPricingData(dataForPricing, Pricing.class));
//        Assert.assertEquals(dataManager.processPricingData(dataForPricing, Pricing.class).size(), 1);
    }

    @After
    public void after(){
        batch = null;
        data = null;
    }
}