package com.reva.ebms.model;

/**
 * Third concrete subclass of Consumer.
 *
 * FINAL CLASS (Unit-II requirement): industrial tariff rules are fixed by
 * the electricity board and must not be further customised by additional
 * subclassing, so this class cannot be extended.
 */
public final class IndustrialConsumer extends Consumer {

    private static final double RATE_PER_UNIT = 10.0;

    public IndustrialConsumer() {
        super();
    }

    public IndustrialConsumer(String consumerName, String consumerNumber, int unitsConsumed) {
        super(consumerName, consumerNumber, unitsConsumed, ConnectionType.INDUSTRIAL);
    }

    @Override
    public double getRatePerUnit() {
        return RATE_PER_UNIT;
    }

    @Override
    public String toString() {
        return "[INDUSTRIAL] " + super.toString();
    }
}
