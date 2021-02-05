package de.unistuttgart.iste.rss.oo.hamstersimulator.sheet10.presence.exercise2;

import java.util.List;

/**
 * this class represents a barn, which consists of fans and cages. In this stable
 * laying poultries can be breed
 *
 * @version 14.01.2021
 */
public class Stable {
    public int id;
    protected String addressOfStable;
    protected float temperature;
    private int capacity;
    private double surface;
    private List<Ventilator> ventilators;
    private List<Cage> cages;
    private Technician technician;


    public Stable(final int id, final String address, final double surface, final Technician technician) {
        this.id = id;
        this.addressOfStable = address;
        this.surface = surface;
        this.technician = technician;
    }

    public void checkTemperature() {
        if (temperature > 40) {
            turnOffAllVentilators();
        } else {
            turnOnAllVentilators();
        }
    }

    private void turnOnAllVentilators() {
        for (final Ventilator aVentilator : ventilators) {
            aVentilator.turnOn();
        }
    }

    private void turnOffAllVentilators() {
        for (final Ventilator aVentilator : ventilators) {
            aVentilator.turnOff();
        }
    }
}
