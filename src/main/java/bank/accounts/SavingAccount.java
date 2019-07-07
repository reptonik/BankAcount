package bank.accounts;

import bank.exceptions.NotSupportedOperation;

/**
 * @author Or Kucher
 * @project BankAccount
 * @since 07-07-2019
 *
 * Implementation of {@link SavingAccount}
 */
public class SavingAccount extends Account{

    private double interestRate = 0.00;

    public SavingAccount(String owner, double balance) {
        super(owner, balance);
    }

    public SavingAccount(String owner, double balance, double interestRate) {
        super(owner, balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double payInterest() throws NotSupportedOperation {
        double newBalance = getBalance();
        newBalance *= (1+interestRate);
        setBalance(newBalance);
        System.out.println("New balance = " + getBalance());
        return getBalance();
    }

    public boolean cacheTransfer(Account from, double amount) throws NotSupportedOperation {
        throw new NotSupportedOperation(this.getClass());
    }

    @Override
    public String toString() {
        return "SavingAccount{" + super.toString() +
                "interestRate=" + interestRate +
                '}';
    }
}
