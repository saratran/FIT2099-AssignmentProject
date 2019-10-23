package game.dinosaur;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.FoodSkill;
import game.Price;
import game.item.Corpse;
import game.item.Egg;

public class Pteranodon extends Dinosaur {

	public Pteranodon(String name, Maturity maturity) {
		super(name, 'W', 100, maturity, 2);		
		edibleFoodSkills.add(FoodSkill.CARNIVORE);
		layEggChance=0.02;
		addSkill(DinoSkill.LAND);
		addSkill(DinoSkill.MARINE);
		
	}
	
	public Pteranodon(String name) {
		this(name, Maturity.ADULT);	
	}
	
	public Pteranodon(Maturity maturity) {
		this("Pteranodons", maturity);
	}
	
	public Pteranodon() {
		this("Pteranodons");
	}

	@Override
	protected List<Item> itemsDroppedWhenDead() {
		List<Item> items = new ArrayList<Item>();
		items.add(new Corpse("pteranodons corpse", Price.PlesiosaurCorpse.sellValue()));
		return items;
	}

	@Override
	protected void layEgg(Location location) {
		location.addItem(new Egg(new Pteranodon(Maturity.BABY), Price.PlesiosaurEgg.buyValue(), Price.PlesiosaurEgg.sellValue()));
	}

	@Override
	protected void initFoodLevel() {
		if (this.maturity == Maturity.ADULT) {
			setFoodLevel(40, 100, 30);
		} else {
			setFoodLevel(10, 40, 30);
		}
	}

}
