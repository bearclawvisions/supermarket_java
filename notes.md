# Notes
[Other External reference](https://medium.com/@vikpoca/moving-from-c-to-java-platforms-overview-e9f3600acd6f)  
This is a small project to get familiar with Java when coming from .NET.
Similarities and differences between Java and C# for a .NET developer.

Given that this is a small project, where the idea is to have a clean architecture without overengineering.
The frontend is a simple Vue.js app which is a copy from the frontend used in a C# project.
This project uses Maven as a build tool. There is a parent pom.xml file which contains the common configuration for all modules as well as the dependency management.
The project is split into 3 modules:
- core
- domain
- infrastructure
---

# Dependency difference between Java and C#
## Dependency Injection .NET Core
In C# (.NET) at startup Interface → implementation mapping. Constructor injection used in consumers.  
```csharp
builder.Services.AddScoped<IUserService, UserService>();
```

Spring Boot equivalent: IoC Container + Bean Registration
Spring Boot has an IoC container that does the same thing.

## There are two main ways to register dependencies:
### 1. Component Scanning (Annotation-based)
```java
public interface UserService {
   void doSomething();
}
```
Implementation
```java
@Service
public class UserServiceImpl implements UserService {
    public void doSomething() {}
}
```

Consumer
```java
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService; // ctor injection
    }
}

// OR

@RestController
public class UserController {

    @Autowired
   private final UserService userService; // field injection

   public UserController() {
   }
}
```

Spring automatically finds @Service and registers it as a bean, then injects it where UserService is required.

### 2. Manual Registration
It is also possible to register beans manually in a DependencyConfig.java as is used in this project.
This approach is similar to .NET Core Dependency Injection and uses less auto-magic code in the project.
---

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

# Technical terms

| C#                               | Java equivalent                                |
|----------------------------------|------------------------------------------------|
| ASP.NET Core                     | Spring Boot                                    |
| .csproj                          | pom.xml                                        |
| library(.csproj)                 | module                                         |
| Startup.cs(or other entry point) | Application.java (main/@SpringBootApplication) |
| .Net builder DI (or Autofac)     | Spring IoC Container                           |
| Controller (inherit)             | @RestController (Spring)                       |
| EF Core                          | JPA (Hibernate)                                |
| Dapper                           | JBDI (?)                                       |
| appsettings.json                 | application.properties / application.yml       |
| [Annotation]                     | @Annotation (Bean)                             |
| dotnet CLI / MSBuild             | Maven / Gradle / Other                         |

# JPA vs EFCore
EFCore has an explicit DbContext class that is used to interact with the database.
JPA uses an implicit EntityManager (hidden) class that is used to interact with the database.
EFCore supports fluent API for configuring the database schema, while JPA uses annotations on entities.
EFCore puts queries in a transaction by default, Java has to use @Transactional annotation.

```java
@Transactional  // This wraps everything in one transaction
public void createUserWithOrders(User user, List<Order> orders) {
    userRepository.save(user);      // Not committed yet
    orders.forEach(orderRepository::save);  // Not committed yet
    // All commits together at method end (like SaveChanges())
}
```

| EFCore                    | JPA            |
|---------------------------|----------------|
| DbContext                 | EntityManager  |
| DbSet                     | Entity         |
| SaveChangesAsync          | @Transactional |
| SaveChangesAsync (single) | .save (on repo) |
