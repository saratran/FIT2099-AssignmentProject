package game.actor;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.FoodSkill;
import game.behaviour.Behaviour;
import game.behaviour.WanderBehaviour;
import game.dinosaur.DinoSkill;

public class Fish extends Actor {
	private List<Behaviour> behaviours = new ArrayList<Behaviour>();

	private int age = 0;
	
	public Fish() {
		super("fish", 'f', 1);
		addSkill(DinoSkill.MARINE);
		addSkill(FoodSkill.CARNIVORE);
		behaviours.add(new WanderBehaviour()); //TODO: fish wandering is going to be printed
	}

	
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		age++;
		if(age>=20) {
			map.removeActor(this);
		}else {
			for (Behaviour behaviour : behaviours) {
				Action action = behaviour.getAction(this, map);
				if (action != null)
					return action;
			}
		}
		
		return new DoNothingAction();
	}


}
