# quick start




## 1. install & verify maven

- install
  please refer to:
  [maven quick-start](https://levelup.gitconnected.com/how-to-create-a-java-project-with-maven-89867bef811a)


- verify

```shell
# execute this command in terminal, then get version of maven
mvn -v
```

## 2. create maven project
```shell
mvn archetype:generate -DgroupId=com.edditen.expdemo -DartifactId=expdemo -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

cd expdemo

mvn clean compile
```


## 3. add dependencies in maven
in file: `pom.xml`, add `spring-expression`

```xml
 <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-expression</artifactId>
    <version>5.2.22.RELEASE</version>
</dependency>
```



## 4. code

- Agent
  in path: `src/main/java/com/edditen/expdemo/Agent.java`

```java
package com.edditen.expdemo;

import java.util.Random;

public class Agent {
    int age;
    double money;
    Random random = new Random();

    public Agent() {
        this.age = random.nextInt(100);
        this.money = random.nextDouble() * 100000;
    }

    public int getAge() {
        return age;
    }

    public double getMoney() {
        return money;
    }


}

```


- AgentHouse
  in path: `src/main/java/com/edditen/expdemo/AgentHouse.java`


```java
package com.edditen.expdemo;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AgentHouse extends ArrayList<Agent> {
    ExpressionParser parser = new SpelExpressionParser();

    public AgentHouse() {
        super();
    }

    public List<Agent> selectAgents(String condition) {
        Expression exp = parser.parseExpression(condition);
        return this.stream().
                filter(agent -> exp.getValue(agent, Boolean.class)).
                collect(Collectors.toList());
    }


}
```


- test code
  in path: `src/test/java/com/edditen/expdemo/AgentHouseTest.java`

```java
package com.edditen.expdemo;

import org.junit.Test;

import java.util.List;


public class AgentHouseTest {

    @Test
    public void selectAgents() {

        AgentHouse agentHouse = new AgentHouse();
        for (int i = 0; i < 100; i++) {
            agentHouse.add(new Agent());
        }

        String condition = "age > 20 and money < 50000";
        List<Agent> agents = agentHouse.selectAgents(condition);
        for (Agent agent : agents) {
            System.out.println("age: " + agent.age + ", money:" + agent.money);
        }

    }
}
```




## Reference:
1. [maven quick-start](https://levelup.gitconnected.com/how-to-create-a-java-project-with-maven-89867bef811a)
2. [spring expression](https://www.baeldung.com/spring-expression-language)
