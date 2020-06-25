package Test.Console;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.*;
import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static junit.framework.TestCase.*;

public class DataInputTest {

    InputStream inputStream;
    ByteArrayInputStream in_userInput;
    ByteArrayInputStream in_userInput_2;

    ByteArrayInputStream in_menuInput;
    ByteArrayInputStream in_menuInput_2;

    ByteArrayInputStream in_consoleIdle;

    ByteArrayInputStream in_validation;
    ByteArrayInputStream in_validation_2;

    @Before
    public void before(){
        inputStream = System.in;
    }

    @Test
    public void getMenuInputTest(){
        //passing value to System input
        in_menuInput = new ByteArrayInputStream("test".getBytes());
        System.setIn(in_menuInput);

        assertEquals(DataInput.getUserInput(), "test");

        //passing value to System input
        in_menuInput_2 = new ByteArrayInputStream("123".getBytes());
        System.setIn(in_menuInput_2);

        assertEquals(DataInput.getUserInput(), "123");

    }

    @Test
    public void getUserInputTest(){
        //passing value to System input
        in_userInput = new ByteArrayInputStream("test".getBytes());
        System.setIn(in_userInput);

        assertEquals(DataInput.getUserInput(), "test");

        //passing value to System input
        in_userInput_2 = new ByteArrayInputStream("123".getBytes());
        System.setIn(in_userInput_2);

        assertEquals(DataInput.getUserInput(), "123");
    }

    @Test
    public void checkInputTest() {

        assertFalse(DataInput.checkInput(""));
        assertTrue(DataInput.checkInput(Command._1.toString()));
        assertTrue(DataInput.checkInput(Command._2.toString()));
        assertTrue(DataInput.checkInput(Command._3.toString()));
        assertTrue(DataInput.checkInput(Command._4.toString()));
        assertTrue(DataInput.checkInput(Command._5.toString()));
        assertTrue(DataInput.checkInput(Command.BACK.toString()));
        assertTrue(DataInput.checkInput(Command.EXIT.toString()));
        assertTrue(DataInput.checkInput(Command.HELP.toString()));
        assertFalse(DataInput.checkInput("test"));

    }

    @Test
    public void consoleIdleTest() {
        //passing value to System input
        in_consoleIdle = new ByteArrayInputStream("\r".getBytes());
        System.setIn(in_consoleIdle);

        assertTrue(DataInput.consoleIdle());
    }

    @Test
    public void checkAttemptNTest() {

        assertFalse(DataInput.checkAttemptN(0));
        assertFalse(DataInput.checkAttemptN(1));
        assertFalse(DataInput.checkAttemptN(2));
        assertTrue(DataInput.checkAttemptN(3));

    }

    @Test
    public void validationTest() {
        Console console = new Console();

        //passing value to System input
        in_validation = new ByteArrayInputStream("y".getBytes());
        System.setIn(in_validation);

        assertTrue(console.validate_Input());

        //passing value to System input
        in_validation_2 = new ByteArrayInputStream("n".getBytes());
        System.setIn(in_validation_2);

        assertFalse(console.validate_Input());
    }

    @After
    public void after() throws IOException {
        //resetting input after getUserInput
        System.setIn(inputStream);

        //Close stream
        inputStream.close();
    }
}