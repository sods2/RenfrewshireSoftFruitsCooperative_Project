package Test.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DateManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Alessandro Spano (Student N. rmb19196)
 */
public class DateManagerTest {

    DateManager dateManager = new DateManager();
    LocalDate date;
    LocalDate dateForID;
    DateTimeFormatter dateFormatter;
    DateTimeFormatter dateForIDFormatter;

    @Before
    public void before(){
        date = LocalDate.now();
        dateFormatter = DateTimeFormatter.ofPattern("dd MM yyyy");

        dateForID = LocalDate.now();
        dateForIDFormatter = DateTimeFormatter.ofPattern("ddMMyy");
    }

    @Test
    public void getDate() {

        Assert.assertEquals(date.format(dateFormatter), dateManager.getDate());

    }

    @Test
    public void getDateForID() {

        Assert.assertEquals(dateForID.format(dateForIDFormatter), dateManager.getDateForID());

    }

    @After
    public void after(){
        dateManager = null;
        date = null;
        dateFormatter = null;
    }
}
