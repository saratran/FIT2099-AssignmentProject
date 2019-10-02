package game.dinosaur;

import java.util.ArrayList;
import java.util.List;


import edu.monash.fit2099.engine.*;
import game.FoodSkill;
import game.action.FeedAction;
import game.actor.Player;
import game.behaviour.Behaviour;
import game.behaviour.SeekFoodBehaviour;
import game.behaviour.WanderBehaviour;
import game.dinosaur.Maturity;

public abstract class Dinosaur extends Consumer {
	protected int age = 0;
	protected Maturity maturity;
	protected String adultName;

	private List<Behaviour> behaviours = new ArrayList<Behaviour>();
	
	public Dinosaur(String name, char displayChar, int hitPoints, Maturity maturity) {
		super(name, displayChar, hitPoints);
		adultName = name;
		if (maturity == Maturity.BABY) {
			this.displayChar = Character.toLowerCase(displayChar);
			this.name = "Baby " + adultName;
		}

		addSkill(FoodSkill.CARNIVORE); // All dinosaurs are meat --> Velociraptor (and any carnivore dino) can eat and
		// attack all other dino
		this.maturity = maturity;
		initFoodLevel();
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
			if (this.isWellFed()) {
				this.layEggAttempt(map.locationOf(this));
			}
			if (this.isMatureAge()) {
				this.growOlder();
			}
			age++;
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
		if (otherActor instanceof Player) {
			otherActor.getInventory().stream().filter(item -> item.isFeedable() && this.isFood(item))
			.forEach((item) -> {
				actions.add(new FeedAction(item, this));
			});
			;
		}
		return actions;
	}

	private void die(GameMap map) {
		for (Item item : itemsDroppedWhenDead()) {
			map.locationOf(this).addItem(item);
		}
		map.removeActor(this);
	}

	protected abstract List<Item> itemsDroppedWhenDead(); // Support adding multiple items to ground when dino dies

	public boolean isDead() {
		return (!this.isConscious() || this.foodLevel <= 0);
	}

	public void addBehaviour(Behaviour behaviour) {
		behaviours.add(behaviour);
	}

	public Maturity getMaturity() {
		return maturity;
	}

	private void layEggAttempt(Location location) {
		if (maturity == Maturity.ADULT && Math.random() < 0.02) {
			this.layEgg(location);
			name = adultName;
			System.out.println(name + " laid an egg!");
		}
	}

	protected abstract void layEgg(Location location);
	
	private boolean isMatureAge() {
		return (age > 20);
	}

	protected void growOlder() {
		if (maturity == Maturity.BABY) {
			this.maturity = Maturity.ADULT;
			this.displayChar = Character.toUpperCase(displayChar);
			System.out.println(name + " has grown!");
		}
	}
	
	public boolean isHungry() {
		return (foodLevel <= hungryLevel);
	}

	public boolean isWellFed() {
		return (foodLevel <= (hungryLevel + 5));
	}

	@Override
	protected abstract void initFoodLevel();

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "bites");
	}
	
	
}
