package generativeai.chatGPT.bugfixing;

//import org.junit.Test;
import java.util.List;
//import static org.junit.Assert.assertEquals;

public class BugFixing3Test {

    // @Test
    public void testGenerateListWithLimit1() {
        List<String> result = BugFixing3.generateList(1, "echos");
        // assertEquals(1, result.size());
    }

    // @Test
    public void testGenerateListWithLimit5() {
        List<String> result = BugFixing3.generateList(5, "echos");
        // assertEquals(5, result.size());
    }
}
