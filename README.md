# JCash Banking App

A beginner-friendly Java console banking simulation using OOP and `ArrayList`.
No JDBC/database is used in this version. All sample users and transactions are
stored in memory and reset when the program closes.

## Project structure

```text
JCashBankingApp/
├── src/
│   ├── Main.java
│   ├── model/
│   │   ├── User.java
│   │   ├── Transaction.java
│   │   ├── CashInTransaction.java
│   │   └── TransferTransaction.java
│   ├── service/
│   │   └── BankService.java
│   └── util/
│       └── InputUtil.java
└── README.md
```

## Run using the terminal

Open the project folder, then run:

```bash
javac -d out src/model/*.java src/service/*.java src/util/*.java src/Main.java
java -cp out Main
```

## Demo accounts

| Mobile number | PIN | Name | Starting balance |
|---|---|---|---:|
| 09171234567 | 1234 | Alex Santos | PHP 5,000.00 |
| 09981234567 | 5678 | Jamie Reyes | PHP 2,500.00 |

## Features

- Login with mobile number and PIN; maximum of 3 attempts
- View current balance
- Cash in with positive amount validation
- Transfer to another registered account
- Validate receiver, prevent self-transfer, and check sender balance
- Record sent and received transfers in both users' transaction histories
- View transaction history

## OOP concepts demonstrated

- **Encapsulation:** model fields are private and accessed through methods.
- **Inheritance:** `CashInTransaction` and `TransferTransaction` extend `Transaction`.
- **Polymorphism:** the transaction history stores `Transaction` references and calls
  each object's overridden `getSummary()` method.
- **Separation of concerns:** models hold data, `BankService` handles banking rules,
  `InputUtil` handles console input, and `Main` controls the menu.

## Important notes

This is an educational simulation, not a real banking application. PINs are stored
as plain text, and balances use `double` to keep the example approachable.
A production financial application should use secure PIN hashing, `BigDecimal`
for money, persistent storage, audit controls, and stronger transaction handling.
