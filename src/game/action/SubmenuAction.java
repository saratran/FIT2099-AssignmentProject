package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;

public class SubmenuAction extends Action {
	private Menu menu;
	private Actions actions;
	private Display display = new Display();
	private String description;
	
	public SubmenuAction(Menu menu, Actions actions, String description) {
		this.menu = menu;
		this.actions = actions;
		this.description = description;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		Action action = menu.showMenu(actor, actions, display);
		return action.execute(actor, map);
	}

	@Override
	public String menuDescription(Actor actor) {
		return description;
	}

}
