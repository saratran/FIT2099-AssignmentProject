package game.behaviour;

import edu.monash.fit2099.engine.*;

/**
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a target Actor.
 * 
 */
public class FollowBehaviour implements Behaviour {

	private Actor target;
	private int speed;

	/**
	 * Constructor.
	 * 
	 * @param subject the Actor to follow
	 * @param speed   TODO
	 */
	public FollowBehaviour(Actor subject, int speed) {
		this.target = subject;
		this.speed = speed;
	}

	/**
	 * return a MoveActorAction to move the actor nearer to the target Actor
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if (!map.contains(target) || !map.contains(actor))
			return null;
		;
		return (new ToLocationBehaviour(map.locationOf(target), speed)).getAction(actor, map);
	}

}