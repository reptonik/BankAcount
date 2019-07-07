package bank.accounts;

import bank.exceptions.NotSupportedOperation;

/**
 * @author Or Kucher
 * @project BankAccount
 * @since 07-07-2019
 *
 * Implementation of {@link CheckingAccount}
 */
public class CheckingAccount extends Account{

    private double overdraftLimit = 0.0;

    public CheckingAccount(String owner, double balance) {
        super(owner, balance);
    }

    public CheckingAccount(String owner, double balance, double overdraftLimit) {
        super(owner, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }



    @Override
    public boolean withdrawal(double amount) {
        if(amount <= 0){
            System.out.println("Invalid amount: " + amount + " . Must be bigger then 0");
            return false;
        }
        double newBalance = getBalance() - amount;
        if(newBalance + overdraftLimit >= 0){
            setBalance(newBalance);
            System.out.println("New balance = " + getBalance());
            return true;
        }
        System.out.println("Not enough funds on:" + this);
        return false;
    }


    public double payInterest() throws NotSupportedOperation {
        throw new NotSupportedOperation(this.getClass());
    }

    public boolean cacheTransfer(Account from, double amount) throws NotSupportedOperation {

        if(from instanceof CheckingAccount){
            boolean withdrawal = from.withdrawal(amount);
            if(withdrawal){
                boolean deposit = this.deposit(amount);
                if(deposit){
                    return true;
                }else {
                    from.deposit(amount);
                }

            }
        }else{
            throw new NotSupportedOperation(from.getClass());
        }
        return false;
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +  super.toString() +
                "overdraftLimit=" + overdraftLimit +
                '}';
    }
}
