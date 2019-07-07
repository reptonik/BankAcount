import bank.accounts.Account;
import bank.accounts.CheckingAccount;
import bank.accounts.SavingAccount;
import bank.exceptions.NotSupportedOperation;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Or Kucher
 * @project bank-account
 * @since 07-07-2019
 */
public class AccountTesting {

    private final Account savingAccount = new SavingAccount("owner1", 1000, 0.04);
    private final Account checkAccount = new CheckingAccount("owner2", 1000, 1000);
    private final Account transferAccount = new CheckingAccount("owner3", 400, 100);

    @Test
    public void testValidDeposit(){

        double amount = 200;
        double balance = savingAccount.getBalance();
        Assert.assertTrue(savingAccount.deposit(amount));
        Assert.assertTrue(savingAccount.getBalance() == amount + balance);

        balance = checkAccount.getBalance();
        Assert.assertTrue(checkAccount.deposit(200));
        Assert.assertTrue(checkAccount.getBalance() == amount + balance);

    }

    @Test
    public void testNotValidDeposit(){
        Assert.assertFalse(savingAccount.deposit(-200));
        Assert.assertFalse(checkAccount.deposit(0));
    }

    @Test
    public void testValidWithdrawal(){
        double amount = 200;
        double balance = savingAccount.getBalance();
        Assert.assertTrue(savingAccount.withdrawal(200));
        Assert.assertTrue(savingAccount.getBalance() == balance - amount);

        balance = checkAccount.getBalance();
        amount = 2000;
        Assert.assertTrue(checkAccount.withdrawal(amount));
        Assert.assertTrue(checkAccount.getBalance() == balance - amount);
    }

    @Test
    public void testNotValidWithdrawal(){
        Assert.assertFalse(savingAccount.withdrawal(-200));
        Assert.assertFalse(savingAccount.withdrawal(1001));

        Assert.assertFalse(checkAccount.withdrawal(-200));
        Assert.assertFalse(checkAccount.withdrawal(2001));

    }

    @Test
    public void testInterest(){
        Assert.assertEquals(1040, savingAccount.payInterest(), 0);
    }

    @Test(expected = NotSupportedOperation.class)
    public void testNotSupportedOperationCheckAccount(){
        checkAccount.payInterest();
    }

    @Test(expected = NotSupportedOperation.class)
    public void testNotSupportedOperationSaveAccount(){
        savingAccount.cacheTransfer(transferAccount, 100);
    }

    @Test
    public void testTransferCache(){
        double targetBalance = checkAccount.getBalance();
        double amount = 500;
        double sourceBalance = transferAccount.getBalance();

        checkAccount.cacheTransfer(transferAccount, amount);
        Assert.assertTrue(checkAccount.getBalance() == targetBalance + amount);
        Assert.assertTrue(transferAccount.getBalance() == sourceBalance - amount);
    }

    @Test
    public void testInvalidTransferCacheRollBack(){
        double targetBalance = checkAccount.getBalance();
        double amount = 501;
        double sourceBalance = transferAccount.getBalance();

        checkAccount.cacheTransfer(transferAccount, amount);
        Assert.assertTrue(checkAccount.getBalance() == targetBalance);
        Assert.assertTrue(transferAccount.getBalance() == sourceBalance);
    }
}
