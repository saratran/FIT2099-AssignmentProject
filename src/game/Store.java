package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class Store extends Ground {
	private List<Item> items = new ArrayList<Item>();
	
	public Store(char displayChar) {
		super(displayChar);
		items.add(new Egg("Protoceratops egg", 'p', Species.PROTOCERATOPS));
	}

	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		Actions actions = new Actions();
		if(actor instanceof Player) {
			for (Item item : items) {
				actions.add(new BuyAction(item));
			}
			for (Item item : actor.getInventory()) {
				if (item.isSellable()) {
					actions.add(new SellAction(item));
				}
			}
		}
		
		return actions;
	}

	@Override
	public void tick(Location location) {
		super.tick(location);
	}
	
	

}
