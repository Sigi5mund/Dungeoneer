package com.example.andrew.dungeoneer.Rooms;

import com.example.andrew.dungeoneer.Characters.Archetypes.Character;
import com.example.andrew.dungeoneer.Characters.Archetypes.Fellowship;
import com.example.andrew.dungeoneer.Items.Corpse;
import com.example.andrew.dungeoneer.Items.Item;
import com.example.andrew.dungeoneer.Items.ThreatObject;
import com.example.andrew.dungeoneer.Magic.ITick;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Room implements Serializable{

    public String name;
    double rewardGold;
    public Fellowship fellowship;
    public ArrayList<Character> baddies;
    public ArrayList<Corpse> floor;
    public ArrayList<Item> shelves;
    public ArrayList<ITick> hotsAndDots;


    public Room(String name) {
        this.name = name;
        this.rewardGold = rewardGold;
        this.fellowship = new Fellowship("The Valiant Few", new ArrayList<>());
        this.baddies = new ArrayList<>();
        this.floor = new ArrayList<>();
        this.shelves = new ArrayList<>();
        this.hotsAndDots = new ArrayList<>();
    }


//    Goodies and Baddies ArrayList Maintenance:

    public ArrayList<Character> getGoodies() {
        return this.fellowship.getHeroes();
    }

    public void loadGoodies(Fellowship fellowship) {
        this.fellowship = fellowship;
    }

    public void addGoodies(Character character) {
        this.fellowship.addHeroes(character);
    }

    public void removeGoodies(Character character) {
        this.fellowship.removeHeroes(character);
    }

    public ArrayList<Character> getBaddies() {
        return baddies;
    }

    public void setBaddies(ArrayList<Character> baddies) {
        this.baddies = baddies;
    }

    public void addBaddies(Character character) {
        this.baddies.add(character);
    }

    public void removeBaddies(Character character) {
        this.baddies.remove(character);
    }


//    Room Reward Mechanisms:

    public void collectReward(Character character) {
        character.setGold(character.getGold() + this.rewardGold);
    }

    public double getRewardGold() {
        return rewardGold;
    }

    public void setRewardGold(double rewardGold) {
        this.rewardGold = rewardGold;
    }


//    Corpse Creation and Implementation:

    public void removeDead() {
        baddies.removeIf(next -> !next.checkAlive());
        fellowship.getHeroes().removeIf(next -> !next.checkAlive());
    }

    private Corpse corpseCreation(Character character) {
        Corpse playerCorpse;
        playerCorpse = new Corpse(character.getName() + "'s corpse", character.getGold(), character.getItems());
        playerCorpse.setArmour(character.getArmour());
        playerCorpse.setWeapon(character.getWeapon());
        playerCorpse.setOffHand(character.getOffHand());
        return playerCorpse;
    }

    private void addToFloor(Corpse corpse) {
        this.floor.add(corpse);
    }


//    End of Combat Turn Checks:

    private void checkForCorpses() {
        for (Character character : fellowship.getHeroes()) {
            if (!character.checkAlive()) {
                addToFloor(corpseCreation(character));
            }
        }
        for (Character character : baddies) {
            if (!character.checkAlive()) {
                addToFloor(corpseCreation(character));
            }
        }
    }

    private void removeStuns() {
        for (Character character : fellowship.getHeroes()) {
            character.setStunned(false);
        }
        for (Character character : baddies) {
            character.setStunned(false);
        }
    }

    private void removeBlockAll() {
        for (Character character : fellowship.getHeroes()) {
            character.setBlockAll(false);
        }
        for (Character character : baddies) {
            character.setBlockAll(false);
        }
    }

    public void triggerITickMechanism() {
        for (ITick iTick : hotsAndDots) {
            iTick.tick();
        }
    }

    private void checkForMaxHealth() {
        for (Character character : fellowship.getHeroes()) {
            character.maxHealthExceededCheck();
        }

        for (Character character : baddies) {
            character.maxHealthExceededCheck();
        }
    }

    private void checkForNoHealth() {
        for (Character character : fellowship.getHeroes()) {
            character.noHealthExceededCheck();
        }

        for (Character character : baddies) {
            character.noHealthExceededCheck();
        }
    }



    public void checkManaAndRegenerate(){
        for (Character character : fellowship.getHeroes()) {
            character.manaRegeneration();
            character.manaPoolMaximumCheck();
        }
        for (Character character : baddies) {
            character.manaRegeneration();
            character.manaPoolMaximumCheck();
        }
    }

    public void endOfCharacterTurnChecks(){
        checkForMaxHealth();
        checkForNoHealth();
    }


    public void endOfCombatChecks() {
        removeStuns();
        removeBlockAll();
        triggerITickMechanism();
        endOfCharacterTurnChecks();
        checkManaAndRegenerate();

    }


    public void massThreatAttack(){
        this.sortAllThreatTables();
        for (Character baddie: this.baddies) {
            baddie.threatAttack();
        }
    }


    public void addThreatObjectsToTables() {
        for (Character baddie : baddies) {
            for (Character goodie : fellowship.getHeroes()
                    ) {
                baddie.addTargetsToThreatTable(new ThreatObject(goodie, goodie.getThreat()));
            }
        }
    }

    public void changeAllThreatTables(Integer changeInThreat, Character heroToBeReduced) {
        for (Character baddie: baddies) {
            for (ThreatObject hero : baddie.getThreatTable()) {
                if (hero.getReference() == heroToBeReduced) {
                hero.increaseThreatLevel(changeInThreat);
            }

        }}
    sortAllThreatTables();
    }

    public void sortAllThreatTables(){
        for (Character baddie: baddies) {
            baddie.sortThreatTable();
        }
    }


    public void villiansTurnAttacks() {
        for (Character baddie : baddies
                ) {
            baddie.sortThreatTable();
            baddie.threatAttack();
        }
    }



//        public String goToTheNextRoom(ArrayList<Character> adventurers){
//        if (baddies.size() == 0){
//            Room nextRoom = new Dungeon(adventurers, baddies, 10000)
//
//        }
//
//
//        return ""
//    }



}
