### To run this application

Please type the following command: 

```bash
mvn compile exec:java -Dexec.mainClass="multiagent.App"
```

Thanks to Maven Compiler Plugin shown as below: 

```xml
  <build> 
    <plugins>
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>3.0.0</version>
        <configuration>
          <mainClass> multiagent.App </mainClass>
        </configuration>
      </plugin>
    </plugins>
  </build>
```

You can run the application with the following command: 

```bash
mvn compile exec:java
```

