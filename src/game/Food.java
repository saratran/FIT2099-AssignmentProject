package game;

public class Food extends PortableDinoItem {

	public Food(String name, char displayChar) {
		super(name, displayChar);
		foodValue = 100;
		
	}

	@Override
	public boolean isFeedable() {
		return true;
	}
	
	
}
