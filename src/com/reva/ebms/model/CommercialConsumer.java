package com.reva.ebms.model;

/**
 * Second concrete subclass of Consumer (INHERITANCE).
 */
public class CommercialConsumer extends Consumer {

    private static final double RATE_PER_UNIT = 8.0;
    private static final double PEAK_SURCHARGE = 30.0; // extra fixed surcharge

    public CommercialConsumer() {
        super();
    }

    public CommercialConsumer(String consumerName, String consumerNumber, int unitsConsumed) {
        super(consumerName, consumerNumber, unitsConsumed, ConnectionType.COMMERCIAL);
    }

    @Override
    public double getRatePerUnit() {
        return RATE_PER_UNIT;
    }

    /** A negative "discount" models a surcharge added on top of the base bill. */
    @Override
    protected double getDiscount() {
        return -PEAK_SURCHARGE;
    }

    @Override
    public String toString() {
        return "[COMMERCIAL] " + super.toString();
    }
}
