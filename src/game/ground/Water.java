package game.ground;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.dinosaur.DinoSkill;

public class Water extends GrowableGround {

	public Water() {
		super('~');
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasSkill(DinoSkill.MARINE);
	}

	@Override
	public void tick(Location location) {
		super.tick(location);
		if(neighbourCount(location, GroundSkill.LAND) > 0) {
			growCurrentLocation(location, 0.1, new Reed());
		}else if (neighbourCount(location, Reed.class) > 0) {
			growCurrentLocation(location, 0.05, new Reed());
		}
	}


	
}
