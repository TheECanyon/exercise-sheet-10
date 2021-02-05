package de.unistuttgart.iste.rss.oo.hamstersimulator.sheet10.teacher;

import de.unistuttgart.iste.rss.oo.hamstersimulator.external.model.SimpleHamsterGame;

/**
 * Hamster game which contains a bug. It's being used to demonstrate the debugging tools provided by eclipse.
 */
public class BuggyHamsterGame extends SimpleHamsterGame {
	
	public BuggyHamsterGame() {
		this.loadTerritoryFromResourceFile("/territories/teacher-territory.ter");
		this.displayInNewGameWindow();
		game.startGame();
	}
	

	@Override
	protected void run() {
		 // TODO: Place breakpoint marker in NEXT line.
        paule.move();
        paule.move();
        paule.pickGrain();
        paule.pickGrain();
        paule.move();
	}
	
}
