package model;

public class Ability {
	private String description;
	private double damage;
	private double heal;
	private String abilityName;
	private int stunduration;
	private int type; //type=0 or 1, if type is 1, ability is casted on target, else cast on self.
	
	public Ability(String name, String des, double damage, int stunduration) {
		this.description = des;
		this.abilityName = name;
		this.damage = damage;
		this.stunduration = stunduration;
	}
	
	public String getDescription() {
		return this.abilityName + ": " + this.description;
	}
	
	public void activateDamage(Entity affected) {
		affected.takeDamage(damage);
	}
	
	public void activateHeal(Entity affected) {
		affected.heal(heal);
	}
	
	public int getStunTime() {
		return this.stunduration;
	}
}
