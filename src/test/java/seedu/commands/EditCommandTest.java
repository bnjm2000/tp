package seedu.commands;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;
import seedu.parser.Parser;
import seedu.util.TestUtil;


import static seedu.commands.EditCommand.RESOURCE_NOT_FOUND;
import static seedu.commands.EditCommand.EDIT_SUCCESS;
import static seedu.common.FormatMessages.formatLastLineDivider;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EditCommandTest {
    private static final Parser parser = new Parser();
    private static List<Resource> testResourceList = new ArrayList<>();
    public TestUtil testUtil = new TestUtil();
    private final List<Resource> emptyResourceList = new ArrayList<>();
    private final Command editCommand = new EditCommand();

    @BeforeAll
    public static void setup() throws SysLibException {
        testResourceList = TestUtil.fillTestList();
    }

    @Test
    public void testEditResourceNotFound() throws SysLibException {
        String outputMessage = testUtil.getOutputMessage(editCommand, "/i 123 /t NEWTITLE", emptyResourceList);
        String expectedMessage =  RESOURCE_NOT_FOUND;
        assertEquals(expectedMessage, outputMessage);
    }

    @Test
    public void testNoArgumentGiven() throws SysLibException {
        assertThrows(SysLibException.class, ()->editCommand.execute("/i 123", parser.container));

    }
    @Test
    //public void testEditTitleBehavior() throws SysLibException {
    //    executeEditSuccessBehavior("/i 2 /t NEWTITLE");
    //}
    //@Test
    //public void testNotBookBehavior() throws SysLibException {
    //    assertThrows(SysLibException.class, ()->editCommand.execute("/i 1 /g Horror", parser.container));
    //}
    //@Test
    //public void testEditAuthorBehavior() throws SysLibException {
    //    executeEditSuccessBehavior("/i 2 /a NEWAUTHOR");
    //}
    //@Test
    //public void testEditGenreBehavior() throws SysLibException {
    //    executeEditSuccessBehavior("/i 2 /g Horror, Action, Fantasy");
    //}

    private void executeEditSuccessBehavior(String argument) throws SysLibException {
        String outputMessage = testUtil.getOutputMessage(editCommand, argument, testResourceList);
        String expectedMessage = EDIT_SUCCESS + formatLastLineDivider((testResourceList.get(1)).toString());
        assertEquals(expectedMessage, outputMessage);
    }
}
