package game.action;

import java.util.Iterator;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * Action for executing multiple actions at once.
 * 
 */
public class ExecuteMultipleActions extends Action {

	private List<Action> actions;
	private String executeDescription;
	private String menuDescription;
	
	/**
	 * Constructor.
	 * 
	 * @param actions	The list of actions that are to be executed.
	 * @param menuDescription	The menu description for the option of executing these actions.
	 * @param executeDescription The description given to the player when the actions are executed.
	 */
	public ExecuteMultipleActions(List<Action> actions, String menuDescription, String executeDescription) {
		this.actions = actions;
		this.menuDescription = menuDescription;
		this.executeDescription = executeDescription;
	}
	@Override
	public String execute(Actor actor, GameMap map) {
		Iterator<Action> iterator = actions.iterator();
		while (iterator.hasNext()) {
			Action action = iterator.next();
			action.execute(actor, map);
		}
		return this.menuDescription;
	}

	@Override
	public String menuDescription(Actor actor) {
		return this.executeDescription;
	}

}
