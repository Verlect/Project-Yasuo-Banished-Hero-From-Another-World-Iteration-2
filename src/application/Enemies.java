package application;

public class Enemies extends Entity{
	private int stunDurationRange;
	private int damageRange;
	
	public Enemies(String name, int stunDurationRange, int damageRange) {
		this.setName(name);
		this.stunDurationRange = stunDurationRange;
		this.damageRange = damageRange;
	}
	
	public Enemies(Enemies user) {
		this.initialize(user.getName(), user.getHealth(), user.getAbility(), user.getItems());
		this.stunDurationRange = user.getStunDuration();
		this.damageRange = user.getDamage();
	}
	
	public int getStunDuration() {
		return this.stunDurationRange;
	}
	
	public int getDamage() {
		return this.damageRange;
	}
}
