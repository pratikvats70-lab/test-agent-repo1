package org.example.javafeatures;

import java.util.HashMap;
import java.util.Map;

public class BankingWithoutLambda {

    static class Account {
        String accountNo;
        double balance;

        Account(String accountNo, double balance) {
            this.accountNo = accountNo;
            this.balance = balance;
        }
    }

    static class AccountService {

        Map<String, Account> db = new HashMap<>();

        public void createAccount(String accountNo, double amount) {

            // COMMON INFRASTRUCTURE
            System.out.println("Transaction Started");

            try {

                if (db.containsKey(accountNo)) {
                    throw new RuntimeException("Account already exists");
                }

                db.put(accountNo, new Account(accountNo, amount));

                System.out.println("Account Created : " + accountNo);

            } catch (Exception e) {

                System.out.println("Rollback");
                System.out.println(e.getMessage());

            } finally {

                System.out.println("Audit Logged");
                System.out.println("Transaction Ended\n");
            }
        }

        public void transferMoney(String from,
                                  String to,
                                  double amount) {

            // SAME INFRASTRUCTURE AGAIN
            System.out.println("Transaction Started");

            try {

                Account src = db.get(from);
                Account dest = db.get(to);

                if (src.balance < amount) {
                    throw new RuntimeException("Insufficient balance");
                }

                src.balance -= amount;
                dest.balance += amount;

                System.out.println("Transfer Success");

            } catch (Exception e) {

                System.out.println("Rollback");
                System.out.println(e.getMessage());

            } finally {

                System.out.println("Audit Logged");
                System.out.println("Transaction Ended\n");
            }
        }

        public void printAccounts() {

            // SAME INFRASTRUCTURE AGAIN
            System.out.println("Transaction Started");

            try {

                db.values().forEach(a ->
                        System.out.println(
                                a.accountNo + " -> " + a.balance));

            } catch (Exception e) {

                System.out.println("Rollback");

            } finally {

                System.out.println("Audit Logged");
                System.out.println("Transaction Ended\n");
            }
        }
    }

    public static void main(String[] args) {

        AccountService service = new AccountService();

        service.createAccount("A1", 10000);
        service.createAccount("A2", 5000);

        service.transferMoney("A1", "A2", 2000);

        service.printAccounts();
    }
}