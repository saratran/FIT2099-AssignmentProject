package game.item;


public class DinosaurTag extends PortableDinoItem {

	public DinosaurTag(String name, char displayChar) {
		super(name, displayChar);
	}
	
	public DinosaurTag() {
		this("Dinosaur Tag", '-');
	}
	
	@Override
	public int getBuyValue() {
		return 0;
	}

	@Override
	public boolean isBuyable() {
		return true;
	}
	
	public boolean isTaggable() {
		return true;
	}
	
}
