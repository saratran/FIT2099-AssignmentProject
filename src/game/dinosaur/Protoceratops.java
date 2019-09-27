package game.dinosaur;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.FoodSkill;
import game.Species;
import game.action.AttackAction;
import game.action.FeedAction;
import game.actor.Player;
import game.behaviour.Behaviour;
import game.behaviour.SeekFoodBehaviour;
import game.behaviour.WanderBehaviour;
import game.ground.Tree;
import game.item.Corpse;
import game.item.FoodItem;
import game.item.HerbivoreFoodItem;

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
		super(name, 'P', 100);		
		species = Species.PROTOCERATOPS;

		// TODO: good way to initialise values?
		initFoodLevel(30, 50, 15);
		initHealthLevel(100, 100, 50);
		
		/*TODO: is this a good way to keep track of edible food?
		 * - Pros: have fine-grain control of which object is edible
		 * - Cons: lose ability to set an abstract class as edible (ie like Protoceratops can eat all Vegetation)
		 * - Alternatives:
		 * 		+ Let food objects have skills like "Skill.herbivoreFood" or "Skill.carnivoreFood"
		 * 		+ Database?
		 * 
		 * - Current solution: combining both the usage of Skill (for broader control) and lists of edible food (for finer control)
		 */
		foodGrounds.add(new Tree()); 
		foodItems.add(new HerbivoreFoodItem("food",'f'));
		
	}


	@Override
	protected List<Item> itemsDroppedWhenDead() {
		List<Item> items = new ArrayList<Item>();
		items.add(new Corpse("Proceratops corpse", 'c', Species.PROTOCERATOPS));
		return items;
	}

}
