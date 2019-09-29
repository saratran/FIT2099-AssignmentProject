package game.item;

import edu.monash.fit2099.interfaces.BuyableInterface;

public class DinosaurTag extends PortableDinoItem implements BuyableInterface {

	public DinosaurTag(String name, char displayChar) {
		super(name, displayChar);
	}

	@Override
	public int getBuyValue() {
		return 0;
	}
	
}
