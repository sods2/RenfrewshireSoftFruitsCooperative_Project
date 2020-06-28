package Test.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.FruitGrade;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.BatchManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DataManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DateManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * @author Alessandro Spano (Student N. rmb19196)
 */
public class BatchManagerTest {

    private final String folder = PathFile.TESTPATH.toString();
    private final String test_filename = PathFile.JSON_TEST + "_030620-BL-001";

    BatchManager batchManager = new BatchManager();

    HashMap<String, Double> grades = new HashMap<>();
    HashMap<String, Double> sizeZeroGrades = new HashMap<>();

    FileManagement fileManagement = new MyJSON();
    DataManager dataManager = new DataManager();

    List<String> fileList;
    List<Batch> batchList;

    @Before
    public void before(){
        //Preparing for gradeVerification
        grades.put(FruitGrade.GRADE_A.toString(), 22.0);
        grades.put(FruitGrade.GRADE_B.toString(), 30.5);
        grades.put(FruitGrade.GRADE_C.toString(), 21.0);
        grades.put(FruitGrade.REJECTED.toString(), 26.5);

        //Preparing data for getBatchListByDateTest
        //getting list of file names
        fileList = fileManagement.getFileList(folder);
        //getting data from all files
        Data data = fileManagement.readAll(folder, fileList);
        //getting batches' list
        batchList = dataManager.processBatchData(data);

    }

    @Test
    public void checkBatchWeightTest() {

        Assert.assertTrue (batchManager.checkBatchWeight("0").isEmpty());
        Assert.assertFalse (batchManager.checkBatchWeight("100").isEmpty());
        Assert.assertFalse (batchManager.checkBatchWeight("50").isEmpty());
        Assert.assertTrue (batchManager.checkBatchWeight("101").isEmpty());

    }

    @Test
    public void checkFarmNTest() {

        Assert.assertEquals ("001", batchManager.checkFarmN("1"));
        Assert.assertEquals ("001", batchManager.checkFarmN("01"));
        Assert.assertEquals("999", batchManager.checkFarmN("999"));
        Assert.assertNotEquals("1", batchManager.checkFarmN("1"));

    }

    @Test
    public void isGradeValidTest() {
        Assert.assertFalse(batchManager.isGradeValid("-1"));
        Assert.assertTrue(batchManager.isGradeValid("0"));
        Assert.assertTrue(batchManager.isGradeValid("5"));
        Assert.assertFalse(batchManager.isGradeValid("25,5"));
        Assert.assertTrue(batchManager.isGradeValid("100"));
        Assert.assertTrue(batchManager.isGradeValid("210"));
        Assert.assertTrue(batchManager.isGradeValid("5.0"));
    }

    @Test
    public void calculateKgTest() {
        Assert.assertEquals(batchManager.calculateKg(5.0, 100), "5,000");
        Assert.assertEquals(batchManager.calculateKg(25, 100), "25,000");
        Assert.assertEquals(batchManager.calculateKg(50.0, 100), "50,000");
        Assert.assertEquals(batchManager.calculateKg(10.0, 39), "3,900");
        Assert.assertEquals(batchManager.calculateKg(50.0, 60), "30,000");
        Assert.assertEquals(batchManager.calculateKg(122.0, 80), "97,600");
    }

    @Test
    public void gradeVerificationTest() {
        Assert.assertTrue(batchManager.gradeVerification(grades));
        Assert.assertFalse(batchManager.gradeVerification(sizeZeroGrades));
    }

    @Test
    public void getBatchObjTest() {
        Assert.assertNotNull(batchManager.getBatchObj(folder, test_filename));
    }

    @Test
    public void getBatchPriceTest() {
        Assert.assertEquals(BatchManager.getBatchPrice(new DateManager().getDateForID(), "ST", 82.0, grades), "60,27");
        Assert.assertEquals(BatchManager.getBatchPrice(new DateManager().getDateForID(), "ST", 50.0, grades), "36,75");
        Assert.assertEquals(BatchManager.getBatchPrice(new DateManager().getDateForID(), "ST", 0.0, grades), "0,00");
        Assert.assertNotEquals(BatchManager.getBatchPrice(new DateManager().getDateForID(), "ST", 0.1, grades), "0,00");
    }

    @Test
    public void getGradePriceTest() {
        Assert.assertEquals(BatchManager.getGradesPrice(new DateManager().getDateForID(), "ST", 82.0, grades, "GRADE A"), "18,04");
        Assert.assertEquals(BatchManager.getGradesPrice(new DateManager().getDateForID(), "ST", 0.0, grades, "GRADE A"), "0,00");
        Assert.assertEquals(BatchManager.getGradesPrice(new DateManager().getDateForID(), "ST", 0.1, grades, "GRADE A"), "0,02");
        Assert.assertNotEquals(BatchManager.getGradesPrice(new DateManager().getDateForID(), "ST", 50.0, grades, "GRADE A"), "0,00");
    }

    @Test
    public void getBatchListByDateTest() {

        Assert.assertNotNull(batchManager.getBatchListByDate(batchList, "03 06 2020"));
        Assert.assertEquals(batchManager.getBatchListByDate(batchList, "03 06 2020").get(0).getReceivedDate(), "03 06 2020");

        Assert.assertEquals(batchManager.getBatchListByDate(batchList, "13 06 2020").size(), 0);
    }

    @Test
    public void getBatchListByFruitTypeTest() {

        Assert.assertNotNull(batchManager.getBatchListByFruitType(batchList, "ST"));
        Assert.assertNotNull(batchManager.getBatchListByFruitType(batchList, "BL"));
        Assert.assertNotNull(batchManager.getBatchListByFruitType(batchList, "RA"));
        Assert.assertNotNull(batchManager.getBatchListByFruitType(batchList, "GO"));

        Assert.assertNull(batchManager.getBatchListByFruitType(batchList, "test"));
    }

    @After
    public void after(){
        grades = null;
        sizeZeroGrades = null;
    }
}