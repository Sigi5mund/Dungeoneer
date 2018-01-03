package com.example.andrew.dungeoneer.Characters.Archetypes;

import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.Interfaces.IAttack;
import com.example.andrew.dungeoneer.Characters.Interfaces.ISpell;
import com.example.andrew.dungeoneer.Characters.Interfaces.ITakeDamage;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;
import com.example.andrew.dungeoneer.Items.Corpse;
import com.example.andrew.dungeoneer.Items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public abstract class Character implements ISpell, IAttack, ITakeDamage {

    protected String name;
    protected double gold;
    protected Weapon weapon;
    private OffHand offHand;
    Double healthBar;
    private ArrayList<Item> items;
    private boolean superWeapon;
    ArrayList<Double> damageModifier;
    private ArrayList<Double> critModifier;
    private ArrayList<Double> blockModifier;
    private Armour armour;
    private boolean alive;
    Integer strength;
    Integer agility;
    Integer intellect;
    Integer stamina;
    Integer baseThreat;
    Integer threat;
    Integer critChance;
    Integer critDamage;
    Boolean stunned;
    double maxHealth;
    private Integer dodgeChance;
    private Integer blockChance;
    private Integer magicDefense;
    private Integer stunnedChance;
    private String attackExclamation;
    private String defenseExclamation;
    private String healedExclamation;
    private String critExclamation;
    private ArrayList<Character> threatTable;




    public Character(String name, double gold, Weapon weapon, Armour armour, OffHand offHand) {
        this.name = name;
        this.gold = gold;
        this.weapon = weapon;
        this.offHand = offHand;
        this.items = new ArrayList<>();
        this.superWeapon = false;
        this.armour = armour;
        this.alive = true;
        this.threatTable = new ArrayList<>();


//        Random Modifiers:
        this.damageModifier = new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0));
        this.critModifier = new ArrayList<>(Arrays.asList(-0.1, 0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 1.0));
        this.blockModifier = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.6, 0.7, 0.7, 0.8, 0.9));

//        Stats:
        this.strength = 100;
        this.agility = 100;
        this.intellect = 100;
        this.stamina = 100;
        this.baseThreat = 50;
        this.threat = 0;
        this.critChance = 50;
        this.critDamage = 50;
        this.stunned = false;
        this.dodgeChance = agility/100;
        this.blockChance = strength/275;
        this.magicDefense = intellect/100;
        this.stunnedChance = stamina/100;
        this.maxHealth = stamina * 20;
        this.healthBar = maxHealth;

//      In-Game Messages:
        this.attackExclamation = "";
        this.defenseExclamation= "";
        this.healedExclamation = "";
        this.critExclamation = "";
    }

//         Attack Mechanics:

    public void attack(Character target) {
        double damage;
        damage = weapon.getWeaponDamage() * randomDamageModifier();
        if (superWeapon) {
            damage = damage * 3;
        }
        this.superWeapon = false;
        target.physicalDamage(damage);
        target.checkAlive();
    }

    public void weaponattack1(Character target){
        double damage;
        Weapon weapon = this.weapon;
        damage = this.calculateWeaponDamage(weapon);
        damage = damage * calculateCritChance();
        damage = damage * doesSuperWeaponApply();
        damage = damage * calculateBlockChance();
        target.physicalDamage(damage);
        target.checkAlive();
        this.threat = this.threat + this.weapon.getThreatIncrease();
    }

    private double calculateCritChance(){
        if (this.critChance + randomCritModifier() >= 1){
            return this.critDamage;
        }
        return 1.0;
    }

    private double calculateWeaponDamage(Weapon weapon){
        double damage;
        damage = weapon.getWeaponDamage() * this.getStrength()/100 * randomDamageModifier();
        return damage;
    }

    private double calculateBlockChance(){
        if (this.canBlockDamage(this.offHand)){
            if (this.blockChance + randomBlockModifier() >=1){
                return 0.0;
            }
        }
        return 1.0;
    }

    private double doesSuperWeaponApply() {
        if (this.superWeapon) {
            return 3.0;
        }
        this.superWeapon = false;
        return 1.0;
    }


    public void physicalDamage(double damage) {
        if (damage < 0) {
            this.healthBar = healthBar - damage;
        } else {
            damage = damage * armour.getValue();
            this.healthBar = this.healthBar - damage;
        }
    }

    void magicDamage(double damage){
        if (damage < 0) {
            this.healthBar = healthBar - damage;
        } else {
            damage = damage * calculateMagicResistance(this);
            this.healthBar = this.healthBar - damage;
        }
    }

    private Integer calculateMagicResistance(Character target){
        return 1 - (target.getIntellect()/100) ;
    }

    Double randomDamageModifier() {
        Collections.shuffle(this.damageModifier);
        return damageModifier.get(0);
    }

    private Double randomCritModifier(){
        Collections.shuffle(this.critModifier);
        return critModifier.get(0);
    }

    private Double randomBlockModifier(){
        Collections.shuffle(this.blockModifier);
        return blockModifier.get(0);
    }


    public String spell(Character target) {
        return "string";
    }

    public void threatAttack(){
        weaponattack1(this.topThreat());
    }



