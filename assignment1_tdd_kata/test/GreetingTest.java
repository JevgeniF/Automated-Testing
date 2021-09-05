import org.junit.Assert;
import org.junit.Test;

public class GreetingTest {

    @Test   // Requirement 1. The method interpolates name in a simple greeting.
    public void testSimpleGreeting() {
        String name = "Bob";
        Assert.assertEquals("Hello, Bob.", Greeting.greet(name));
    }

    @Test   // Requirement 2. The method handles nulls and returns greeting.
    public void testNullStringHandling() {
        String name = null;
        Assert.assertEquals("Hello, my friend.", Greeting.greet(name));
    }

    @Test   // Requirement 3. The method returns greeting all uppercase if name is all uppercase.
    public void testUppercaseGreetingIfNameUppercase() {
        String name = "JERRY";
        Assert.assertEquals("HELLO, JERRY!", Greeting.greet(name));
    }

    @Test   // Requirement 4. The method returns greeting for all persons listed in array of two names.
    public void testTwoNamesArrayHandling() {
        String[] name = new String[] {"Jill", "Jane"};
        Assert.assertEquals("Hello, Jill and Jane.", Greeting.greet(name));
    }

    @Test   // Requirement 5. The method returns greeting for all persons listed in array of two and more names.
    public void testTwoNamesOrMoreArrayHandling() {
        String[] name = new String[] {"Amy", "Brian", "Charlotte"};
        Assert.assertEquals("Hello, Amy, Brian, and Charlotte.", Greeting.greet(name));
    }

    @Test   // Requirement 6. The method returns normal greeting for normal names and uppercase greeting for uppercase names.
    public void testMixedGreetingForNormalAndUppercaseNames() {
        String[] name = new String[] {"Amy", "BRIAN", "Charlotte"};
        Assert.assertEquals("Hello, Amy and Charlotte. AND HELLO, BRIAN!", Greeting.greet(name));
    }

    @Test   // Requirement 7. The method allows escaping intentional commas in String[] name elements.
    public void testEscapeIntentionalCommas() {
        String[] name = new String[] {"Bob", "Charlie, Dianne"};
        Assert.assertEquals("Hello, Bob, Charlie, and Dianne.", Greeting.greet(name));
    }
}
