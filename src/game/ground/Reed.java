package game.ground;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.actor.Fish;
import game.dinosaur.DinoSkill;
import game.item.FishItem;

public class Reed extends Vegetation {
	
	public Reed() {
		super('|');
	}

	
	@Override
	public void tick(Location location) {
		super.tick(location);
		// Die if overcrowded
		if (neighbourCount(location, this.getClass()) >= 6) {
			location.setGround(eatenGround());
		} else if(Math.random() <= 0.2 && !location.containsAnActor()) {
//			location.addActor(new Fish());
			location.addItem(new FishItem());
		}
		
	}
	
	@Override
	public Ground eatenGround() {
		return new Water();
	}


	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasSkill(DinoSkill.MARINE);
	}


	@Override
	public boolean canItemEnter(Item item) {
		return item.hasSkill(DinoSkill.MARINE);
	}
	
	

}
