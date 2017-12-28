package com.example.andrew.dungeoneer.Characters.Archetypes;

import com.example.andrew.dungeoneer.Items.Item;

import java.util.ArrayList;

public class Fellowship {

    String name;
    ArrayList<Character> heroes;

    Integer goldPool;
    ArrayList<Item> itemPool;
    Integer fellowshipMegaManaPool;

    public Fellowship(String name, ArrayList<Character> heroes) {
        this.name = name;
        this.heroes = heroes;
        this.goldPool = 0;
        this.itemPool = new ArrayList<>();
        this.fellowshipMegaManaPool = 0;
    }


// Getters:
    public String getName() {
        return name;
    }

    public ArrayList<Character> getHeroes() {
        return heroes;
    }

    public Integer getGoldPool() {
        return goldPool;
    }

    public ArrayList<Item> getItemPool() {
        return itemPool;
    }

    public Integer getFellowshipMegaManaPool() {
        return fellowshipMegaManaPool;
    }



//    Setters:


    public void setHeroes(ArrayList<Character> heroes) {
        this.heroes = heroes;
    }

    public void setGoldPool(Integer goldPool) {
        this.goldPool = goldPool;
    }

    public void setItemPool(ArrayList<Item> itemPool) {
        this.itemPool = itemPool;
    }

    public void setFellowshipMegaManaPool(Integer fellowshipMegaManaPool) {
        this.fellowshipMegaManaPool = fellowshipMegaManaPool;
    }
}
