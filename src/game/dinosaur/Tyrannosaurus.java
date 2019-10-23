package game.dinosaur;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.FoodSkill;
import game.Price;
import game.actor.ExitGameAction;
import game.item.Corpse;
import game.item.Egg;

public class Tyrannosaurus extends Dinosaur {

	public Tyrannosaurus(String name, Maturity maturity) {
		super(name, 'T', 100, maturity);		
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

	//	@Override
	//	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
	//		Action action =  super.playTurn(actions, lastAction, map, display);
	//		if ((maturity == Maturity.ADULT) & (this.hasSkill(DinoSkill.CAPTIVE_BRED))){
	//			return new ExitGameAction("A captive bred T-Rex has reached adulthood! You won!");
	//		} else {
	//		return action;
	//		}
	//	}

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
	protected List<Item> itemsDroppedWhenDead() {
		List<Item> items = new ArrayList<Item>();
		items.add(new Corpse("tyrannosaurus corpse", Price.TyrannosaurusCorpse.sellValue()));
		return items;
	}

	@Override
	protected void initFoodLevel() {
		if (this.maturity == Maturity.ADULT) {
			setFoodLevel(200, 500, 350);
		} else {
			setFoodLevel(40, 100, 50);
		}
	}

	@Override
	protected void layEgg(Location location) {
		Tyrannosaurus baby = new Tyrannosaurus(Maturity.BABY);
		baby.addSkill(DinoSkill.CAPTIVE_BRED);
		location.addItem(new Egg(baby, Price.TyrannosaurusEgg.buyValue(), Price.TyrannosaurusEgg.sellValue()));
	}


}
