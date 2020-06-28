package Test.Data;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Alessandro Spano (Student N. rmb19196)
 */
public class FileManagementTest {

    FileManagement fileManagement = new MyJSON();

    @Test
    public void getFileListTest() {

        //Testing list of file names
        Assert.assertNotNull(fileManagement.getFileList(PathFile.TESTPATH.toString()));

    }

}