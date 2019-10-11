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
import game.FoodSkill;
import game.Price;
import game.action.BuyAction;
import game.action.ExecuteMultipleActions;
import game.action.SellAction;
import game.action.SellTaggedActorsAction;
import game.actor.Player;
import game.dinosaur.Dinosaur;
import game.actor.Trader;
import game.dinosaur.Maturity;
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

		return actions;
	}

	/**
	 * Modify this to add more items being sold at the Store. These items need to have overridden the method isBuyable() to return true.
	 * 
	 * @return list of items that the Store is selling
	 */
	private List<Item> createItemList() {
		List<Item> items = new ArrayList<Item>();
		items.add(new Egg(new Protoceratops(Maturity.BABY), Price.ProtoceratopsEgg.getBuyValue(),
				Price.ProtoceratopsEgg.getSellValue()));
		items.add(new Egg(new Velociraptor(Maturity.BABY), Price.VelociraptorEgg.getBuyValue(),
				Price.VelociraptorEgg.getSellValue()));
		items.add(new FoodItem(FoodSkill.HERBIVORE, Price.HerbivoreFoodItem.getBuyValue()));
		items.add(new FoodItem(FoodSkill.CARNIVORE, Price.CarnivoreFoodItem.getBuyValue()));
		items.add(new DinosaurTag());

		return items;
	}
}
