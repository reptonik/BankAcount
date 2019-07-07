package bank.accounts;

import bank.services.AccountService;

/**
 * @author Or Kucher
 * @project BankAccount
 * @since 07-07-2019
 *
 * Base account Class
 */
public abstract class Account implements AccountService {

    //For real need to be object
    private String owner;
    private double balance;

    public Account(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public boolean deposit(double amount) {
        if(amount > 0){
            double newBalance = getBalance() + amount;
            setBalance(newBalance);
            System.out.println("New balance = " + getBalance());
            return true;
        }
        System.out.println("Invalid amount: " + amount + " . Must be bigger then 0");
        return false;
    }

    public boolean withdrawal(double amount) {
        if(amount <= 0){
            System.out.println("Invalid amount: " + amount + " . Must be bigger then 0");
            return false;
        }
        double newBalance = getBalance() - amount;
        if(newBalance >=0){
            setBalance(newBalance);
            System.out.println("New balance = " + getBalance());
            return true;
        }
        System.out.println("Not enough funds on:" + this);
        return false;
    }


    @Override
    public String toString() {
        return "Account{" +
                "owner='" + owner + '\'' +
                ", balance=" + balance +
                '}';
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


}
