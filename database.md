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


## Lazy loading
Comparison with C#/Entity Framework and Java/Hibernate

| Feature        | C# EF Core                   | Java JPA                  |
|----------------|------------------------------|---------------------------|
| Default fetch  | Explicit .Include() required | LAZY (must call method)   |
| Automatic join | Only with .Include()         | Only with FetchType.EAGER |
| Lazy loading   | Via proxy objects            | Via Hibernate proxies     |
| Session scope  | DbContext                    | Hibernate Session         |