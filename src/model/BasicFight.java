package model;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BasicFight {
	private User player;
	private Enemies enemy;
	private int turns;
	private int playerStun = 0;
	private int enemyStun = 0;
	private int death = 0;
	private int nextScene = 0;
	Scanner scan = new Scanner(System.in);
	Random random = new Random();
	
	public BasicFight(User player, Enemies enemy, int nextScene) {
		this.player = new User(player);
		this.enemy = new Enemies(enemy);
		this.nextScene = nextScene;
	}
	
	
	public User startFight() {
		System.out.println("Initiating Battle Sequence");
		while(this.player.getHealth() > 0 && this.enemy.getHealth() > 0) {
			
			System.out.println("\n\nTurn " + (turns+1));
			System.out.println("---------------------------");
			System.out.println(this.player.getName() + "'s Health: " + this.player.getHealth());
			System.out.println(this.enemy.getName() + "'s Health: " + this.enemy.getHealth());
			
			if(playerStun < 0) {
				playerStun = 0;
			} 
			
			if(enemyStun < 0) {
				enemyStun = 0;
			} 
			
			if (playerStun == 0) {
				System.out.println("Pick an action for this action");
				System.out.println("1. Attack Using Your Basic Attack");
				System.out.println("2. Use an Item or Weapon");
				System.out.println("3. Use Ability");
				
				int response = scan.nextInt();
				
				if(response == 1) {
					int rand = random.nextInt(10);
					enemy.takeDamage(rand);
					System.out.println("You did " + rand + " damage");
				} else if (response == 2) {
					if(player.getItems().size() == 0) {
						System.out.println("No Items");
					} else {
						for(int i = 0; i < player.getItems().size(); i++) {
							System.out.println((i + 1) + ". " + player.getItems().get(i).toString());
						}
						
						System.out.println("Please select an item or weapon: ");
						int iresponse = scan.nextInt();
						
						if(iresponse <= player.getItems().size() 
								&& player.getItems().get(iresponse - 1) instanceof Potions) {
							System.out.println("You have healed for " + player.getItems().get(iresponse - 1).getHeal() + " health.");
							player.heal(player.getItems().get(iresponse - 1).getHeal());
							player.removeItem(player.getItems().get(iresponse - 1));
						} else if (iresponse <= player.getItems().size() 
								&& player.getItems().get(iresponse - 1) instanceof Weapons) {
							enemy.takeDamage(player.getItems().get(iresponse - 1).getDamage());
							System.out.println("You have dealt " + player.getItems().get(iresponse - 1).getDamage() + " damage.");
						} else {
							System.out.println("Invalid input, you missed your turn");
						}
					}
				} else if (response == 3) {
					if(player.getAbility().size() == 0) {
						System.out.println("No Abilities");
					} else {
						for(int i = 0; i < player.getAbility().size(); i++) {
							System.out.println((i + 1) + ". " + player.getAbility().get(i).getDescription());
						}
						
						System.out.println("Please select an ability ");
						int iresponse = scan.nextInt();
						
						if(iresponse <= player.getAbility().size() && iresponse >= 0) {
							enemyStun += player.getAbility().get(iresponse - 1).getStunTime();
							//System.out.println(enemy.getName() + " stunned for " + enemyStun + "turns"); Testing
						} else {
							System.out.println("Invalid input, you missed your turn");
						}
					}
				}
			} else if (playerStun > 0) {
				System.out.println("You are stunned for " + playerStun + "turns.");
			}
			
			if (playerStun > 0){
				playerStun--;
			}
			
			if (enemyStun == 0) {
				int end = random.nextInt(enemy.getDamage()+1);
				player.takeDamage(end);
				System.out.println("You took " + end + " damage");
				int stun = random.nextInt(enemy.getStunDuration()+1);
				playerStun += stun;
			} else if (enemyStun > 0) {
				System.out.println(enemy.getName() + " is stunned for " + enemyStun + " turn(s).");
			}
			
			if (enemyStun > 0) {
				enemyStun--;
			}
			
			
			turns++;
		}
		if (player.getHealth() <= 0) {
			death = -1;
		} else {
			death = this.nextScene;
		}
		return new User(player);
		
	}
	
	public int getDeath() {
		return death;
	}
}
