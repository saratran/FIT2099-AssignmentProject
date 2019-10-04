package game.dinosaur;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.FoodSkill;
import game.Price;
import game.item.Corpse;
import game.item.Egg;
import game.item.FoodItem;

/**
 * A carnivorous dinosaur.
 */
public class Velociraptor extends Dinosaur {
	/**
	 * Constructor. All Velociraptors are represented by a 'v' and have 175 hit
	 * points.
	 * 
	 * @param name the name of this Velociraptor
	 */
	public Velociraptor(String name, Maturity maturity) {
		super(name, 'V', 175, maturity);		
		edibleFoodSkills.add(FoodSkill.CARNIVORE);

	}

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
		items.add(new Corpse("Velociraptor corpse", Price.VelociraptorCorpse.getSellValue()));
		return items;
	}
	
	@Override
	protected void initFoodLevel() {
		if (this.maturity == Maturity.ADULT) {
			setFoodLevel(40, 100, 20);
		} else {
			setFoodLevel(15, 40, 20);
		}
	}
	
	@Override
	protected void layEgg(Location location) {
		location.addItem(new Egg(new Velociraptor(Maturity.BABY), Price.VelociraptorEgg.getBuyValue(), Price.VelociraptorEgg.getSellValue()));
	}
	
	@Override
	public int getSellValue() {
		return 400;
	}

}
