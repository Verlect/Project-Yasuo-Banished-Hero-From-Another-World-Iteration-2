package model;

public class Enemies extends Entity{
	private int stunDurationRange;
	private int damageRange;
	private String picturePath;
	
	public Enemies(String name, int stunDurationRange, int damageRange, String picture) {
		this.setName(name);
		this.stunDurationRange = stunDurationRange;
		this.damageRange = damageRange;
		this.picturePath = picture;
	}
	
	public Enemies(Enemies user) {
		this.initialize(user.getName(), user.getHealth(), user.getAbility(), user.getItems());
		this.stunDurationRange = user.getStunDuration();
		this.damageRange = user.getDamage();
	}
	
	public int getStunDuration() {
		return this.stunDurationRange;
	}
	
	public String getPicture() {
		return this.picturePath;
	}
	
	public int getDamage() {
		return this.damageRange;
	}
}
