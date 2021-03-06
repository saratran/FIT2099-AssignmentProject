package game.ground;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Menu;
import game.FoodSkill;
import game.action.BuyAction;
import game.action.ExecuteMultipleActions;
import game.action.SellAction;
import game.action.SellTaggedActorsAction;
import game.action.SubmenuAction;
import game.actor.Player;
import game.dinosaur.Dinosaur;
import game.actor.Trader;
import game.dinosaur.Maturity;
import game.dinosaur.Plesiosaur;
import game.dinosaur.Protoceratops;
import game.dinosaur.Pteranodon;
import game.dinosaur.Tyrannosaurus;
import game.dinosaur.Velociraptor;
import game.item.Boat;
import game.item.DinosaurTag;
import game.item.Egg;
import game.item.FoodItem;

/**
 * This class can interact with Actor that has Skill.Buyer to buy from and sell items to them.
 *
 */
public class Store extends Ground {
	private Menu menu = new Menu();
	
	/**
	 * Constructor
	 * 
	 * @param displayChar character displayed on the GameMap
	 */
	public Store(char displayChar) {
		super(displayChar);
		addSkill(GroundSkill.CANNOT_GROW_ON);
		addSkill(GroundSkill.LAND);
	}

	public Store() {
		this('S');
	}

	/**
	 * return list of BuyAction and SellAction for different items
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		Actions actions = new Actions();
		if (actor instanceof Trader) {
			createItemList().stream().filter(item -> item.isBuyable())
					.forEach(item -> actions.add(new BuyAction(item)));
			
			actor.getInventory().stream().filter(item -> item.isSellable())
					.forEach(item -> actions.add(new SellAction(item)));
			
			if (!actor.getTaggedActors().isEmpty()) {
				actions.add(new SellTaggedActorsAction(actor.getTaggedActors()));
			}
		}
		Actions returnedActions = new Actions();
		returnedActions.add(new SubmenuAction(menu, actions, "Visit store"));
		return returnedActions;
	}

	/**
	 * Modify this to add more items being sold at the Store. These items need to have overridden the method isBuyable() to return true.
	 * 
	 * @return list of items that the Store is selling
	 */
	private List<Item> createItemList() {
		List<Item> items = new ArrayList<Item>();
		items.add((new Protoceratops().getEgg()));
		items.add((new Velociraptor().getEgg()));
		items.add((new Plesiosaur().getEgg()));
		items.add((new Pteranodon().getEgg()));
		items.add((new Tyrannosaurus().getEgg()));
		items.add(new Boat());
		items.add(new FoodItem(FoodSkill.HERBIVORE, 10));
		items.add(new FoodItem(FoodSkill.CARNIVORE, 50));	
		items.add(new FoodItem(FoodSkill.MARINE, 100));
		items.add(new DinosaurTag());

		return items;
	}
}
