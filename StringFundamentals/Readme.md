# Java Strings

In Java, a String is the type of object that can store a sequence of characters enclosed by double quotes and every character is stored in 16 bits, i.e., using UTF 16-bit encoding. A string acts the same as an array of characters.

Example: 

String name = "Geeks";
String num = "1234";

```
public class Geeks {

    // Main Function
    public static void main(String args[])
    {

        // creating Java string using a new keyword
        String str = new String("Geeks");

        System.out.println(str);
    }
}

```

**Output:** -> Geeks

# String Class in Java

The String class in Java is used to create and manipulate sequences of characters. It is one of the most commonly used classes in Java. Objects of the String class are immutable, which means they cannot be changed once created

### Key Features of the String Class

1. Immutable
Immutable means that once a String object is created, its value cannot be changed.

```
public class Main {
    public static void main(String[] args) {
        String text = "hello";
        text.charAt(0) = 'H';  // compile-time error
    }
}
```

**Explanation:** The line text.charAt(0) = 'H'; causes a compile-time error because charAt(0) returns a read-only char, not a variable. String is immutable in Java, you cannot modify its characters directly.

2. Thread-Safe
String in Java is thread-safe because it is immutable, allowing safe access by multiple threads without synchronization.

3. Supports Various Utility Methods
String is a predefined final class in Java present in java.lang package. It provides various methods to create, manipulate, and compare strings, like length(), charAt(), concat(), equals(), etc.

```
import java.io.*;

class GFG {
    public static void main (String[] args) {
        String str = "hello geeks";
        System.out.println("Length of String-> "+str.length()); 
        System.out.println("Changed String ->"+str.toUpperCase());
    }
}
```

Outputs-> 
Length of String-> 11
Changed String ->HELLO GEEKS

4. Implements Interfaces
The String class in Java implements three important interfaces.

**CharSequence:** Allows access to characters in the string using charAt(), length(), etc.
**Comparable<String>:** Enables comparing two strings lexicographically using compareTo()
**Serializable:** Allows string objects to be converted into a byte stream

#### String Constructors in Java
In Java, String constructors are used to create new String objects from different sources like character arrays, byte arrays, or another string. Although strings in Java are usually created using string literals, the String class also provides constructors for more control.

Let us check these constructors using a example demonstrating the use of them.

```
public class Geeks {
    public static void main(String[] args) {
        
        // Constructor 1: Creating string using new keyword
        String str1 = new String("Hello Java");
        System.out.println("String using new keyword: " + str1);

        // Constructor 2: Creating string from character array
        char[] charArray = { 'J', 'A', 'V', 'A' };
        String str2 = new String(charArray);
        System.out.println("String from char array: " + str2);

        // Constructor 3: Creating string from byte array
        byte[] byteArray = { 72, 101, 108, 108, 111 };
        String str3 = new String(byteArray);
        System.out.println("String from byte array: " + str3);
    }
}
```

Output->
String using new keyword: Hello Java
String from char array: JAVA
String from byte array: Hello

String Constructors Table

<img width="1536" height="1024" alt="string_constructors" src="https://github.com/user-attachments/assets/5c9d0d82-d27e-44ed-8bc3-488f8b0383e2" />
