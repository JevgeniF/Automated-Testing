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
}
