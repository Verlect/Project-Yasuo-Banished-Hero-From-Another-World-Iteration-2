package model;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public abstract class Items {
	private int damage;
	private int defense;
	private int heal;
	private String name;
	private String description;
	private ImageView mapItem;
	
	public void setDamage(int damage)
	{
		this.damage = damage;
	}
	
	public void setDefense(int defense) {
		this.defense = defense;
	}
	
	public void setHeal(int heal ) {
		this.heal = heal;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setMapItem(ImageView mapItem) {
		this.mapItem = mapItem;
	}
	
	public int getHeal() {
		return heal;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name + ": " + description;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public ImageView getMapItem() {
		return mapItem;
	}
	
}
