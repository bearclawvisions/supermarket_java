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