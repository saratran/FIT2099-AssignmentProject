package game.dinosaur;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.interfaces.BabyDinoInterface;
import game.action.GrowAction;

public class BabyVelociraptor extends Velociraptor implements BabyDinoInterface {

	public BabyVelociraptor(String name) {
		super(name, 'v');
		foodLevel = 20; // TODO: hard code food level :/
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
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(20, "bites"); // weaker weapon
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
		// TODO: "dirty" copy, not copying over attributes to adult dino
		return new Velociraptor("Velociraptor");
	}
	
	

}
