package game.ground;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.interfaces.BuyableInterface;
import edu.monash.fit2099.interfaces.SellableInterface;
import game.Skill;
import game.Species;
import game.action.BuyAction;
import game.action.SellAction;
import game.actor.Player;
import game.dinosaur.Protoceratops;
import game.item.CarnivoreFoodItem;
import game.item.DinosaurTag;
import game.item.Egg;
import game.item.HerbivoreFoodItem;

/**
 * This class can interact with the Player by buying from and selling items to them.
 * @author Sara Tran
 *
 */
public class Store extends Ground {
	
	/**
	 * Constructor
	 * @param displayChar character displayed on the GameMap
	 */
	public Store(char displayChar) {
		super(displayChar);
	}

	/**
	 * return list of BuyAction and SellAction for different items
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		Actions actions = new Actions();
		// Don't want a Dinosaur to buy from and sell to the Store ;)
		// TODO: may replace with Buyer interface or Skill.BUYER
		if(actor.hasSkill(Skill.BUYER)) {
			for (BuyableInterface item : createItemList()) {
				actions.add(new BuyAction(item));
			}
			for (Item item : actor.getInventory()) {
				// TODO: not sure
				if (item instanceof SellableInterface) {
					actions.add(new SellAction((SellableInterface) item));
				}
			}
		}
		
		return actions;
	}

	/**
	 * Modify this to add more items being sold at the Store
	 * @return list of items that the Store is selling
	 */
	private List<BuyableInterface> createItemList() {
		List<BuyableInterface> items = new ArrayList<BuyableInterface>();
		items.add(new Egg(new Protoceratops()));
//		items.add(new Egg(new Velociraptor())); // TODO: implement Velociraptor
		items.add(new HerbivoreFoodItem());
		items.add(new CarnivoreFoodItem());
		items.add(new DinosaurTag("Dinosaur tag",'-'));

		return items;
	}
}
