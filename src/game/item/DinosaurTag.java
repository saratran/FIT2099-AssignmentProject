package game.item;


public class DinosaurTag extends PortableDinoItem {

	public DinosaurTag(String name, char displayChar) {
		super(name, displayChar);
	}

	@Override
	public int getBuyValue() {
		return 0;
	}

	@Override
	public boolean isBuyable() {
		return true;
	}
	
	
	
}
