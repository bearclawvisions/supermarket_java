# Extension methods
In C# a type can have extension methods by using `this` keyword in a static method. Java does not have this concept (syntactic sugar), but uses Utils class to achieve the same thing.

## C# extension methods
```csharp
public static class StringExtensions
{
    public static bool AddBasicStringTemplate(this string input)
    {
        return input + " with a basic string template";
    }
}

// Usage:
var example = "Some string";
var result = example.AddBasicStringTemplate();
```

## Java extension methods
```java
public class StringUtils {
    public static String addBasicStringTemplate(String input) {
        return input + " with a basic string template";
    }
}

// Usage:
String example = "Some string";
String result = StringUtils.addBasicStringTemplate(example);
```

| Feature                    | C#                | Java               |
| -------------------------- | ----------------- | ------------------ |
| Extension method syntax    | `this Type param` | Not supported      |
| Call like an instance      | Yes               | No                 |
| Utility method alternative | Yes, static class | Yes, static method |
| Interface default method   | N/A               | Limited use case   |
---