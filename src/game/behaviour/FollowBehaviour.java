package game.behaviour;

import edu.monash.fit2099.engine.*;

/**
 * A class that figures out a MoveAction that will move the actor one step 
 * closer to a target Actor.
 * 
 */
public class FollowBehaviour implements Behaviour {

	private Actor target;

	/**
	 * Constructor.
	 * 
	 * @param subject the Actor to follow
	 */
	public FollowBehaviour(Actor subject) {
		this.target = subject;
	}
	
	/**
	 * return a MoveActorAction to move the actor nearer to the target Actor
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if(!map.contains(target) || !map.contains(actor))
			return null;
		;
		return (new ToLocationBehaviour(map.locationOf(target))).getAction(actor, map);
	}

}