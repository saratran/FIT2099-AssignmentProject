package game.ground;

public class Grass extends Vegetation {

	public Grass() {
		super(',');
	}

	@Override
	public int getFoodValue() {
		return 5;
	}

	
}
