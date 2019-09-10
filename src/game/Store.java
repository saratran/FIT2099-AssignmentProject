package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class Store extends Actor {

	public Store() {
		super("Store", 'S', 100);
		addItemToInventory(new PortableDinoItem("egg", 'e'));
		addItemToInventory(new PortableDinoItem("food", 'f'));
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		return new DoNothingAction();
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		for(Item item : this.getInventory()) {
			actions.add(new StoreSellAction(item));
		}
		return actions;
	}
}
