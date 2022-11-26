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