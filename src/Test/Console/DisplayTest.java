package Test.Console;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.FruitGrade;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.BatchManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Price;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Pricing;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DisplayTest {
    private final String folder = PathFile.TESTPATH.toString();
    private final String test_filename = PathFile.JSON_TEST + "_030620-BL-001";

    BatchManager batchManager = new BatchManager();
    List<Batch> batchList = new ArrayList<>();


    Price price = new Price();
    Pricing pricing = new Pricing();

    @Before
    public void before(){
        //Getting batch object
        batchList = batchManager.getBatchObj(folder, test_filename);

        //Preparing price for test
        price.getPrice().put(FruitGrade.GRADE_A.toString(), 20.0);
        price.getPrice().put(FruitGrade.GRADE_B.toString(), 15.0);
        price.getPrice().put(FruitGrade.GRADE_C.toString(), 10.0);

        pricing.getPricingList().put("ST", price);
    }

    @Test
    public void displayString() {

        System.out.println("expected: Test display content (String)");

        Display.displayString("Test display content (String)");

    }

    @Test
    public void displayStream() {

        Stream<String> testStream = "Test display content (Stream)".codePoints().mapToObj(o -> Character.toString((char) o));

        System.out.println("expected: Test display content (Stream)");

        Display.displayStream(testStream);
    }

    @Test
    public void displayBatchesWithGrades() {
        System.out.println("expected: Test display Batches with grades: no value should be printed only the headers will be displayed");
        Display.displayBatchesWithGrades(new ArrayList<>());

        System.out.println("expected: Test display Batches with grades: the headers will be displayed and a test batch with grades will be displayed");
        Display.displayBatchesWithGrades(batchList);
    }

    @Test
    public void displayPricing() {

        System.out.println("Testing display of Pricing the format should be (Fruit Type) - { GRADE A: value, GRADE B: value, GRADE C: value }");
        System.out.println("");
        Display.displayPricing(pricing);

        //Testing Pricing
        Assert.assertTrue(pricing.getPricingList().containsKey("ST"));
        Assert.assertFalse(pricing.getPricingList().containsKey("BL"));
        Assert.assertFalse(pricing.getPricingList().containsKey("RA"));
        Assert.assertFalse(pricing.getPricingList().containsKey("GO"));
    }

    @After
    public void after(){
        batchList = null;
        pricing = null;
    }
}