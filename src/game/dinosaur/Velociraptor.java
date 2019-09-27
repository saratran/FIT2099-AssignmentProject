package game.dinosaur;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import game.FoodSkill;
import game.Species;
import game.item.CarnivoreFoodItem;
import game.item.Corpse;

public class Velociraptor extends Dinosaur {

	public Velociraptor(String name) {
		super(name, 'V', 200);
		species = Species.VELOCIRAPTOR;
		edibleFoodSkills.add(FoodSkill.CARNIVORE); // TODO: May be a good idea to have CarnivoreDino and HerbivoreDino base classes
		
		initFoodLevel(30, 100, 30);
		initHealthLevel(100, 100, 50);
		
//		foodActors.add(new Protoceratops("proceratops")); 
//		foodItems.add(new CarnivoreFoodItem("food",'f'));
//		foodItems.add(new ProceratopsEgg("egg",'e'));
//		foodItems.add(new Corpse("corpse", 'c', Species.PROTOCERATOPS));
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "bites");
	}


	@Override
	protected List<Item> itemsDroppedWhenDead() {
		List<Item> items = new ArrayList<Item>();
		items.add(new Corpse("Velociraptor corpse", 'c', Species.VELOCIRAPTOR));
		return items;
	}
	
}
