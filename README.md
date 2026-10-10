# Electricity Bill Management System — Java OOP Mini Project

**Student:** Sambangi Chidwilasini
**SRN:** R24SA035
**Sl. No.:** 35
**Assigned Domain:** Electricity Bill Management System
**Subject:** Java Programming (Unit I & Unit II)

## 1. How to Compile & Run

From the project root (the folder containing `src/`):

```bash
# Compile
javac -d bin src/com/reva/ebms/model/*.java src/com/reva/ebms/service/*.java src/com/reva/ebms/app/*.java

# Run
java -cp bin com.reva.ebms.app.Main
```

## 2. Package Structure

```
com.reva.ebms.model    -> ConnectionType, Payable, Consumer, DomesticConsumer,
                           CommercialConsumer, IndustrialConsumer
com.reva.ebms.service  -> BillService
com.reva.ebms.app      -> Main (entry point)
```

## 3. Feature Traceability (Section 4 requirement)

| # | Mandatory Feature | File / Class | Where |
|---|---|---|---|
| 1 | 3–4 classes with encapsulation | `Consumer`, `DomesticConsumer`, `CommercialConsumer`, `IndustrialConsumer` | private fields + public getters/setters in `Consumer.java` |
| 2 | Varied data types, final constants, variable scope | `Consumer.java` | `String/int/double/boolean/enum` fields; `SERVICE_CHARGE`, `LATE_FEE_PERCENT` (final); static vs instance vs local scope comments in `Consumer.java` and `Main.java` |
| 3 | Operators & operator precedence | `Main.java` | `precedenceDemo` line in `main()`; also `Consumer.calculateBill()` |
| 4 | Type conversion / casting (double→int) | `Main.java` | `case 4`: `int roundedRevenue = (int) revenue;` |
| 5 | Enum | `ConnectionType.java` | `DOMESTIC, COMMERCIAL, INDUSTRIAL` |
| 6 | Control flow + jump statements | `Main.java`, `BillService.java` | `if/else`, `switch`, `do-while`, `for`, `break`, `continue`, `return` |
| 7 | Arrays of objects | `Main.java` | `Consumer[] consumers = new Consumer[MAX_CONSUMERS];` |
| 8 | Console I/O, formatted output | `Main.java` | `Scanner`, `System.out.printf` |
| 9 | Multiple constructors (overloading) | `Consumer.java` + all subclasses | default & parameterized constructors |
| 10 | Method overloading | `Consumer.applyLateFee(...)`, `BillService.displayBill(...)` | two signatures each |
| 11 | Static fields/methods | `Consumer.java` | `totalConsumers`, `getTotalConsumers()` |
| 12 | Explicit `this` | `Consumer.java` | constructor field assignment + `this(...)` chaining |
| 13 | String methods (search/compare/split/trim) | `BillService.java` | `trim`, `split`, `equalsIgnoreCase`, `contains`, `compareTo` |
| 14 | Base class + ≥2 subclasses | `Consumer` → `DomesticConsumer`, `CommercialConsumer`, `IndustrialConsumer` | `model` package |
| 15 | `super` keyword | subclasses | `super(...)` constructor calls; `super.toString()` in each subclass |
| 16 | Overriding + dynamic binding | `Main.java` `case 5` | `Consumer parentRef = consumers[4];` |
| 17 | Abstract class + abstract method | `Consumer.java` | `abstract class Consumer`, `abstract double getRatePerUnit();` |
| 18 | Interface, accessed via interface reference | `Payable.java`, `Main.java` `case 5` | `Payable interfaceRef = consumers[0];` |
| 19 | `toString()` / `equals()` override | `Consumer.java` | both overridden |
| 20 | final method / final class | `Consumer.calculateBill()` (final method), `IndustrialConsumer` (final class) | comments explain why |
| 21 | ≥2 custom packages with imports | `com.reva.ebms.model`, `.service`, `.app` | see import statements at top of each file |

## 4. Deliverables Included

- Complete source code (`src/` — organized in packages)
- This README (feature traceability report)
- `sample_output.txt` — a captured/representative console run

## 5. Notes

- Consumer numbers use prefixes `DC` (Domestic), `CC` (Commercial), `IC` (Industrial).
- Domestic consumers get a ₹20 discount for usage ≤ 100 units.
- Commercial consumers carry a fixed ₹30 peak-hour surcharge.
- Please run a short test of your own after compiling, and be ready to explain
  any part of the code during the viva as required by the assignment.
