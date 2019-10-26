package game.actor;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import game.FoodSkill;
import game.Skill;
import game.dinosaur.DinoSkill;
import game.dinosaur.Dinosaur;

/**
 * Class representing the Player.
 * 
 */
public class Player extends Trader {
	private Menu menu = new Menu();
	private List<Actor> taggedActors = new ArrayList<Actor>();
	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		addSkill(FoodSkill.NOT_HERBIVORE);
		addSkill(DinoSkill.LAND);
		addSkill(FoodSkill.CARNIVORE);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if(!isConscious()) {
			return new ExitGameAction("Player is dead");
		}
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		actions.add(new SleepAction());
		actions.add(new ExitGameAction("Game exited"));
		return menu.showMenu(this, actions, display);
	}
	
	private class SleepAction extends Action {
		private int sleepTime = 100;

		@Override
		public String execute(Actor actor, GameMap map) {
			sleepTime--;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
			return actor + " is sleeping.";
		}

		@Override
		public String menuDescription(Actor actor) {
			return "Sleep a while";
		}

		@Override
		public Action getNextAction() {
			if (sleepTime > 0)
				return this;

			return null;
		}
	}

	public void addTaggedActor(Actor actor) {
		this.taggedActors.add(actor);
	}

	public List<Actor> getTaggedActors() {
		return this.taggedActors;
	}

//	@Override
//	public boolean isConscious() {
//		return false;
//	}
	
	
	
}
