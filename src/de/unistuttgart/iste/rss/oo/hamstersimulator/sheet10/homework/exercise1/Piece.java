package de.unistuttgart.iste.rss.oo.hamstersimulator.sheet10.homework.exercise1;

/**
 * This is the implementation of a Piece in Mensch�rgereDichNicht
 * 
 * 
 * 
 * @author Sven Naber
 * @version 1.0
 * 
 */
public final class Piece {
	final Player owner;
	final int id;
	Location position;
	
	/** 
	* Class constructor
	* 
	* position is default Location(Zone.START,0)
	**/
	/*@
	  @ param owner		the player owning the piece
	  @ param id			the id of the piece (1-4 RED; 5-8 BLUE; 9-12 YELLOW; 13-16 GREEN) 
	  @*/
	public Piece(Player owner, int id) {
		this.owner = owner;
		this.id = id;
		this.position = new Location(Zone.START,0);
	}
	
	/*@
	  @ returns position
	  @*/
	public final Location getPosition() {
		return position;
	}

	/*@
	  @ param position	new Location
	  @*/
	public final void setPosition(Location position) {
		this.position = position;
	}

	
	/** 
	* checks if Location of the piece and a given Location are the same
	**/
	/*@
	  @ param otherLocation	given Location
	  @ returns 	true if otherLocation == this.position
	  @*/
	public final Boolean checkForCollision(Location otherLocation) {
		if (this.position.getZone()== otherLocation.getZone()) {
			if(this.position.getCoordinate() == otherLocation.getCoordinate()) {
				return true;
			}
		}
		return false;
	}
	
	
	/** 
	* generates a new legal Location for a piece given a roll
	* if there is none returns old Location
	**/
	/*@
	  @ param roll	Diceroll (1-6) 
	  @ returns 	new legal Location if there is one this.position otherwise
	  @*/
	public final Location generateNewLocationGivenRoll(int roll) {
		/*
		 * Goal: can move up 1 to 3 steps depending on position
		 */
		if (this.position.getZone()== Zone.GOAL) {
			if(roll <= 3-this.position.getCoordinate()) {
				return new Location(Zone.GOAL,this.position.getCoordinate()+roll);
			}
		}
		/*
		 * Start: if a 6 is rolled a piece can enter the field at its first position
		 */
		if (this.position.getZone()== Zone.START) {
			if (roll==6) {
				if (this.id <= 4) {
					return new Location(Zone.FIELD,0);
				}else if (this.id <= 8) {
					return new Location(Zone.FIELD,10);
				}else if (this.id <= 12) {
					return new Location(Zone.FIELD,20);
				}else if (this.id <= 16) {
					return new Location(Zone.FIELD,30);
				}	
			}
		}
		/*
		 *Field: will enter goal depending on position, may also have no legal location
		 */
		if (this.position.getZone()==Zone.FIELD) {
			if (this.id <= 4) {
				if (this.position.getCoordinate()+roll <= 39) {
					return new Location(Zone.FIELD,this.position.getCoordinate()+roll);
				}else if (this.position.getCoordinate()+roll <= 43) {
					return new Location(Zone.GOAL,this.position.getCoordinate()+roll-40);
				}
			}else if (this.id <= 8) {
				if (this.position.getCoordinate()>= 10 && this.position.getCoordinate()+roll<=39 ) {
					return new Location(Zone.FIELD,this.position.getCoordinate()+roll);
				}else if (this.position.getCoordinate()>= 10 && this.position.getCoordinate()+roll>39 ) {
					return new Location(Zone.FIELD,(this.position.getCoordinate()+roll)%40);
				}else if (this.position.getCoordinate()< 10 && this.position.getCoordinate()+roll<10) {
					return new Location(Zone.FIELD,this.position.getCoordinate()+roll);
				}else if (this.position.getCoordinate()< 10 && this.position.getCoordinate()+roll<= 13) {
					return new Location(Zone.GOAL,this.position.getCoordinate()+roll-10);
				}
			}else if (this.id <= 12) {
				if (this.position.getCoordinate()>= 20 && this.position.getCoordinate()+roll<=39 ) {
					return new Location(Zone.FIELD,this.position.getCoordinate()+roll);
				}else if (this.position.getCoordinate()>= 20 && this.position.getCoordinate()+roll>39 ) {
					return new Location(Zone.FIELD,(this.position.getCoordinate()+roll)%40);
				}else if (this.position.getCoordinate()< 20 && this.position.getCoordinate()+roll<20) {
					return new Location(Zone.FIELD,this.position.getCoordinate()+roll);
				}else if (this.position.getCoordinate()< 20 && this.position.getCoordinate()+roll<= 23) {
					return new Location(Zone.GOAL,this.position.getCoordinate()+roll-20);
				}
			}else if (this.id <= 16) {
				if (this.position.getCoordinate()>= 30 && this.position.getCoordinate()+roll<=39 ) {
					return new Location(Zone.FIELD,this.position.getCoordinate()+roll);
				}else if (this.position.getCoordinate()>= 30 && this.position.getCoordinate()+roll>39 ) {
					return new Location(Zone.FIELD,(this.position.getCoordinate()+roll)%40);
				}else if (this.position.getCoordinate()< 30 && this.position.getCoordinate()+roll<30) {
					return new Location(Zone.FIELD,this.position.getCoordinate()+roll);
				}else if (this.position.getCoordinate()< 30 && this.position.getCoordinate()+roll<= 33) {
					return new Location(Zone.GOAL,this.position.getCoordinate()+roll-30);
				}
			}
		}
		return this.position;
	}
}
