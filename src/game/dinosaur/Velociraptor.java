package game.dinosaur;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Item;
import game.FoodSkill;
import game.Species;
import game.ground.Tree;
import game.item.Corpse;
import game.item.HerbivoreFoodItem;

/**
 * A carnivorous dinosaur.
 *
 */
public class Velociraptor extends Dinosaur {
	/**
	 * Constructor. All Velociraptors are represented by a 'v' and have 175 hit
	 * points.
	 * 
	 * @param name the name of this Velociraptor
	 */
	public Velociraptor(String name) {
		super(name, 'v', 175);		
		species = Species.VELOCIRAPTOR;

		// TODO: good way to initialise values?
		initFoodLevel(40, 100, 30);
		
		/*TODO: is this a good way to keep track of edible food?
		 * - Pros: have fine-grain control of which object is edible
		 * - Cons: lose ability to set an abstract class as edible (ie like Protoceratops can eat all Vegetation)
		 * - Alternatives:
		 * 		+ Let food objects have skills like "Skill.herbivoreFood" or "Skill.carnivoreFood"
		 * 		+ Database?
		 * 
		 * - Current solution: combining both the usage of Skill (for broader control) and lists of edible food (for finer control)
		 */
		foodItems.add(new HerbivoreFoodItem("food",'f'));
		edibleFoodSkills.add(FoodSkill.CARNIVORE);
		
	}
	
	public Velociraptor() {
		this("Velociraptor");
	}


	@Override
	protected List<Item> itemsDroppedWhenDead() {
		List<Item> items = new ArrayList<Item>();
		items.add(new Corpse("Velociraptor corpse", 'c', Species.VELOCIRAPTOR));
		return items;
	}

}
