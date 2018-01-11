package com.example.andrew.dungeoneer.Characters.Archetypes;

import java.io.Serializable;
import java.util.ArrayList;

public class Fellowship implements Serializable {

    String name;
    public ArrayList<Character> heroes;

    public Fellowship(String name, ArrayList<Character> heroes) {
        this.name = name;
        this.heroes = heroes;
    }

// Getters:
    public String getName() {
        return name;
    }

    public ArrayList<Character> getHeroes() {
        return heroes;
    }

    public Character healer(){
        return this.getHeroes().get(0);
    }
    public Character tank(){
        return this.getHeroes().get(1);
    }
    public Character dps(){
        return this.getHeroes().get(2);
    }




//    Setters:


    public void addHeroes(Character character){
        this.heroes.add(character);
    }

    public void removeHeroes(Character character){
        this.heroes.remove(character);
    }
}
