package application;
import java.util.ArrayList;

public class Entity {
	private String name;
	private double health=100;
	private ArrayList<Ability> abilities = new ArrayList<>();
	private ArrayList<Items> items = new ArrayList<>();
	
	public void initialize(String name, double health, ArrayList<Ability> abilities, ArrayList<Items> items) {
		this.name = name;
		this.health = health;
		this.abilities = abilities;
		this.items = items;
	}
	
	public Entity (String name) {
		this.name = name;
	}
	public Entity() {}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getHealth() {
		return health;
	}
	
	public ArrayList<Ability> getAbility() {
		return abilities;
	}
	
	public ArrayList<Items> getItems() {
		return items;
	}
	
	public void takeDamage(double damage) {
		health -= damage;
		if(health < 0) {
			health = 0;
		}
	}
	
	public void heal(double healing) {
		health += healing;
	}
	
	public String toString() {
		return this.getName()+": "+this.getHealth();
	}
	
	public void addItem(Items toAdd) {
		items.add(toAdd);
	}
	
	public void addAbility(Ability toAdd) {
		abilities.add(toAdd);
	}
	
	public void removeItem(Items toRemove) {
		for (int i = 0; i < items.size(); i++) {
			if(toRemove.equals(items.get(i))) {
				items.remove(i);
			}
		}
	}
}
