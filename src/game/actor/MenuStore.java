package game.actor;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;

public class MenuStore extends Action {
	private Menu menu;
	private Actions actions;
	private Display display;
	
	public MenuStore(Menu menu, Display display, Actions actions) {
		this.menu = menu;
		this.actions = actions;
		this.display = display;
	}
	@Override
	public String execute(Actor actor, GameMap map) {
		Action action = menu.showMenu(actor, actions, display);
		return action.execute(actor, map);
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Store menu";
	}

}
