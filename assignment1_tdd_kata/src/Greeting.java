public class Greeting {

    public static String greet(String name) {
        if (name == null) return "Hello, my friend.";
        if (name.equals(name.toUpperCase())) return String.format("HELLO, %s!", name);
        return String.format("Hello, %s.", name);
    }

    public static String greet(String[] name) {
        return String.format("Hello, %s and %s", name[0], name[1]);
    }
}
