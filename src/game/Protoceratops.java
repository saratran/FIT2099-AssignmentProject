package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * A herbivorous dinosaur.
 *
 */
public class Protoceratops extends Dinosaur {

	/**
	 * Constructor. All Protoceratops are represented by a 'd' and have 100 hit
	 * points.
	 * 
	 * @param name the name of this Protoceratops
	 */
	public Protoceratops(String name) {
		super(name, 'd', 100);
		food_level = 30;
		HUNGRY_LEVEL = 15;
		MAX_FOOD_LEVEL = 50;
		food_grounds.add(new Tree()); // TODO: is this a good way to keep track of edible food?
		food_items.add(new Food("food",'f'));
		behaviours.add(new SeekFoodBehaviour());
		behaviours.add(new WanderBehaviour());
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions(new AttackAction(this));
		if (otherActor instanceof Player) {
			for (Item item : otherActor.getInventory()) {
				if (item.isFeedable() && this.isFood(item)) {
					actions.add(new FeedAction(item, this));
				}
			}
		}
		return actions;
	}

	/**
	 * Figure out what to do next.
	 * 
	 * FIXME: Protoceratops wanders around at random, or if no suitable MoveActions
	 * are available, it just stands there. That's boring.
	 * 
	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap,
	 *      Display)
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// actions is the actions got from inventory, surrounding actors and items
		super.playTurn(actions, lastAction, map, display);
		if (!this.die(map)) {
			for (Behaviour behaviour : behaviours) {
				Action action = behaviour.getAction(this, map);
				if (action != null)
					return action;
			}

		}
		return new DoNothingAction();
	}

}
