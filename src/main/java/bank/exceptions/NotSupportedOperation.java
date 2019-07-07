package bank.exceptions;

/**
 * @author Or Kucher
 * @project BankAccount
 * @since 07-07-2019
 *
 * Exception for not supported operation
 */
public class NotSupportedOperation extends RuntimeException{

    public NotSupportedOperation(Class source) {
        super("Action not supported by " + source.getName() + " account type");
        System.out.println("Action not supported by " + source.getName() + " account type");
    }
}
