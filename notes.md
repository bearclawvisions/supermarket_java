# Notes
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
```C#
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