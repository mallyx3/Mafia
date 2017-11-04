package mafia;

import java.util.Scanner;

public class Game {
	private Integer numCivilian;
	private Integer numMafia;
	private phases phase;
	
	private enum phases {
		INTRO, DAY, NIGHT, FINISH
	}
	
	public Game(Integer c, Integer m) {
		this.numCivilian = c;
		this.numMafia = m;
		phase = phases.INTRO;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Integer civ, maf;
		System.out.println("How many Civilians do you have?");
		civ = input.nextInt();
		System.out.println("How many Mafia do you have?");
		maf = input.nextInt();
		
		Game g = new Game(civ, maf);
		System.out.println("Starting the game with " + civ.toString() + " civilians and " + maf.toString() + " mafia. Good luck!");
		g.processIntro();
		
		input.nextLine();
		while (g.phase != phases.FINISH) {
			g.processPhase(input);
		}
		
		if (g.numMafia == 0) {
			System.out.println("Civilians win!");
		} else {
			System.out.println("Mafia wins!");
		}
		
		input.close();
		
	}
	
	public void processIntro() {
		this.phase = phases.DAY;
	}
	
	public void processPhase(Scanner input) {
		System.out.println("It's now " + phase);
		System.out.println("There are " + numCivilian + " civilians and " + numMafia + " mafia.");
		if (this.phase == phases.NIGHT) {
			numCivilian--;
			phase = phases.DAY;
		} else if (this.phase == phases.DAY) {
			System.out.println("It's now daytime. Do you kill a mafia (Y) or civilian (anything else)");
			String req = input.nextLine();
			if (req.equals("Y")) {
				numMafia--;
			}
			phase = phases.NIGHT;
		}

		if (numCivilian <= numMafia || numMafia == 0) {
			phase = phases.FINISH;
		} 
	}
	
}
