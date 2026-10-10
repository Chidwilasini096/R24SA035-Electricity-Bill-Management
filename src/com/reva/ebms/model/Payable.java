package com.reva.ebms.model;

/**
 * INTERFACE DEMONSTRATION (Unit-II requirement).
 * Any class that can generate a payable electricity bill implements this.
 * It is accessed through an interface reference in Main (dynamic method dispatch).
 */
public interface Payable {
    double calculateBill();
}
