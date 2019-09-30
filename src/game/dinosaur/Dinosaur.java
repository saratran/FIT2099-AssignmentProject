package game.dinosaur;

import java.util.ArrayList;
import java.util.List;

//import edu.monash.fit2099.engine.Action;
//import edu.monash.fit2099.engine.Actions;
//import edu.monash.fit2099.engine.Actor;
//import edu.monash.fit2099.engine.Display;
//import edu.monash.fit2099.engine.DoNothingAction;
//import edu.monash.fit2099.engine.GameMap;
//import edu.monash.fit2099.engine.Ground;
//import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.*;
import game.FoodSkill;
import game.Species;
import game.action.FeedAction;
import game.actor.Player;
import game.behaviour.Behaviour;
import game.behaviour.SeekFoodBehaviour;
import game.behaviour.WanderBehaviour;
import game.dinosaur.Maturity;

public abstract class Dinosaur extends Actor {
	protected int age = 0;
	protected Species species;
	protected Maturity maturity;
	private int foodLevel;
	private int maxFoodLevel;
	private int hungryLevel;
	private List<Behaviour> behaviours = new ArrayList<Behaviour>();
	protected List<Item> foodItems = new ArrayList<Item>();
	protected List<Ground> foodGrounds = new ArrayList<Ground>();
	protected List<Actor> foodActors = new ArrayList<Actor>();
	protected List<FoodSkill> edibleFoodSkills = new ArrayList<FoodSkill>(); // List of food skills that the dino can eat

	public Dinosaur(String name, char displayChar, int hitPoints, Maturity maturity) {
		super(name, displayChar, hitPoints);
		addSkill(FoodSkill.CARNIVORE); // All dinosaurs are meat --> Velociraptor (and any carnivore dino) can eat and attack all other dino
		this.maturity = maturity;
		this.initFoodLevel();
		behaviours.add(new SeekFoodBehaviour());
		behaviours.add(new WanderBehaviour());
	}


	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		foodLevel--;
		if (isDead()) {
			this.die(map);
		} else {
			if (this.isHungry()) {
				System.out.println(name + " is hungry!");
			}
			for (Behaviour behaviour : behaviours) {
				Action action = behaviour.getAction(this, map);
				if (action != null)
					return action;
			}
		}
		return new DoNothingAction();
	}



	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		if(otherActor instanceof Player) {
			otherActor.getInventory().stream().filter(item -> item.isFeedable() && this.isFood(item)).forEach((item) ->{
				actions.add(new FeedAction(item, this));
			});;
		}
		return actions;
	}


	public void die(GameMap map) {
		for(Item item : itemsDroppedWhenDead()) {
			map.locationOf(this).addItem(item);
		}
		map.removeActor(this);
	}

	protected abstract List<Item> itemsDroppedWhenDead(); // Support adding multiple items to ground when dino dies

	public boolean isDead() {
		return (foodLevel <= 0);
	}

	public boolean isFood(Item item) {
		for (Item food_item : foodItems) {
			// This means the dino can either eat or not eat a class
			if (item.getClass().equals(food_item.getClass())) {
				return true;
			}
		}

		for (FoodSkill skill : edibleFoodSkills) {
			if (item.hasSkill(skill)) {
				return true;
			}
		}
		return false;
	}

	public boolean isFood(Ground ground) {
		for (Ground food_ground : foodGrounds) {
			// This means the dino can either eat or not eat a class
			if (ground.getClass().equals(food_ground.getClass())) {
				return true;
			}
		}
		for (FoodSkill skill : edibleFoodSkills) {
			if (ground.hasSkill(skill)) {
				return true;
			}
		}
		return false;
	}

	public boolean isFood(Actor actor) {
		for (Actor food_actor : foodActors) {
			// This means the dino can either eat or not eat a class
			if (actor.getClass().equals(food_actor.getClass())) {
				return true;
			}
		}

		for (FoodSkill skill : edibleFoodSkills) {
			if (actor.hasSkill(skill)) {
				return true;
			}
		}
		return false;
	}

	public boolean isHungry() {
		return (foodLevel <= hungryLevel);
	}

	public void addFoodValue(int food_value) {
		foodLevel += food_value;
		foodLevel = Math.min(foodLevel, maxFoodLevel); // capped at max
	}

	protected void initFoodLevel() {
		if (this.maturity == Maturity.ADULT) {
			switch (this.species) {
			case PROTOCERATOPS:
				setFoodLevel(30, 50, 15);
			case VELOCIRAPTOR:
				setFoodLevel(40, 100, 20);	
				break;
			}
		} else if (this.maturity == Maturity.BABY) { 
			switch (this.species) {
			case PROTOCERATOPS:
				setFoodLevel(10, 25, 15);
			case VELOCIRAPTOR:
				setFoodLevel(15, 40, 20);	
				break;
			}
		}
	}

	protected void setFoodLevel(int current, int max, int hungry) {
		foodLevel = current;
		maxFoodLevel = max;
		hungryLevel = hungry;
	}

	public Species getSpecies() {
		return species;
	}

	public void addBehaviour(Behaviour behaviour) {
		behaviours.add(behaviour);
	}

	public Maturity getMaturity() {
		return maturity;
	}


}
