package game.dinosaur;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import game.FoodSkill;
import game.item.CarnivoreFoodItem;
import game.item.Corpse;
import game.item.ProceratopsEgg;
import game.item.VelociraptorEgg;

public class Velociraptor extends Dinosaur {

	public Velociraptor(String name) {
		this(name, 'V');
	}
	
	public Velociraptor(String name, char displayChar) {
		super(name, displayChar, 100);
		edibleFoodSkills.add(FoodSkill.CARNIVORE); // TODO: May be a good idea to have CarnivoreDino and HerbivoreDino base classes
		
		initFoodLevel(30, 100, 30);
		
//		foodActors.add(new Protoceratops("proceratops")); 
//		foodItems.add(new CarnivoreFoodItem("food",'f'));
//		foodItems.add(new ProceratopsEgg("egg",'e'));
//		foodItems.add(new Corpse("corpse", 'c', Species.PROTOCERATOPS));
	}
	
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(30, "bites");
	}


	@Override
	protected List<Item> itemsDroppedWhenDead() {
		List<Item> items = new ArrayList<Item>();
		items.add(new Corpse("Velociraptor corpse", 'c'));
		return items;
	}
	
	@Override
	protected Item getEgg() {
		return new VelociraptorEgg("Velociraptor egg", 'e');
	}
	
}
