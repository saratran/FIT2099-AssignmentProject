package game.dinosaur;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Item;
import game.FoodSkill;
import game.Species;
import game.item.Corpse;

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
	public Velociraptor(String name, Maturity maturity) {
		super(name, 'v', 175, maturity);		
		species = Species.VELOCIRAPTOR;

		/*TODO: is this a good way to keep track of edible food?
		 * - Pros: have fine-grain control of which object is edible
		 * - Cons: lose ability to set an abstract class as edible (ie like Protoceratops can eat all Vegetation)
		 * - Alternatives:
		 * 		+ Let food objects have skills like "Skill.herbivoreFood" or "Skill.carnivoreFood"
		 * 		+ Database?
		 * 
		 * - Current solution: combining both the usage of Skill (for broader control) and lists of edible food (for finer control)
		 */
		edibleFoodSkills.add(FoodSkill.CARNIVORE);

	}

	/*TODO is this okay? Feels as though you'd need to add this version of the constructor 
	 * to all kinds of Dinosaurs. Should probably implement this in the parent class somehow?
	 */
	public Velociraptor(String name) {
		this(name, Maturity.ADULT);	
	}

	public Velociraptor(Maturity maturity) {
		this("Velociraptor", maturity);
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
