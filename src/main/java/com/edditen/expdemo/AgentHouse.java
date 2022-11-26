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

    public List<Agent> selectAgents(int minAge, double maxMoney) {
        return this.stream().
                filter(agent -> agent.age > minAge).
                filter(agent -> agent.money < maxMoney).
                sorted(Comparator.comparing(Agent::getAge)).
                collect(Collectors.toList());
    }

}