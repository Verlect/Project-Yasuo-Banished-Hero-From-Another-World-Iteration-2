package model;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Weapons extends Items{
	
	public Weapons(int damage, String name, String description) {
		this.setDamage(damage);
		this.setName(name);
		this.setDescription(description);
	}
	
	public Weapons(int damage, String name, String description, ImageView mapItem) {
		this.setDamage(damage);
		this.setName(name);
		this.setDescription(description);
		this.setMapItem(mapItem);
	}
}
