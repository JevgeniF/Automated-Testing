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
        Assert.assertEquals("Hello, Jill and Jane", Greeting.greet(name));
    }
}
