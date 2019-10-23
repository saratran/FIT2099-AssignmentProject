package game.dinosaur;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.*;
import game.FoodSkill;
import game.Skill;
import game.action.AttackAction;
import game.action.FeedAction;
import game.action.PlaceTagAction;
import game.actor.Player;
import game.behaviour.Behaviour;
import game.behaviour.SeekFoodBehaviour;
import game.behaviour.WanderBehaviour;
import game.dinosaur.Maturity;
import game.item.DinosaurTag;

/**
 * An abstraction for dinosaurs. Defines behaviours of dinosaurs
 * and how their turns take place. 	Also generalises breeding
 * behaviours as well as feeding.
 *
 */
public abstract class Dinosaur extends Consumer {
	protected int age = 0;
	protected Maturity maturity;
	protected String adultName;
	private List<Behaviour> behaviours = new ArrayList<Behaviour>();

	protected double layEggChance = 0.02;
	/**
	 * Constructor.
	 * 
	 * @param name	the name of the dinosaur.
	 * @param displayChar	the display character seen on the map.
	 * @param hitPoints	the starting hitpoints of the Dinosaur.
	 * @param maturity	the maturity of the Dinosaur: adult or baby.
	 * @param speed TODO
	 */
	public Dinosaur(String name, char displayChar, int hitPoints, Maturity maturity, int speed) {
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
		behaviours.add(new WanderBehaviour(speed));
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		foodLevel--;
		if (!isConscious()) {
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
		// Would be better if can get player actor from World
		if (otherActor instanceof Player) {
			// Add FeedAction for relevant items
			otherActor.getInventory().stream().filter(item -> item.isFeedable() && this.isFood(item))
			.forEach((item) -> {
				actions.add(new FeedAction(item, this));
			});
			
			// Add PlaceTagAction for relevant items
			if (this.maturity == Maturity.ADULT && this.isWellFed() && !hasSkill(Skill.TAGGED)) {
				for (Item item : otherActor.getInventory()) {
					// PENDING: may refactor this if more other Items can be placed on Actor
					if (item instanceof DinosaurTag) {
						actions.add(new PlaceTagAction(item, this));
						break;
					}
				}
			}
			
			// Player can attack dinosaurs any time
			actions.add(new AttackAction(this));
		}
		return actions;
	}

	/**
	 * Kill this dinosaur, dropping all of its droppable
	 * items in the process.
	 * 
	 * @param map	the map the dinosaur is on.
	 */
	public void die(GameMap map) {
		for (Item item : itemsDroppedWhenDead()) {
			map.locationOf(this).addItem(item);
		}
		map.removeActor(this);
		System.out.println(this + " dies");
	}

	/**
	 * Used to define the items that will be dropped
	 * upon the death of the dinosaur.
	 * 
	 * @return a List of the items that will be dropped upon death.
	 */
	protected abstract List<Item> itemsDroppedWhenDead(); // Support adding multiple items to ground when dino dies
	
	@Override
	protected abstract void initFoodLevel();
	

	@Override
	public boolean isConscious() {
		return (super.isConscious() && this.foodLevel > 0);
	}

	/**
	 * Adds a behaviour to the dinosaur.
	 * 
	 * @param behaviour	the behaviour that is to be added.
	 */
	public void addBehaviour(Behaviour behaviour) {
		behaviours.add(behaviour);
	}

	/**
	 * Returns the maturity of the dinosaur.
	 * 
	 * @return Returns the maturity of the dinosaur.
	 */
	public Maturity getMaturity() {
		return maturity;
	}

	/**
	 * Attemps to lay an egg at its current location if and only if it the dinosaur
	 * is an adult and the random chance of laying an egg is met.
	 * 
	 * @param location	the location that the egg will be laid.
	 */
	private void layEggAttempt(Location location) {
		if (maturity == Maturity.ADULT && Math.random() < this.layEggChance) {
			this.layEgg(location);
			name = adultName;
			System.out.println(name + " laid an egg!");
		}
	}

	/**
	 * Lays an egg at a specified location.
	 * 
	 * @param location the location that the egg will be laid.
	 */
	protected abstract void layEgg(Location location);

	/**
	 * Returns true if and only if the dinosaur is old enough to 
	 * mature in age, that is become an adult.
	 * 
	 * @return	Returns true if and only if the dinosaur's age is greater than 20.
	 */
	private boolean isMatureAge() {
		return (age > 30);
	}

	/**
	 * Changes the maturity of the dinosaur to adult if and only if
	 * the maturity of the dinosaur was baby.
	 * @return TODO
	 */
	protected boolean growOlder() {
		if (maturity == Maturity.BABY) {
			this.maturity = Maturity.ADULT;
			this.displayChar = Character.toUpperCase(displayChar);
			System.out.println(name + " has grown!");
			name = adultName;
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return returns true if and only if the current food level is less than 
	 * or equal to the hunger level of the dinosaur.
	 */
	public boolean isHungry() {
		return (foodLevel <= hungryLevel);
	}

	/**
	 * 
	 * @return returns true if the current food level of the dinosaur is
	 * greater than or equal to 5 greater than its hunger level.
	 */
	public boolean isWellFed() {
		return (foodLevel >= (hungryLevel + 5));
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(30, "bites");
	}

}
