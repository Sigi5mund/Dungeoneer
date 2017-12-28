package com.example.andrew.dungeoneer.Rooms;

import java.util.ArrayList;

import com.example.andrew.dungeoneer.Items.Corpse;
import com.example.andrew.dungeoneer.Items.Item;
import com.example.andrew.dungeoneer.Magic.ITick;
import com.example.andrew.dungeoneer.Characters.Archetypes.Character;

public abstract class Room {

    double rewardGold;
    public ArrayList<Character> goodies;
    public ArrayList<Character> baddies;
    public ArrayList<Corpse> floor;
    public ArrayList<Item> shelves;
    public ArrayList<ITick> hotsAndDots;


    public Room(ArrayList goodies, ArrayList baddies, double rewardGold) {
        this.rewardGold = rewardGold;
        this.goodies = goodies;
        this.baddies = baddies;
        this.floor = new ArrayList<>();
        this.shelves = new ArrayList<>();
        this.hotsAndDots = new ArrayList<>();
    }



//    Goodies and Baddies ArrayList Maintenance:

    public ArrayList<Character> getGoodies() {
        return goodies;
    }

    public void setGoodies(ArrayList<Character> goodies) {
        this.goodies = goodies;
    }

    public void addGoodies(Character character){
        this.goodies.add(character);
    }

    public void removeGoodies(Character character){
        this.goodies.remove(character);
    }

    public ArrayList<Character> getBaddies() {
        return baddies;
    }

    public void setBaddies(ArrayList<Character> baddies) {
        this.baddies = baddies;
    }

    public void addBaddies(Character character){
        this.baddies.add(character);
    }

    public void removeBaddies(Character character){
        this.baddies.remove(character);
    }




//    Room Reward Mechanisms:

    public void collectReward(Character character){
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
        goodies.removeIf(next -> !next.checkAlive());
    }

    private Corpse corpseCreation(Character character){
        Corpse playerCorpse;
        playerCorpse= new Corpse(character.getName()+ "'s corpse", character.getGold(), character.getItems());
        playerCorpse.setArmour(character.getArmour());
        playerCorpse.setWeapon(character.getWeapon());
        playerCorpse.setOffHand(character.getOffHand());
        return playerCorpse;
    }

    private void addToFloor(Corpse corpse) {
        this.floor.add(corpse);
    }




//    End of Combat Turn Checks:

    private void checkForCorpses(){
        for (Character character: goodies){
            if (character.checkAlive() == false){
                addToFloor(corpseCreation(character));
            }
        }
        for (Character character: baddies){
            if (character.checkAlive() == false){
                addToFloor(corpseCreation(character));
            }
        }
    }

    private void removeStuns() {
        for (Character character : goodies) {
            character.setStunned(false);
        }
        for (Character character : baddies) {
            character.setStunned(false);
        }
    }

    public void triggerITickMechanism(){
        for (ITick iTick: hotsAndDots) {
            iTick.tick();
        }
    }

    private void checkForMaxHealth() {
        for (Character character : goodies) {
            character.maxHealthExceededCheck();
        }
        for (Character character : baddies) {
            character.maxHealthExceededCheck();
        }
    }

    public void endOfCombatChecks(){
        checkForCorpses();
        removeDead();
        removeStuns();
        triggerITickMechanism();
        checkForMaxHealth();
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
