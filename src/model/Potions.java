package model;

public class Potions extends Items {
	
	public Potions(int heal, String name, String description) {
		this.setHeal(heal);
		this.setName(name);
		this.setDescription(description);
	}
	
	public User heal(User user) {
		User result = new User(user);
		result.heal(this.getHeal());
		return new User(user);
	}
}
