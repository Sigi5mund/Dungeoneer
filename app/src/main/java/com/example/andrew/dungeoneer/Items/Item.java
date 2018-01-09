package com.example.andrew.dungeoneer.Items;

import com.example.andrew.dungeoneer.Characters.Archetypes.Character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Item implements Serializable{
    String name;
    String description;
    double gold;
    ArrayList<Item> items;
    ArrayList<Integer> randomChance;

    public Item(String name, double gold, ArrayList<Item> items) {
        this.name = name;
        this.description = description;
        this.gold = gold;
        this.items = items;
        this.randomChance = new ArrayList<>(Arrays.asList(1, 1, 1,2,3));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item){
        this.items.remove(item);
    }

    public void takeGold(Character looter){
        looter.addGold(gold);
    }

    public String openBox(Character target) {
        Integer chance;
        Collections.shuffle(this.randomChance);
        chance = randomChance.get(0);
        switch (chance) {
            case 1:
                target.increaseHealth(250);
                return target.getName() + " opens the box in the corner and finds a health potion. It raises " + target.getName() + "'s health by 250 points!";
            case 2:
                target.increaseHealth50Percent();
                return target.getName() + " opens the box in the corner and finds a health potion. The health potion raises " + target.getName() + "'s health by 50%!";
            case 3:
                target.setSuperWeapon(true);
                return target.getName() + " opens the box. A magical dust hangs in the air for a moment before infusing "+ target.getName() + "'s weapon with magical energy";
            default:
                return "Error"; }
    }

}
