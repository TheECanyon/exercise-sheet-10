package de.unistuttgart.iste.rss.oo.hamstersimulator.sheet10.homework.exercise1;

/**
 * This is the implementation of a Location in Mensch�rgereDichNicht
 *
 *
 *
 * @author Sven Naber
 * @version 1.0
 *
 */
public class Location {
    Zone zone;
    int coordinate;

    /**
     * Instances a new zone object
     **/
    /*@
      @param zone       the zone (START,FIELD,GOAL) of the piece
      @param coordinate the coordinate of the piece inside the zone (START 0; GOAL
                        0-3; FIELD 0-39)
      @requires zone != null
      @*/
    public Location(final Zone zone, final int coordinate) {
	this.zone = zone;
	this.coordinate = coordinate;
    }

    /*@
      @returns zone
      @*/
    public final Zone getZone() {
	return zone;
    }

    /*@
      @param zone the zone (START,FIELD,GOAL) of the piece
     
      @ensures this.zone = zone
      @*/
    public final void setZone(Zone zone) {
	this.zone = zone;
    }

    /*@
      @ensures Location printed to console
      @*/
    public final String toString() {
	return "Zone: " + this.getZone() + ", Coordinate: " + this.getCoordinate();
    }

    /*@
      @returns coordinate
      @*/
    public final int getCoordinate() {
	return coordinate;
    }

    /*@
      @ param coordinate the coordinate of the piece inside the zone (START 0; GOAL
                        0-3; FIELD 0-39) 
      @ ensures this.coordinate = coordinate
      @*/
    public final void setCoordinate(int coordinate) {
	this.coordinate = coordinate;
    }

}
