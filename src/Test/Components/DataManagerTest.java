package Test.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DataManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DateManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Price;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Pricing;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Objects;

public class DataManagerTest {

    DataManager dataManager = new DataManager();

    Data data;
    Batch batch;

    Pricing pricing = new Pricing();
    Price price = new Price();
    Data dataForPricing = new Data();

    @Before
    public void before(){
        batch = new Batch(Integer.parseInt("50"), "001", "ST", new HashMap<>());

        price.getPrice().put("TestPrice", 12.0);
        pricing.getPricingList().put(new DateManager().getDateForID(), price);
        dataForPricing = dataManager.processData(pricing,"TestPricing");
    }

    @Test
    public void processData() {

        Assert.assertNotNull(data = new DataManager().processData(batch, batch.getId()));
        Assert.assertEquals(data.getData().size(), 1);

    }

//    @Test
//    public void processPricingData() {
//
//        Assert.assertNotNull(dataManager.processPricingData(dataForPricing, Price.class));
//        Assert.assertNotNull(dataManager.processPricingData(dataForPricing, Pricing.class));
//
//    }

    @After
    public void after(){
        batch = null;
        data = null;
    }
}