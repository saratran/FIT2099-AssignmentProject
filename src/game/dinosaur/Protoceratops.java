package game.dinosaur;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.FoodSkill;
import game.Species;
import game.ground.Tree;
import game.item.Corpse;
import game.item.Egg;
import game.item.FoodItem;

/**
 * A herbivorous dinosaur.
 *
 */
public class Protoceratops extends Dinosaur {
	
	private char adultDisplayChar = 'P';
	private char babyDisplayChar= 'p';
	/**
	 * Constructor. All Protoceratops are represented by a 'd' and have 100 hit
	 * points.
	 * 
	 * @param name the name of this Protoceratops
	 */
	public Protoceratops(String name, Maturity maturity) {
		super(name, 'P', 100, maturity);		
		species = Species.PROTOCERATOPS;
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
		foodItems.add(new FoodItem(FoodSkill.HERBIVORE, 20));
		edibleFoodSkills.add(FoodSkill.HERBIVORE);
		
	}
	
	public Protoceratops(String name) {
		this(name, Maturity.ADULT);	
	}
	
	public Protoceratops(Maturity maturity) {
		this("Protoceratops", maturity);
	}
	
	public Protoceratops() {
		this("Protoceratops");
	}
		
	@Override
	protected List<Item> itemsDroppedWhenDead() {
		List<Item> items = new ArrayList<Item>();
		items.add(new Corpse("Proceratops corpse", 'c', Species.PROTOCERATOPS));
		return items;
	}

	@Override
	protected void initFoodLevel() {
		if (this.maturity == Maturity.ADULT) {
			setFoodLevel(30, 50, 15);
		} else {
			setFoodLevel(10, 25, 15);
		}
	}

	@Override
	protected void layEgg(Location location) {
		location.addItem(new Egg(new Protoceratops(Maturity.BABY)));
	}

}
