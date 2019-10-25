package game.dinosaur;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.FoodSkill;
import game.Price;
import game.item.Corpse;
import game.item.Egg;

public class Plesiosaur extends Dinosaur {
	// TODO: change sell, buy, hp, food level
		
	public Plesiosaur(String name, Maturity maturity) {
		super(name, 'L', 175, maturity, 1);		
		edibleFoodSkills.add(FoodSkill.CARNIVORE);
		edibleFoodSkills.add(FoodSkill.MARINE);
		addSkill(DinoSkill.MARINE);
	}

	public Plesiosaur(String name) {
		this(name, Maturity.ADULT);	
	}

	public Plesiosaur(Maturity maturity) {
		this("Plesiosaur", maturity);
	}

	public Plesiosaur() {
		this("Plesiosaur");
	}
	
	@Override
	protected List<Item> itemsDroppedWhenDead() {
		List<Item> items = new ArrayList<Item>();
		items.add(new Corpse("Plesiosaur corpse", Price.PlesiosaurCorpse.sellValue()));
		return items;
	}
	
	@Override
	protected void initFoodLevel() {
		if (this.maturity == Maturity.ADULT) {
			setFoodLevel(40, 100, 30);
		} else {
			setFoodLevel(10, 40, 30);
		}
	}
	
	@Override
	protected void layEgg(Location location) {
		location.addItem(new Egg(new Plesiosaur(Maturity.BABY), Price.PlesiosaurEgg.buyValue(), Price.PlesiosaurEgg.sellValue()));
	}
	
	@Override
	public int getSellValue() {
		return 400;
	}

}
