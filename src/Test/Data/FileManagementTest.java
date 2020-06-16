package Test.Data;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;
import org.junit.Assert;
import org.junit.Test;

public class FileManagementTest {

    FileManagement fileManagement = new MyJSON();

    @Test
    public void getFileList() {

        //Testing list of file names
        Assert.assertNotNull(fileManagement.getFileList(PathFile.TESTPATH.toString()));

    }

}