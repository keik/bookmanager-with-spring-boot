# Bookmanager w/ Spring Boot


## Developing frontend

First of all,

```
[project-root] $ npm install
[project-root] $ grunt install
```

And then,

```
[project-root] $ grunt 
```

This process watches the change of files in resourecs/{static,templates}, and then compile LESSs and lint JavaScripts automatically.


## Run embedded Tomcat

```
[project-root] $ MAVEN_OPTS="-javaagent:springloaded-1.2.1.jar -noverify" mvn spring-boot:run
```
