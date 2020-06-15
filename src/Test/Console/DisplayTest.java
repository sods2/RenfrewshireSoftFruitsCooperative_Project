package Test.Console;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display;
import org.junit.Test;

import java.util.stream.Stream;

public class DisplayTest {

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
}