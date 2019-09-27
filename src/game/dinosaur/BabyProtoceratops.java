package game.dinosaur;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.interfaces.BabyDinoInterface;
import game.action.GrowAction;

public class BabyProtoceratops extends Protoceratops implements BabyDinoInterface {

	public BabyProtoceratops(String name) {
		super(name, 'p');
		foodLevel = 20;

	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// TODO: repeated code for all baby dino --> Should I use default method in interface?
		if (canGrow()) {
			return new GrowAction(growInto());
		}
		return super.playTurn(actions, lastAction, map, display);
	}
	
	@Override
	protected boolean isAdult() {
		return false;
	}

	// TODO: interface method has to be public?
	public boolean canGrow() {
		return age >= 10;
	}

	public Actor growInto() {
		return new Protoceratops("Protoceratops");
	}
}
