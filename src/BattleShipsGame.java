import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BattleShipsGame {
	
	static int p;
	static int c;
	static char[][] sea;
	static char[][] playerSeaView;
	
	public static void main(String[] args) {
		
		sea = new char[10][10];
		
		p=5;c=5;
		
		printSea(sea);
		
		System.out.println();
		
		playerInitChoices(sea);
		
		printSea(sea);
		
		System.out.println();
		
		playerSeaView = new char[10][10];
		
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				playerSeaView[i][j]=sea[i][j];
			}
		}
		
		ComputerInitChoices(sea);
		
		
		while(p!=0 || c!=0) {
			
			playerAttack(sea);
			printSea(playerSeaView);
			if(p==0 || c==0)
				break;
			computerAttack(playerSeaView);
			printSea(playerSeaView);			
		}
		
		printSea(sea);
		
		System.out.println("Your score: "+p+" Computer score: "+ c);
		if(p>c)
			System.out.println("Hooray! You win!");
		else
			System.out.println("Sorry you lost");
	}


	private static void playerInitChoices(char[][] sea) {
		for(int i=0;i<5;i++) {
			
			Scanner input = new Scanner(System.in);
			
			try {
				System.out.print("Enter X coordinate for your ship: ");
				int x = input.nextInt();
				System.out.print("Enter Y coordinate for your ship: ");
				int y = input.nextInt();
				
				if(sea[x][y]=='@') {
					System.out.println("location denied\nselect another coordinate");
					--i;
				}
				else
					sea[x][y]='@';
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Enter a valid position");
				i--;
			}
        
		}
		
	}

	public static void printSea(char[][] sea) {
				
		for(int i=0; i<sea.length;i++) {
			System.out.print(" ");
			System.out.print(" ");
			System.out.print(i);
		}
		
		System.out.println();
		
		for (int i=0;i<sea.length; ++i) {
			System.out.print(i);
			System.out.print(Arrays.toString(sea[i]));
			System.out.println(i);
		}

		for(int i=0; i<sea.length;i++) {
			System.out.print(" ");
			System.out.print(" ");
			System.out.print(i);
		}
		
		System.out.println();
	}
	
	public static void ComputerInitChoices(char[][] sea) {
			for(int i=0;i<5;i++) {
			
				Random rand = new Random();
				int x = rand.nextInt(9);
				int y = rand.nextInt(9);
				
		        if(sea[x][y]=='@' || sea[x][y]=='2') {
		        	--i;
		        }
		        else {
		        	sea[x][y]='2';
		        	System.out.println("ship deployed");
		        }
		}
	}
	
	private static void playerAttack(char[][] sea) {
		
		
			Scanner input = new Scanner(System.in);
		
			try {
				System.out.print("Enter X coordinate for your ship: ");
				int x = input.nextInt();
				System.out.print("Enter Y coordinate for your ship: ");
				int y = input.nextInt();
				
				if(sea[x][y]=='@') {
					sea[x][y]='X';
					playerSeaView[x][y]='X';
					System.out.println("Oh no, you sunk your own ship :(");
					p--;
				}
				else if(sea[x][y]=='2') {
					sea[x][y]='!';
					playerSeaView[x][y]='!';
					System.out.println("Boom! You sunk the ship!");
					c--;
				}
				else if(sea[x][y]==0) {
					sea[x][y]='-';
					playerSeaView[x][y]='-';
					System.out.println("Sorry, you missed");
				}
			} catch (Exception e) {
				System.out.println("Enter a valid position");
				playerAttack(sea);
			}
	}
	
	private static void computerAttack(char[][] sea) {

		Random rand = new Random();
		
		System.out.print("Enter X coordinate for your ship: ");
        int x = rand.nextInt(9);
        System.out.print("Enter Y coordinate for your ship: ");
        int y = rand.nextInt(9);
		
        if(sea[x][y]=='@') {
        	sea[x][y]='X';
			playerSeaView[x][y]='X';
        	System.out.println("The Computer sunk one of your ships!");
        	p--;
        }
        else if(sea[x][y]=='2') {
        	sea[x][y]='!';
			playerSeaView[x][y]='!';
        	System.out.println("The Computer sunk one of its own ships");
        	c--;
        }
        else {
        	System.out.println("Computer missed");
        }
		
	}

}
