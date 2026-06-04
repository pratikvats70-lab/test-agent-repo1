package org.example.javafeatures;

import java.util.HashMap;
import java.util.Map;

public class BankingWithLambdaReturn {

    // -----------------------------
    // Entity
    // -----------------------------
    static class Account {

        private final String accountNo;
        private double balance;

        public Account(String accountNo, double balance) {
            this.accountNo = accountNo;
            this.balance = balance;
        }

        public String getAccountNo() {
            return accountNo;
        }

        public double getBalance() {
            return balance;
        }

        public void credit(double amount) {
            balance += amount;
        }

        public void debit(double amount) {
            balance -= amount;
        }

        @Override
        public String toString() {
            return "Account{" +
                    "accountNo='" + accountNo + '\'' +
                    ", balance=" + balance +
                    '}';
        }
    }

    // -----------------------------
    // Functional Interface
    // -----------------------------
    @FunctionalInterface
    interface BusinessOperation<T> {
        T execute();
    }

    // -----------------------------
    // Transaction Manager
    // -----------------------------
    static class TransactionManager {

        public <T> T execute(BusinessOperation<T> operation) {

            System.out.println("\n=== Transaction Started ===");

            try {

                T result = operation.execute();

                System.out.println("Commit");

                return result;

            } catch (Exception e) {

                System.out.println("Rollback");
                System.out.println("Error : " + e.getMessage());

                throw e;

            } finally {

                System.out.println("Audit Logged");
                System.out.println("=== Transaction Ended ===");
            }
        }
    }

    // -----------------------------
    // Service
    // -----------------------------
    static class AccountService {

        private final Map<String, Account> db =
                new HashMap<>();

        public Account createAccount(
                String accountNo,
                double openingBalance) {

            if (db.containsKey(accountNo)) {
                throw new RuntimeException(
                        "Account already exists");
            }

            Account account =
                    new Account(accountNo,
                            openingBalance);

            db.put(accountNo, account);

            System.out.println(
                    "Account created : "
                            + accountNo);

            return account;
        }

        public void transferMoney(
                String from,
                String to,
                double amount) {

            Account source = db.get(from);
            Account destination = db.get(to);

            if (source == null || destination == null) {
                throw new RuntimeException(
                        "Invalid account");
            }

            if (source.getBalance() < amount) {
                throw new RuntimeException(
                        "Insufficient balance");
            }

            source.debit(amount);
            destination.credit(amount);

            System.out.println(
                    "Transferred "
                            + amount
                            + " from "
                            + from
                            + " to "
                            + to);
        }

        public Account findAccount(
                String accountNo) {

            return db.get(accountNo);
        }
    }

    // -----------------------------
    // Main
    // -----------------------------
    public static void main(String[] args) {

        AccountService service =
                new AccountService();

        TransactionManager tx =
                new TransactionManager();

        // returns Account
        Account a1 = tx.execute(
                () -> service.createAccount(
                        "A1001",
                        10000));

        Account a2 = tx.execute(
                () -> service.createAccount(
                        "A1002",
                        5000));

        System.out.println("\nReturned Objects:");
        System.out.println(a1);
        System.out.println(a2);

        // returns null (Void style)
        tx.execute(() -> {
            service.transferMoney(
                    "A1001",
                    "A1002",
                    2000);

            return null;
        });

        Account updated =
                tx.execute(() ->
                        service.findAccount(
                                "A1002"));

        System.out.println(
                "\nUpdated Account : "
                        + updated);
    }
}