package org.example.javafeatures;

public class BankApplication {

    @FunctionalInterface
    interface BusinessOperation {
        void execute() throws Exception;
    }

    static class RetryExecutor {

        public void execute(BusinessOperation operation) {

            int maxRetries = 3;

            for (int attempt = 1; attempt <= maxRetries; attempt++) {

                try {

                    System.out.println("\nAttempt : " + attempt);

                    operation.execute();

                    System.out.println("Operation Successful");
                    return;

                } catch (Exception e) {

                    System.out.println("Error : " + e.getMessage());

                    if (attempt == maxRetries) {
                        System.out.println("Retries Exhausted");
                    }
                }
            }
        }
    }

    static class AccountService {

        private int createAccountCallCount = 0;

        public void createAccount(String customerName,
                                  String accountType,
                                  double initialDeposit) {

            createAccountCallCount++;

            System.out.println(
                    "Creating account for "
                            + customerName
                            + " Type="
                            + accountType
                            + " Deposit="
                            + initialDeposit);

            if (initialDeposit < 1000) {
                throw new RuntimeException(
                        "Minimum deposit should be 1000");
            }

            // Simulate temporary DB failure
            if (createAccountCallCount < 3) {
                throw new RuntimeException(
                        "Database temporarily unavailable");
            }

            System.out.println(
                    "Account Created Successfully for "
                            + customerName);
        }
    }

    public static void main(String[] args) {

        RetryExecutor retryExecutor = new RetryExecutor();

        AccountService accountService =
                new AccountService();

        retryExecutor.execute(() ->
                accountService.createAccount(
                        "Pratik",
                        "SAVINGS",
                        5000
                ));
    }
}