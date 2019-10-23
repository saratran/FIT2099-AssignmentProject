package game.ground;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Menu;
import game.FoodSkill;
import game.Price;
import game.action.BuyAction;
import game.action.ExecuteMultipleActions;
import game.action.SellAction;
import game.action.SellTaggedActorsAction;
import game.actor.MenuStore;
import game.actor.Player;
import game.dinosaur.Dinosaur;
import game.actor.Trader;
import game.dinosaur.Maturity;
import game.dinosaur.Plesiosaur;
import game.dinosaur.Protoceratops;
import game.dinosaur.Velociraptor;
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

	/**
	 * return list of BuyAction and SellAction for different items
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction, Display display) {
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
		Actions returnActions = new Actions();
		returnActions.add(new MenuStore(menu, display, actions));
		return returnActions;
	}

	/**
	 * Modify this to add more items being sold at the Store. These items need to have overridden the method isBuyable() to return true.
	 * 
	 * @return list of items that the Store is selling
	 */
	private List<Item> createItemList() {
		List<Item> items = new ArrayList<Item>();
		items.add(new Egg(new Protoceratops(Maturity.BABY), Price.ProtoceratopsEgg.buyValue(),
				Price.ProtoceratopsEgg.sellValue()));
		items.add(new Egg(new Velociraptor(Maturity.BABY), Price.VelociraptorEgg.buyValue(),
				Price.VelociraptorEgg.sellValue()));
		// TODO: set correct value
		items.add(new Egg(new Plesiosaur(Maturity.BABY), Price.VelociraptorEgg.buyValue(),
				Price.VelociraptorEgg.sellValue()));
		
		items.add(new FoodItem(FoodSkill.HERBIVORE, Price.HerbivoreFoodItem.buyValue()));
		items.add(new FoodItem(FoodSkill.CARNIVORE, Price.CarnivoreFoodItem.buyValue()));
	
		// TODO: set correct value, may consider combining FoodSkill and DinoSkill
		items.add(new FoodItem(FoodSkill.MARINE, Price.CarnivoreFoodItem.buyValue()));
		items.add(new DinosaurTag());

		return items;
	}
}
