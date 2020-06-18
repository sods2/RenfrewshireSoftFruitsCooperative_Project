package Test.Console;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.BatchManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;
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

    @Before
    public void before(){
        //Getting batch object
        batchList = batchManager.getBatchObj(folder, test_filename);
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
}