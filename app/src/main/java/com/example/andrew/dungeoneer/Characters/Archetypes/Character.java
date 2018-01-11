package com.example.andrew.dungeoneer.Characters.Archetypes;

import android.graphics.drawable.Drawable;

import com.example.andrew.dungeoneer.Characters.Armour;
import com.example.andrew.dungeoneer.Characters.Interfaces.IAttack;
import com.example.andrew.dungeoneer.Characters.Interfaces.ISpell;
import com.example.andrew.dungeoneer.Characters.Interfaces.ITakeDamage;
import com.example.andrew.dungeoneer.Characters.OffHand;
import com.example.andrew.dungeoneer.Characters.Weapon;
import com.example.andrew.dungeoneer.Items.Corpse;
import com.example.andrew.dungeoneer.Items.Item;
import com.example.andrew.dungeoneer.Items.ThreatObject;
import com.example.andrew.dungeoneer.Rooms.Room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public abstract class Character implements Serializable, IAttack, ITakeDamage {

    protected String name;
    protected String designation;
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
    boolean alive;
    Integer strength;
    Integer agility;
    Integer intellect;
    Integer stamina;
    Integer threat;
    Integer critChance;
    Integer critDamage;
    Boolean stunned;
    Double maxHealth;
    private Integer dodgeChance;
    private Integer blockChance;
    private boolean blockAll;
    private Integer magicDefense;
    private Integer stunnedChance;
    private String attackExclamation;
    private String defenseExclamation;
    private String healedExclamation;
    private String critExclamation;
    private ArrayList<ThreatObject> threatTable;
    Integer manaMax;
    Integer manaPool;
    Integer manaRegen;
    Integer action1cost;
    Integer action2cost;
    Integer action3cost;
    Integer action4cost;
    Integer action1threat;
    Integer action2threat;
    Integer action3threat;
    Integer action4threat;
    String action1desc;
    String action2desc;
    String action3desc;
    String action4desc;
    String classResource;


    public Character(String name, double gold, Weapon weapon, Armour armour, OffHand offHand) {
        this.name = name;
        this.designation = "Class";
        this.gold = gold;
        this.weapon = weapon;
        this.offHand = offHand;
        this.items = new ArrayList<>();
        this.superWeapon = false;
        this.armour = armour;
        this.alive = true;
        this.threatTable = new ArrayList<>();



//        Random Modifiers:
        this.damageModifier = new ArrayList<>(Arrays.asList(0.5, 0.8, 1.0, 1.0, 1.0, 1.2, 1.5));
        this.critModifier = new ArrayList<>(Arrays.asList( 0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 1.0));
        this.blockModifier = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.6, 0.7, 0.7, 0.8, 0.9));

//        Stats:
        this.strength = 100;
        this.agility = 100;
        this.intellect = 100;
        this.stamina = 100;
        this.threat = 0;
        this.critChance = 50;
        this.critDamage = 50;
        this.stunned = false;
        this.dodgeChance = agility / 100;
        this.blockChance = strength / 275;
        this.blockAll = false;
        this.magicDefense = intellect / 100;
        this.stunnedChance = stamina / 100;
        this.maxHealth = stamina * 20.00;
        this.healthBar = maxHealth;
        this.manaPool = 0;
        this.manaRegen = 5;
        this.manaMax = 100;
        this.action1cost = 0;
        this.action2cost = 0;
        this.action3cost = 0;
        this.action4cost = 0;
        this.action1threat = 0;
        this.action2threat = 0;
        this.action3threat = 0;
        this.action4threat = 0;
        this.action1desc = "";
        this.action2desc = "";
        this.action3desc = "";
        this.action4desc = "";
        this.classResource = "";





//      In-Game Messages:
        this.attackExclamation = "";
        this.defenseExclamation = "";
        this.healedExclamation = "";
        this.critExclamation = "";
    }

