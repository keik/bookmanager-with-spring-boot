# Bookmanager w/ Spring Boot


## Frontend build

```
[project-root] $ grunt install
[project-root] $ grunt build
```

The process generate resourecs/static and resources/templates from resoureces/work.

In the default, Spring will refer to `classpath:work` for view-templates. you can change where to refer by editting `spring.thymeleaf.prefix` in application.properties.


## Build and run

```
[project-root] $ mvn spring-boot:run
```
