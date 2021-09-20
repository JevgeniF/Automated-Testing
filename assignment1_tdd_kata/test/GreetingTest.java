import org.junit.Assert;
import org.junit.Test;

public class GreetingTest {

    @Test   // Requirement 1. The method interpolates name in a simple greeting.
    public void testReturnsGreetingWithName() {
        String name = "Bob";
        Assert.assertEquals("Hello, Bob.", Greeting.greet(name));
    }

    @Test   // Requirement 2. The method handles nulls and returns greeting.
    public void testReturnsStandInWhenNameIsNullString() {
        String name = null;
        Assert.assertEquals("Hello, my friend.", Greeting.greet(name));
    }

    @Test   // Requirement 3. The method returns greeting all uppercase if name is all uppercase.
    public void testReturnsUppercaseGreetingIfNameUppercase() {
        String name = "JERRY";
        Assert.assertEquals("HELLO, JERRY!", Greeting.greet(name));
    }

    @Test   // Requirement 4. The method returns greeting for all persons listed in array of two names.
    public void testReturnsGreetingWithAllNamesInTwoNamesArray() {
        String[] names = new String[] {"Jill", "Jane"};
        Assert.assertEquals("Hello, Jill and Jane.", Greeting.greet(names));
    }

    @Test   // Requirement 5. The method returns greeting for all persons listed in array of two and more names.
    public void testReturnsGreetingWithAllNamesInBiggerThanTwoNamesArray() {
        String[] names = new String[] {"Amy", "Brian", "Charlotte"};
        Assert.assertEquals("Hello, Amy, Brian, and Charlotte.", Greeting.greet(names));
    }

    @Test   // Requirement 6. The method returns normal greeting for normal names and uppercase greeting for uppercase names.
    public void testReturnsMixedGreetingForNormalAndUppercaseNames() {
        String[] names = new String[] {"Amy", "BRIAN", "Charlotte"};
        Assert.assertEquals("Hello, Amy and Charlotte. AND HELLO, BRIAN!", Greeting.greet(names));
    }

    @Test   // Requirement 7. The method allows escaping intentional commas in String[] name elements.
    public void testEscapesIntentionalCommas() {
        String[] names = new String[] {"Bob", "Charlie, Dianne"};
        Assert.assertEquals("Hello, Bob, Charlie, and Dianne.", Greeting.greet(names));
    }

    @Test   // Requirement 8. The method allows escaping intentional quotes in String[] name elements.
    public void testEscapesIntentionalQuotes() {
        String[] names = new String[] {"Bob", "\"Charlie, Dianne\""};
        Assert.assertEquals("Hello, Bob and Charlie, Dianne.", Greeting.greet(names));
    }
}