//         Attack Mechanics:




    public double oldAttack(Character target) {
        double damage;
        damage = weapon.getWeaponDamage() * randomDamageModifier();
        if (superWeapon) {
            damage = damage * 3;
        }
        this.superWeapon = false;
        return target.physicalDamage(damage);


    }


    public double weaponAttack(Character target) {
        double damage;
        Weapon weapon = this.weapon;
        damage = this.calculateWeaponDamage(weapon);
        damage = damage * calculateCritChance();
        damage = damage * doesSuperWeaponApply();
        damage = damage * calculateBlockChance(target);
        return target.physicalDamage(damage);
    }

     double calculateCritChance() {
        if (this.critChance + randomCritModifier() >= 1) {
            return this.critDamage;
        }
        return 1.0;
    }

    private double calculateWeaponDamage(Weapon weapon) {
        double damage;
        damage = weapon.getWeaponDamage() * this.getStrength() / 100 * randomDamageModifier();
        return damage;
    }

    private double calculateBlockChance(Character target) {
        if (target.blockAll){return 0.0;}
        else if (target.canBlockDamage(target.offHand)) {
            if (target.blockChance + randomBlockModifier() >= 1) {
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


    public double physicalDamage(double damage) {
        if (damage < 0) {
            this.healthBar = healthBar - damage;
        } else {
            damage = damage * armour.getValue();
            this.healthBar = this.healthBar - damage;
        }
        return damage;
    }

    void magicDamage(double damage) {
        if (damage < 0) {
            this.healthBar = healthBar - damage;
        } else {
            damage = damage * calculateMagicResistance(this);
            this.healthBar = this.healthBar - damage;
        }
    }

    private Integer calculateMagicResistance(Character target) {
        return 1 - (target.getIntellect() / 100);
    }

    Double randomDamageModifier() {
        Collections.shuffle(this.damageModifier);
        return damageModifier.get(0);
    }

    Double randomCritModifier() {
        Collections.shuffle(this.critModifier);
        return critModifier.get(0);
    }

    private Double randomBlockModifier() {
        Collections.shuffle(this.blockModifier);
        return blockModifier.get(0);
    }


    public String spell(Character target) {
        return "string";
    }

//    Baddies Attacks
    public double threatAttack(){
        return 0.0;
    }

//     Fellowship Attacks
//     knight attacks
    public double tauntAttack(Character target, Room room){return 0.0;}
    public void shieldWall(Room room){}
    public void tauntAOE(Room room){}
    public double headBash(Character target){ return 0.0;}
//    priest attacks
public void heal(Character target, Room room){}
    public void aoeHeal (Fellowship fellowship, Room room){}
    public void aoeHot (Fellowship fellowship, Room room){}
    public void manaStorm(Room room){}
//    wizard attacks
    public double fireBall(Character target, Room room){return 0;}
    public double fireStorm(Room room) {return 0;}
    public double slowBurn(Room room) {return 0;}
    public double slagArmour(Character target){return 0;}


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

    private boolean canBlockDamage(OffHand offhand) {
        return offhand.CanBlock();
    }


//    Health Getters and Setters:

    public Double getHealthBar() {
        return this.healthBar;
    }

    public Double getMaxHealth() {
        return this.maxHealth;
    }

    private void setHealthBar(Double healthBar) {
        this.healthBar = healthBar;
    }

    public void increaseHealth(double increase) {
        this.healthBar = healthBar + increase;
    }

    public void decreaseHealth(double decrease) {
        this.healthBar = healthBar - decrease;
    }

    public void increaseHealth50Percent() {
        this.healthBar = healthBar * 1.5;
    }

    public void maxHealthExceededCheck() {
        if (this.getHealthBar() > this.getMaxHealth()) {
            this.setHealthBar(this.getMaxHealth());
        }
    }

    public void noHealthExceededCheck() {
        if (this.getHealthBar() < 0) {
            this.setHealthBar(0.0);
        }
    }

    public boolean checkAlive() {
        if (this.healthBar <= 0) {
            this.alive = false;

        } else {
            this.alive = true;
        }
        return alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public String aliveNow(){
        if (this.healthBar > 0){
            return "ALIVE";
        }
        else
        {return "DEAD";}
    }



//  Constructor Getters and Setters:

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public boolean isSuperWeapon() {
        return superWeapon;
    }

    public void setSuperWeapon(Boolean change) {
        this.superWeapon = change;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation(){
        return this.designation;
    }


//    Gold and Loot Mechanics:

    public double getGold() {
        return this.gold;
    }

    public void takeGold(Corpse corpse) {
        addGold(corpse.getGold());
        corpse.setGold(corpse.getGold());
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public void addGold(double gold) {
        this.gold = this.gold + gold;
    }

    public void payGold(double gold) {
        this.gold = this.gold - gold;
    }

    public String examineCorpse(Corpse corpse) {
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

    public boolean getBlockAll(){ return this.blockAll; }

    public void setBlockAll(boolean state){ this.blockAll = state; }

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

    public Integer getAction1cost(){return this.action1cost;}
    public Integer getAction2cost(){return this.action2cost;}
    public Integer getAction3cost(){return this.action3cost;}
    public Integer getAction4cost(){return this.action4cost;}



//    Stats and Status Setters:

    public void setStunned(boolean stun) {
        this.stunned = stun;
    }

    public void decreaseIntellect(Integer change){
        this.intellect = this.intellect - change;
        if (this.intellect <10){this.intellect = 10;
    }}



    //    Threat Table Management
    public void setThreat(Integer number) {
        this.threat = number;
    }

    public void addTargetsToThreatTable(ThreatObject object) {
        this.threatTable.add(object);
    }

    public void sortThreatTable() {
        Collections.sort(threatTable, new Comparator<ThreatObject>() {
            public int compare(ThreatObject h1, ThreatObject h2) {
                return Integer.valueOf(h2.getThreatLevel()).compareTo(h1.getThreatLevel());
            }});}

    public Character topThreat() {
        return this.threatTable.get(0).getReference();
    }

    public Character middleThreat() {
        return this.threatTable.get(1).getReference();
    }

    public Character bottomThreat() {
        return this.threatTable.get(2).getReference();
    }

    public void randomiseThreatTable() {
        Collections.shuffle(this.threatTable);
    }

    public ArrayList<ThreatObject> getThreatTable() {
        return threatTable;
    }

    public void increaseSpecificThreat(Integer increaseInThreat, Character target) {
        for (ThreatObject heroInTable : target.getThreatTable()) {
            if (heroInTable.getReference() == this) {
                heroInTable.increaseThreatLevel(increaseInThreat);
            }
        }}

    public void raiseAllThreat(Integer increaseInThreat, Room room) {
        for (Character baddie: room.baddies
             ) {
            for (ThreatObject hero : baddie.getThreatTable()) {
                if (hero.getReference() == this) {
                    hero.increaseThreatLevel(increaseInThreat);
                }
            }
        }
    }



    //    Getters and Setters for Mana
    public Integer getManaMax() {
        return manaMax;
    }

    public Integer getManaPool() {
        return manaPool;
    }

    public void setManaPool(Integer manaPool) {
        this.manaPool = manaPool;
    }

    public void manaRegeneration(){
        this.manaPool += this.manaRegen;
    }

    public void manaPoolMaximumCheck(){
        if (this.manaPool > manaMax){
            setManaPool(manaMax);
        }
    }

    public String getClassResource(){
        return classResource;
    }

    public boolean sufficientManaCheck(Integer cost){
        if (this.manaPool >= cost){
            return true;
        }
        else return false;
    }

    public void spendManaToCast(Integer cost){
        this.manaPool -= cost;
    }

    public boolean isManaAbove50(){
        if (this.manaPool > manaMax/2){
            return false;
        }
        else return true;
    }


}
