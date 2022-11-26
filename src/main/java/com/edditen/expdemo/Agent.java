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
