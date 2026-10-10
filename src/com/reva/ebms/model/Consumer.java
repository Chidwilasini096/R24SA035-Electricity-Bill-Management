package com.reva.ebms.model;

/**
 * ABSTRACT CLASS + BASE CLASS OF INHERITANCE HIERARCHY.
 * Consumer -> DomesticConsumer / CommercialConsumer / IndustrialConsumer
 *
 * Demonstrates: encapsulation, constructors + overloading, this()/this,
 * static fields & methods, final constants, an abstract method, a final
 * method, toString()/equals() overriding, and implements Payable (interface).
 */
public abstract class Consumer implements Payable {

    // ---- STATIC (class-level) SCOPE ----
    // Shared by every Consumer object; used as an object counter.
    private static int totalConsumers = 0;

    // FINAL CONSTANTS (final keyword, Unit-I requirement)
    public static final double SERVICE_CHARGE = 50.0;   // flat monthly service charge
    public static final double LATE_FEE_PERCENT = 2.0;  // default late fee % per day

    // ---- INSTANCE (object-level) SCOPE ----
    // Private fields -> ENCAPSULATION. Accessed only via public getters/setters.
    private String consumerName;
    private String consumerNumber;
    private int unitsConsumed;
    private ConnectionType connectionType;
    private boolean billPaid;

    /**
     * Default constructor (CONSTRUCTOR OVERLOADING, part 1).
     * Uses this(...) to chain to the parameterized constructor below,
     * so the object-counting / field-initialisation logic lives in one place.
     */
    public Consumer() {
        this("UNKNOWN", "CN0000", 0, ConnectionType.DOMESTIC);
    }

    /**
     * Parameterized constructor (CONSTRUCTOR OVERLOADING, part 2).
     * Explicit use of `this` to resolve shadowing between parameter names
     * and instance field names of the same name.
     */
    public Consumer(String consumerName, String consumerNumber,
                     int unitsConsumed, ConnectionType connectionType) {
        this.consumerName = consumerName;     // explicit `this` keyword
        this.consumerNumber = consumerNumber;
        this.unitsConsumed = unitsConsumed;
        this.connectionType = connectionType;
        this.billPaid = false;
        totalConsumers++;                     // static field updated on every object creation
    }

    /**
     * ABSTRACT METHOD. Each subclass has a different tariff, so the rate
     * per unit cannot be defined here — it MUST be supplied by a subclass.
     */
    public abstract double getRatePerUnit();

    /**
     * Hook method subclasses may override to apply a discount or surcharge.
     * Given a default (no discount) here so subclasses that don't need one
     * are not forced to implement it.
     */
    protected double getDiscount() {
        return 0.0;
    }

    /**
     * FINAL METHOD (Unit-II requirement).
     * Declared final because the *formula* used to compute a bill must stay
     * identical for every consumer type — only the rate/discount inputs
     * differ (via the abstract/overridable hooks above). Making it final
     * stops a subclass from accidentally breaking the official billing rule.
     *
     * Also demonstrates OPERATOR PRECEDENCE: multiplication (*) is
     * evaluated before addition (+) and subtraction (-).
     */
    @Override
    public final double calculateBill() {
        double energyCharge = unitsConsumed * getRatePerUnit(); // '*' binds tighter than the '+'/'-' below
        double total = energyCharge + SERVICE_CHARGE - getDiscount();
        return total;
    }

    /**
     * METHOD OVERLOADING (two versions of applyLateFee).
     */
    public double applyLateFee(double daysLate) {
        return applyLateFee(daysLate, LATE_FEE_PERCENT);
    }

    public double applyLateFee(double daysLate, double customPercent) {
        double fee = calculateBill() * (customPercent / 100.0) * daysLate;
        return fee;
    }

    // ---------------- GETTERS / SETTERS (Encapsulation) ----------------

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConsumerNumber() {
        return consumerNumber;
    }

    public void setConsumerNumber(String consumerNumber) {
        this.consumerNumber = consumerNumber;
    }

    public int getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(int unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    public boolean isBillPaid() {
        return billPaid;
    }

    public void setBillPaid(boolean billPaid) {
        this.billPaid = billPaid;
    }

    /** STATIC METHOD reading the static counter. */
    public static int getTotalConsumers() {
        return totalConsumers;
    }

    /**
     * toString() OVERRIDDEN from Object class.
     */
    @Override
    public String toString() {
        return String.format(
                "Consumer[No.=%-6s Name=%-20s Type=%-10s Units=%-4d Bill=Rs.%.2f]",
                consumerNumber, consumerName, connectionType, unitsConsumed, calculateBill());
    }

    /**
     * equals() OVERRIDDEN from Object class.
     * Uses a String method (equalsIgnoreCase) for the comparison.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Consumer)) {
            return false;
        }
        Consumer other = (Consumer) obj;
        return this.consumerNumber.equalsIgnoreCase(other.consumerNumber);
    }
}
