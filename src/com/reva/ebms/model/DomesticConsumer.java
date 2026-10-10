package com.reva.ebms.model;

/**
 * First concrete subclass of Consumer (INHERITANCE).
 */
public class DomesticConsumer extends Consumer {

    private static final double RATE_PER_UNIT = 5.0;

    public DomesticConsumer() {
        super(); // SUPER keyword: calls Consumer's default constructor
    }

    public DomesticConsumer(String consumerName, String consumerNumber, int unitsConsumed) {
        // SUPER keyword: calls Consumer's parameterized constructor
        super(consumerName, consumerNumber, unitsConsumed, ConnectionType.DOMESTIC);
    }

    /** METHOD OVERRIDING of the abstract method. */
    @Override
    public double getRatePerUnit() {
        return RATE_PER_UNIT;
    }

    /** Domestic consumers using 100 units or fewer get a small discount. */
    @Override
    protected double getDiscount() {
        return getUnitsConsumed() <= 100 ? 20.0 : 0.0;
    }

    /** METHOD OVERRIDING + use of super to reuse the parent's implementation. */
    @Override
    public String toString() {
        return "[DOMESTIC] " + super.toString(); // SUPER keyword: method call
    }
}
