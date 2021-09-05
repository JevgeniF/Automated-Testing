import java.util.ArrayList;
import java.util.List;

public class Greeting {

    public static String greet(String name) {
        if (name == null) return "Hello, my friend.";
        if (name.equals(name.toUpperCase())) return String.format("HELLO, %s!", name);
        return String.format("Hello, %s.", name);
    }

    public static String greet(String[] name) {
        if (name.length == 2) return String.format("Hello, %s and %s.", name[0], name[1]);
        List<String> allNamesExcLast = new ArrayList<>();
        for (int i = 0; i < name.length - 1; i++) {
            allNamesExcLast.add(name[i]);
        }
        String lastName = name[name.length - 1];
        String firstNamesInGreeting = String.join(", ", allNamesExcLast);
        return String.format("Hello, %s, and %s.", firstNamesInGreeting, lastName);
    }
}
