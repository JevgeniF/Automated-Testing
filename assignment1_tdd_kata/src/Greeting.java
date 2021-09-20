import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Greeting {

    public static String greet(String name) {
        if (name == null) return "Hello, my friend.";
        if (name.equals(name.toUpperCase())) return String.format("HELLO, %s!", name);
        return String.format("Hello, %s.", name);
    }

    public static String greet(String @NotNull [] names) {
        List<String> lowercaseNames = new ArrayList<>();
        List<String> uppercaseNames = new ArrayList<>();
        for (String name: names) {
            if (!name.matches("^\".+\"$")) {
                if (name.equals(name.toUpperCase())) uppercaseNames.addAll(Arrays.asList(name.split(", ")));
                else lowercaseNames.addAll(Arrays.asList(name.split(", ")));
            } else {
                name = name.replaceAll("\"", "");
                if (name.equals(name.toUpperCase())) uppercaseNames.add(name);
                else lowercaseNames.add(name);
            }
        }

        String greeting = "";
        switch (lowercaseNames.size()) {
            case 1 -> greeting += greet(lowercaseNames.get(0));
            case 2 -> greeting += String.format("Hello, %s and %s.", lowercaseNames.get(0), lowercaseNames.get(1));
            default -> {
                if (lowercaseNames.size() >= 3) {
                    String lastName = lowercaseNames.remove(lowercaseNames.size() - 1);
                    String firstNamesInGreeting = String.join(", ", lowercaseNames);
                    greeting += String.format("Hello, %s, and %s.", firstNamesInGreeting, lastName);
                }
            }
        }
        if (!uppercaseNames.isEmpty()) greeting += " AND ";
        switch (uppercaseNames.size()) {
            case 1 -> greeting += greet(uppercaseNames.get(0));
            case 2 -> greeting += String.format("HELLO, %s AND %s!", uppercaseNames.get(0), uppercaseNames.get(1));
            default -> {
                if (lowercaseNames.size() >= 3) {
                    String lastName = uppercaseNames.remove(uppercaseNames.size() - 1);
                    String firstNamesInGreeting = String.join(", ", uppercaseNames);
                    greeting += String.format("HELLO, %s, AND %s.", firstNamesInGreeting, lastName);
                }
            }
        }
        return greeting;
    }
}
