# Notes

[External reading .Net vs Java/Spring](external-reading.md)  
[Dependency injection .Net vs Java/Spring](dependency-injection.md)
[Database handling .Net vs Java/Spring](database.md)
[Extension methods(.Net) vs Utils(Java)](extensions.md)

This is a small project to get familiar with Java when coming from .NET.
Similarities and differences between Java and C# for a .NET developer.

Given that this is a small project, where the idea is to have a clean architecture without overengineering.
The frontend is a simple Vue.js app which is a copy from the frontend used in a C# project.
This project uses Maven as a build tool. There is a parent pom.xml file which contains the common configuration for all modules as well as the dependency management.
The project is split into 4 modules:
- core
- domain
- infrastructure
- persistence

In C# this is more like a layered Clean Architecture, but in Java this would be called Hexagonal Architecture.  

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
---

# Optional<T> vs @Nullable
Both types are similar to the nullable type annotation in C# (string?).  
But java has different rules for handling null values.  
`@Nullable String` = Absence is implicit. You discover it at runtime.  
`Optional<String>` = Absence is explicit. The type system forces awareness.  

## Usage guidelines
| `Optional<String>` | `@Nullable String` |
|--------------------|--------------------|
| Wrapper object     | Raw reference      |
| Explicit absence   | Implicit absence   |
| Forces handling    | Easy to forget     |
| Fluent API         | Manual null checks |
| Slower             | Fast               |
| For returns        | For params/fields  |


# Enums
Enums in Java are fundamentally different from C#. In C# enums are like a named integer type.  
In Java enums are more like a class with static final fields. They can have methods and constructors.  
This allows for more complex behavior and state management within enums.  

In Java enums are not compile time constants, like in C#. They are runtime constants. This means that you need a workaround to get compile time constants.  
Duplication is inevitable, e.g. a role enum class can be used in the whole application, but to use the name as a constant you need to duplicate it to static final fields.  