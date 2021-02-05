package de.unistuttgart.iste.rss.oo.hamstersimulator.sheet10.presence.exercise2;

/**
 * this class represents a cage in which a defined number of laying poultries can live.
 *
 * @version 14.01.2021
 */
public class Cage<T extends LayingPoultry> {
    public int id;
    private int height;
    private double width;
    private int capacity;
    private boolean isFull;
}
