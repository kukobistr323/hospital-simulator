# Hospital Simulator

A simple command-line simulator that models patient health states in a
hospital based on administered drugs.\
This project was implemented in Java with Maven and follows clean code
principles (KISS, separation of concerns, extensibility).

------------------------------------------------------------------------

## 📋 Problem Description

Patients can be in one of the following states:

- **F** → Fever\
- **H** → Healthy\
- **D** → Diabetes\
- **T** → Tuberculosis\
- **X** → Dead

Drugs:

- **As** → Aspirin\
- **An** → Antibiotic\
- **I** → Insulin\
- **P** → Paracetamol

### Rules

- Aspirin cures Fever\
- Paracetamol cures Fever\
- Antibiotic cures Tuberculosis\
- Insulin prevents diabetics from dying (but does not cure Diabetes)\
- Insulin + Antibiotic makes Healthy → Fever\
- Paracetamol + Aspirin together kill the patient\
- Dead patients may randomly resurrect (1 in 1,000,000 chance →
  Healthy)

------------------------------------------------------------------------

## 🏗️ Project Structure

    src/
     ├── main/java/com/example/hospital/
     │    ├── HospitalSimulator.java   # Entry point
     │    ├── app/                     # CLI orchestration
     │    ├── io/                      # Input parsing
     │    ├── model/                   # Enums: HealthState, Drug
     │    ├── service/                 # Business rules & simulation
     │    ├── util/                    # Output formatter
     │    └── validation/              # Validators (chain of responsibility)
     └── test/java/com/example/hospital/
          ├── unit tests               # Per class
          └── integration tests        # Full flow with examples

------------------------------------------------------------------------

## ⚙️ Build & Run

### Requirements

- Java 17+
- Maven 3.8+

### Build JAR

``` bash
mvn clean package
```

The executable JAR will be created at:

    target/hospital-simulator-1.0.0.jar

### Run

``` bash
java -jar target/hospital-simulator-1.0.0.jar <patients> [drugs]
```

- `<patients>` → comma-separated list of states (e.g.`D,F,F`)\
- `[drugs]` → comma-separated list of drugs (e.g.`As,I`), optional

------------------------------------------------------------------------

## 🧪 Examples

### Example 1

``` bash
java -jar target/hospital-simulator-1.0.0.jar D,D
```

Output:

    F:0,H:0,D:0,T:0,X:2

### Example 2

``` bash
java -jar target/hospital-simulator-1.0.0.jar F P
```

Output:

    F:0,H:1,D:0,T:0,X:0

### Example 3

``` bash
java -jar target/hospital-simulator-1.0.0.jar T,F,D An,I
```

Output:

    F:2,H:0,D:1,T:0,X:0

------------------------------------------------------------------------

## ✅ Testing

All tests (unit + integration) are run automatically by Maven.

Run manually:

``` bash
mvn test
```

------------------------------------------------------------------------

## 🔍 Design Highlights

- **Command pattern** for drug rules --- each rule is encapsulated in
  its own class.\
- **Chain of Responsibility** for input validation --- modular
  validators.\
- **Separation of concerns**: parsing, domain mapping, simulation, and
  output formatting.\
- **Extensible**: adding new states/drugs only requires extending
  enums and adding new rules.\
- **Readable**: clear structure, documented assumptions.

------------------------------------------------------------------------

## 👤 Author

Uladzislau Kukoba
