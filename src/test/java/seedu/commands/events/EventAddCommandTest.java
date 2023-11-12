package seedu.commands.events;

import org.junit.jupiter.api.Test;
import seedu.exception.SysLibException;
import seedu.parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventAddCommandTest {
    private final Parser parser = new Parser();
    private final EventAddCommand eventAddCommand = new EventAddCommand();

    @Test
    public void eventAddCommandValidData() throws SysLibException {
        eventAddCommand.execute("/t testrun /date 1 Dec 2001 /desc testing 123", parser.container);
        String output = parser.eventsList.get(0).toString();
        String expectedOutput = "testrun | 01 Dec 2001 | testing 123";
        assertEquals(output, expectedOutput);
    }

    @Test
    public void eventAddCommandOutput() throws SysLibException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        eventAddCommand.execute("/t testrun /date 1 DEC 2001 /desc testing 123", parser.container);
        String output = outputStream.toString();
        String expectedOutput = "Event inserted at: 0" + System.lineSeparator() +
                "0: testrun | 01 Dec 2001 | testing 123" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    @Test
    public void eventAddCommandInvalidDate() {
        assertThrows(IllegalArgumentException.class, ()->eventAddCommand.execute(
                "/t testrun /date tmr /desc testing 123", parser.container));
    }

    @Test
    public void eventAddCommandInvalidTitle() {
        assertThrows(IllegalArgumentException.class, ()->eventAddCommand.execute(
                "/t /date 12 Jan 2022", parser.container));
    }

    @Test
    public void eventAddCommandInsufficientData() {
        assertThrows(IllegalArgumentException.class, ()->eventAddCommand.execute("/t ", parser.container));
    }
}
