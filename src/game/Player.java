package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;

/**
 * Class representing the Player.
 */
public class Player extends Actor {
	private int money = 0;
	private Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		actions.add(new SleepAction());
		return menu.showMenu(this, actions, display);
	}
	
	private class SleepAction extends Action {
		// TODO: Copied from conwayslife (is this ok?)
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
	
	public void addMoney(int value) {
		//TODO: needs exception?
//		if(value < 0) {
//			throw new Exception("Cannot add negative value");
//		}
		
		money += value;
	}
	
	public void deductMoney(int value) {
//		if(value < 0) {
//			throw new Exception("Cannot remove negative value");
//		}
		
		money -= value;
	}
	
	public int getMoney() {
		return money;
	}
	

}
