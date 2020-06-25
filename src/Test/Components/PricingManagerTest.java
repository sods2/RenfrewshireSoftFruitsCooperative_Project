package Test.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.FruitGrade;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DataManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DateManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.PricingManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Console;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Price;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Pricing;
import com.sun.xml.internal.ws.api.FeatureListValidatorAnnotation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PricingManagerTest {

    Console console = new Console();

    PricingManager pricingManager = new PricingManager();
    DataManager dataManager = new DataManager();
    DateManager dateManager = new DateManager();

    Data data = new Data();

    Pricing pricing = new Pricing();
    Price price = new Price();

    @Before
    public void before(){

        //Preparing price for test
        price.getPrice().put(FruitGrade.GRADE_A.toString(), 20.0);
        price.getPrice().put(FruitGrade.GRADE_B.toString(), 15.0);
        price.getPrice().put(FruitGrade.GRADE_C.toString(), 10.0);

        pricing.getPricingList().put("ST", price);

    }

    /**
     * To test this again we would need to delete the Pricing's test file Manually
     * (No files with today's date should be in the folder to avoid errors from isPricingUpToDate)
     */
    @Test
    public void isPricingUpToDateTest() {
        Assert.assertFalse(pricingManager.isPricingUpToDate(PathFile.TESTPATH.toString()));

        createTestFile();

        Assert.assertTrue(pricingManager.isPricingUpToDate(PathFile.TESTPATH.toString()));
    }

    @Test
    public void isPriceValidTest() {
        Assert.assertFalse(pricingManager.isPriceValid("-1"));
        Assert.assertTrue(pricingManager.isPriceValid("0"));
        Assert.assertTrue(pricingManager.isPriceValid("2.5"));
        Assert.assertTrue(pricingManager.isPriceValid("2.50"));
        Assert.assertFalse(pricingManager.isPriceValid("2.500"));
        Assert.assertFalse(pricingManager.isPriceValid("2,5"));
    }

    @Test
    public void getPriceMapTest() {
        //Creating the file needed
        createTestFile();
        Assert.assertNotNull(pricingManager.getPriceMap(data, "ST"));
        Assert.assertNull(pricingManager.getPriceMap(data, "BL"));
        Assert.assertNull(pricingManager.getPriceMap(data, "RA"));
        Assert.assertNull(pricingManager.getPriceMap(data, "GO"));
    }

    @After
    public void after(){

    }

    private void createTestFile(){
        //formatting data
        data = dataManager.processData(pricing, dateManager.getDateForID());
        //writing Test file
        console.createNewJSON(PathFile.TESTPATH.toString() + "/" + PathFile.PRICING_TEST.toString() + dateManager.getDateForID(), data);
    }
}