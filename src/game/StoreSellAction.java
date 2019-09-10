package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/*
 * TODO:
 * - Need to take money into account
 * - Potential problem(?): after buying an item, menu shows option to drop it --> can get clutter if bought many item
 */
public class StoreSellAction extends Action {
	protected Item item;
	
	public StoreSellAction(Item item) {
		this.item = item;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.addItemToInventory(new PortableDinoItem(item.toString(), item.getDisplayChar()));
		return "Player bought " + item.toString();
	}

	@Override
	public String menuDescription(Actor actor) {

		return "Buy " + item.toString();
	}

}
