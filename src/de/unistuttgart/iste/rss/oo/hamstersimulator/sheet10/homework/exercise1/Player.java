package de.unistuttgart.iste.rss.oo.hamstersimulator.sheet10.homework.exercise1;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * This is the implementation of a Player in Mensch�rgereDichNicht
 * 
 * 
 * 
 * @author Sven Naber
 * @version 1.0
 * 
 */
public final class Player {
	final List<Piece> pieces = new LinkedList<Piece>();
	final Colour playerColour;
	final Game field;
	/** 
	* Class constructor
	* 
	* Generates it's Pieces and adds them to the LinkedList pieces
	**/
	/*@
	  @ param field					the Game the player participates in
	  @ param playerColour			the colour of the player (1-4 RED; 5-8 BLUE; 9-12 YELLOW; 13-16 GREEN) 
	  @*/
	public Player(Game field, Colour playerColour) {
		this.playerColour = playerColour;
		this.field = field;
		int startid = this.getStartIdByColour(playerColour);
		for (int i = 0; i<=3; i++) {
			pieces.add(new Piece(this,i+startid));
		}
		
	}
	
	/** 
	* random dice roll (1 up to number)
	**/
	/*@
	  @ param number		specifies the die you are using
	  @ returns random integer (1 up to number)
	  @*/
	public final Integer getRoll(int number) {
		Random dice = new Random();
		return 1 + dice.nextInt(number);
		
	}
	
	/*@ 
	  @ param id		the id of a piece (1-4 RED; 5-8 BLUE; 9-12 YELLOW; 13-16 GREEN)
	  @ returns colour 
	  @*/
	public final Colour getColourById(int id) {
		if (id<=4) {
			return Colour.RED;
		}else if (id<=8) {
			return Colour.BLUE;
		}else if (id<=12) {
			return Colour.YELLOW;
		}else{
			return Colour.GREEN;
		}
	}
	
	/** 
	* gets the lowest id for given colour
	**/
	/*@
	  @ param colour		the colour of the player (1-4 RED; 5-8 BLUE; 9-12 YELLOW; 13-16 GREEN)
	  @ returns id
	  @ */
	public final int getStartIdByColour(Colour colour) {
		if (colour == Colour.RED) {
			return 1;
		}else if (colour == Colour.BLUE) {
			return 5;
		}else if (colour == Colour.YELLOW) {
			return 9;
		}else{
			return 13;
		}
	}
	
	/** 
	* Player takes turn: 
	* rolls die
	* sets flag if roll = 6
	* generates possible moves
	* chooses one (if available) move at random and executes it
	* prints move to console
	**/
	/*@
	  @ ensures if roll==6 this.field.rolled6 = true else this.field.rolled6 = false
	  @ ensures positions of pieces updated if a move is chosen
	  @ ensures move printed to console if one is chosen 
	  @*/
	public final void takeTurn() {
		System.out.println("Player " + this.playerColour + "'s turn");
		List<Move> possibleMoves = new LinkedList<Move>();
		int roll = this.getRoll(6);
		if (roll == 6) {
			field.setRolled6(true);
		}else {
			field.setRolled6(false);
		}
		System.out.println("\nPlayer " + this.playerColour + " rolled a " + roll+ "\n");
		generateMoves(possibleMoves, roll);
		int chosenMove;
		if (!possibleMoves.isEmpty()) {
			chosenMove = this.getRoll(possibleMoves.size())-1;
			possibleMoves.get(chosenMove).executeMove();
		}
		

	}
	
	/** 
	* generates legal moves if there are any
	* checks for collision with own and enemy pieces
	**/
	/*@
	  @ param roll			roll of the die
	  @ param possibleMoves	List where the generated moves will be added
	   
	  @ requires 1 <= roll <= 6
	  @ requires possibleMoves != null
	  @ ensures all legal moves added to possibleMoves 
	  @ */
	private final void generateMoves(List<Move> possibleMoves, int roll) {
		for (Piece piece : pieces) {
			Location newLocation = piece.generateNewLocationGivenRoll(roll);
			if (newLocation.getZone()==piece.position.getZone() && newLocation.getCoordinate() == piece.position.getCoordinate()) {
				continue;
			}
			/*
			 * checks for collision with own pieces in GOAL and excludes Move if true
			 */
			if (newLocation.getZone()==Zone.GOAL) {
				Boolean collision = false;
				for (Piece ownPiece : pieces) {
					if (ownPiece.checkForCollision(newLocation)) {
						collision = true;
					}
				}
				if (!collision) {
					possibleMoves.add(new Move(piece,newLocation));
				}
			}
			/*
			 * checks for collision with own pieces in FIELD and excludes Move if true
			 */
			if (newLocation.getZone()==Zone.FIELD) {
				Boolean collision = false;
				for (Piece ownPiece : pieces) {
					if (ownPiece.checkForCollision(newLocation)) {
						collision = true;
					}
				}
				/*
				 * checks for collision with enemy pieces in FIELD
				 * if there is one generates Move specifying the enemy Piece
				 * otherwise generates Move with only itself and the new Location
				 * (see constructor of Move)
				 */
				if (!collision) {
					Boolean pieceDisplaced = false;
					for (Player player : this.field.players) {
						for (Piece otherPiece : player.pieces) {
							if (otherPiece.checkForCollision(newLocation)) {
								pieceDisplaced = true;
								possibleMoves.add(new Move(piece,newLocation,otherPiece));
							}
						}
					}
					if (!pieceDisplaced) {
						possibleMoves.add(new Move(piece,newLocation));
					}
				}
			}
		}
	}
	
	
}
