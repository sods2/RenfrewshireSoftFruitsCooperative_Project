package Test.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DataManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.TransactionReportManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Fruit;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Strawberries;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alessandro Spano (Student N. rmb19196)
 */
public class TransactionReportManagerTest {

    TransactionReportManager reportManager = new TransactionReportManager();

    private final String folder = PathFile.TESTPATH.toString();

    List<Batch> batchList = new ArrayList<>();
    Fruit fruit = new Strawberries();

    FileManagement fileManagement = new MyJSON();
    DataManager dataManager = new DataManager();

    List<String> fileList;
    @Before
    public void before(){
        //Preparing data for getBatchListByDateTest
        //getting list of file names
        fileList = fileManagement.getFileList(folder);
        //getting data from all files
        Data data = fileManagement.readAll(folder, fileList);
        //getting batches' list
        batchList = dataManager.processBatchData(data);
    }

    @Test
    public void getPricesForTransactionReport() {
        Assert.assertNotNull(reportManager.getPricesForTransactionReport(batchList, fruit));

        Assert.assertEquals(reportManager.getPricesForTransactionReport(batchList, fruit).size(), 3);
    }

    @Test
    public void getWeightsForTransactionReport() {
        Assert.assertNotNull(reportManager.getWeightsForTransactionReport(batchList, fruit));

        Assert.assertEquals(reportManager.getWeightsForTransactionReport(batchList, fruit).size(), 4);
    }

    @After
    public void after(){
        batchList = null;
        fruit = null;
    }
}
