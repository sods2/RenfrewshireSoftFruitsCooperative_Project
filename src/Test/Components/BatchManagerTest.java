package Test.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.FruitGrade;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.BatchManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class BatchManagerTest {

    private final String folder = PathFile.TESTPATH.toString();
    private final String test_filename = PathFile.JSON_TEST + "_030620-BL-001";

    BatchManager batchManager = new BatchManager();
    HashMap<String, Double> grades = new HashMap<>();
    HashMap<String, Double> sizeZeroGrades = new HashMap<>();

    @Before
    public void before(){

        grades.put(FruitGrade.GRADE_A.toString(), 24.5);
        grades.put(FruitGrade.GRADE_B.toString(), 24.5);
        grades.put(FruitGrade.GRADE_C.toString(), 24.5);
        grades.put(FruitGrade.REJECTED.toString(), 26.5);

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

    @Test
    public void isGradeValid() {
        Assert.assertFalse(batchManager.isGradeValid("-1"));
        Assert.assertTrue(batchManager.isGradeValid("0"));
        Assert.assertTrue(batchManager.isGradeValid("5"));
        Assert.assertFalse(batchManager.isGradeValid("25,5"));
        Assert.assertTrue(batchManager.isGradeValid("100"));
        Assert.assertTrue(batchManager.isGradeValid("210"));
        Assert.assertTrue(batchManager.isGradeValid("5.0"));
    }

    @Test
    public void calculateKg() {
        Assert.assertEquals(batchManager.calculateKg(5.0, 100), "5,000");
        Assert.assertEquals(batchManager.calculateKg(25, 100), "25,000");
        Assert.assertEquals(batchManager.calculateKg(50.0, 100), "50,000");
        Assert.assertEquals(batchManager.calculateKg(10.0, 39), "3,900");
        Assert.assertEquals(batchManager.calculateKg(50.0, 60), "30,000");
        Assert.assertEquals(batchManager.calculateKg(122.0, 80), "97,600");
    }

    @Test
    public void gradeVerification() {
        Assert.assertTrue(batchManager.gradeVerification(grades));
        Assert.assertFalse(batchManager.gradeVerification(sizeZeroGrades));
    }

    @Test
    public void getBatchObj() {
        Assert.assertNotNull(batchManager.getBatchObj(folder, test_filename));
    }

    @After
    public void after(){
        grades = null;
        sizeZeroGrades = null;
    }
}