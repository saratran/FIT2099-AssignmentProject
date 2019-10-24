package game.dinosaur;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.FoodSkill;
import game.Price;
import game.ground.Tree;
import game.item.Corpse;
import game.item.Egg;
import game.item.FoodItem;

/**
 * A herbivorous dinosaur.
 * 
 */
public class Protoceratops extends Dinosaur {

	/**
	 * Constructor. All Protoceratops are represented by a 'P' and have 100 hit
	 * points.
	 * 
	 * @param name the name of this Protoceratops
	 */
	public Protoceratops(String name, Maturity maturity) {
		super(name, 'P', 100, maturity, 1);		
		edibleFoodSkills.add(FoodSkill.HERBIVORE);
		nonEdibleFoodSkills.add(FoodSkill.NOT_HERBIVORE);
		layEggChance=0.03;
		addSkill(DinoSkill.LAND);
		
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
		items.add(new Corpse("protoceratops corpse", Price.ProtoceratopsCorpse.sellValue()));
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
		location.addItem(new Egg(new Protoceratops(Maturity.BABY), Price.ProtoceratopsEgg.buyValue(), Price.ProtoceratopsEgg.sellValue()));
	}

	@Override
	public int getSellValue() {
		return 100;
	}

}
