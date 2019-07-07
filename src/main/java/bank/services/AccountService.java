package bank.services;

import bank.accounts.Account;
import bank.exceptions.NotSupportedOperation;

/**
 * @author Or Kucher
 * @project BankAccount
 * @since 07-07-2019
 *
 * Define account services
 */
public interface AccountService {

    boolean deposit(double amount);
    boolean withdrawal(double amount);

    double payInterest() throws NotSupportedOperation;
    boolean cacheTransfer(Account from, double amount) throws NotSupportedOperation;

}
