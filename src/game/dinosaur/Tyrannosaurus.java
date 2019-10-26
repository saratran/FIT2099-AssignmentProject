package game.dinosaur;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.FoodSkill;
import game.actor.ExitGameAction;
import game.item.Corpse;
import game.item.Egg;

public class Tyrannosaurus extends Dinosaur {

	public Tyrannosaurus(String name, Maturity maturity) {
		super(name, 'T', 100, maturity, 1);		
		edibleFoodSkills.add(FoodSkill.CARNIVORE);
		layEggChance=0.02;
		addSkill(DinoSkill.LAND);

	}

	public Tyrannosaurus(String name) {
		this(name, Maturity.ADULT);	
	}

	public Tyrannosaurus(Maturity maturity) {
		this("Tyrannosaurus", maturity);
	}

	public Tyrannosaurus() {
		this("Tyrannosaurus");
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		Action action =  super.playTurn(actions, lastAction, map, display);
		if ((maturity == Maturity.ADULT) & (this.hasSkill(DinoSkill.CAPTIVE_BRED))){
			System.out.println("A captive bred T-Rex has reached adulthood! You won!");	
			System.exit(0);
			return null;
		} else {
			return action;
		}
	}

	@Override
	protected void initFoodLevel() {
		if (this.maturity == Maturity.ADULT) {
			setFoodLevel(200, 500, 350);
		} else {
			setFoodLevel(150, 500, 350);
		}
	}

	@Override
	public int getSellValue() {
		return 2000;
	}
	
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(60, "bites");
	}

	@Override
	public Egg getEgg() {
		Tyrannosaurus baby = new Tyrannosaurus(Maturity.BABY);
		baby.addSkill(DinoSkill.CAPTIVE_BRED);
		return new Egg(baby, 20000, 5000);
	}

	@Override
	public Corpse getCorpse() {
		return new Corpse("tyrannosaurus corpse", 2000);
	}
}