//    Weapons and Armours:

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armour getArmour() {
        return this.armour;
    }

    public void setArmour(Armour armour) {
        this.armour = armour;
    }

    public void changeArmour(Armour armour) {
        this.armour = armour;
    }

    public OffHand getOffHand() {
        return this.offHand;
    }

    private boolean canBlockDamage(OffHand offhand){
           return offhand.CanBlock();
    }


//    Health Getters and Setters:

    public Double getHealthBar() {
        return this.healthBar;
    }

    public double getMaxHealth() {
        return this.maxHealth;
    }

    public void setHealthBar(Double healthBar) {
        this.healthBar = healthBar;
    }

    public void increaseHealth(double increase){
        this.healthBar = healthBar + increase;
    }

    public void decreaseHealth (double decrease){
        this.healthBar = healthBar - decrease;
    }

    public void increaseHealth50Percent(){
        this.healthBar = healthBar * 1.5;
    }

    public void maxHealthExceededCheck(){
        if (this.getHealthBar() > this.getMaxHealth()){
            this.setHealthBar(this.getMaxHealth());
        }
    }

    public boolean checkAlive() {
        if (this.healthBar <= 0){
            this.alive = false;
            return alive;
        }
        else {
            this.alive = true;
            return alive;
        }
    }

    public boolean isAlive() {
        return alive;
    }




//  Constructor Getters and Setters:

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public boolean isSuperWeapon() {
        return superWeapon;
    }

    public void setSuperWeapon(Boolean change){
        this.superWeapon = change;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }




//    Gold and Loot Mechanics:

    public double getGold() {
        return this.gold;
    }

    public void takeGold(Corpse corpse){
        addGold(corpse.getGold());
        corpse.setGold(corpse.getGold());
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public void addGold(double gold){
        this.gold= this.gold + gold;
    }

    public void payGold(double gold){
        this.gold = this.gold - gold;
    }

    public String examineCorpse(Corpse corpse){
        return corpse.getName() + " has " + corpse.getGold() + " gold, " + corpse.getArmour() + " armour and a " + corpse.getWeapon() + " weapon. What will you take?";
    }




//    Stats and Status Getters:

    public Integer getStrength() {
        return this.strength;
    }

    public Integer getAgility() {
        return this.agility;
    }

    public Integer getIntellect() {
        return intellect;
    }

    public Integer getStamina() {
        return stamina;
    }

    public Integer getBaseThreat() {
        return this.baseThreat;
    }

    public Integer getThreat() {
        return this.threat;
    }

    public Integer getCritChance() {
        return this.critChance;
    }

    public Integer getDodgeChance() {
        return this.dodgeChance;
    }

    public Integer getBlockChance() {
        return this.blockChance;
    }

    public Integer getMagicDefense() {
        return this.magicDefense;
    }

    public Boolean getStunned() {
        return this.stunned;
    }

    public Integer getStunnedChance() {
        return this.stunnedChance;
    }

    public String getAttackExclamation() {
        return this.attackExclamation;
    }

    public String getDefenseExclamation() {
        return this.defenseExclamation;
    }

    public String getHealedExclamation() {
        return this.healedExclamation;
    }

    public String getCritExclamation() {
        return this.critExclamation;
    }



//    Stats and Status Setters:

    public void setStunned(boolean stun){
        this.stunned = stun;
    }

    public void setThreat(Integer increase){
        this.threat = this.getThreat() + increase;
    }

    public void increaseThreat( Integer increaseInThreat){
        this.setThreat(increaseInThreat);
    }

    public void addTargetsToThreatTable(Fellowship theEnemy){
        ArrayList<Character> enemies;
        enemies =theEnemy.getHeroes();
        this.threatTable.addAll(enemies);
    }

    public void sortThreatTable() {
        Collections.sort(threatTable, new Comparator<Character>(){
                    public int compare(Character h1, Character h2) {
                        return Integer.valueOf(h2.threat).compareTo(h1.threat);
                    }
        });
    }

    public void initialSortThreatTable(){
        Collections.sort(threatTable, new Comparator<Character>() {
                    public int compare(Character h1, Character h2) {
                        return Integer.valueOf(h2.baseThreat).compareTo(h1.baseThreat);
                    }
                });
    }

    public Character topThreat(){
        return this.threatTable.get(0);
    }

    public Character middleThreat(){
        return this.threatTable.get(1);
    }

    public Character bottomThreat(){
        return this.threatTable.get(2);
    }

    public void randomiseThreatTable() {
        Collections.shuffle(this.threatTable);
    }

    public ArrayList<Character> getThreatTable() {
        return threatTable;
    }
}
