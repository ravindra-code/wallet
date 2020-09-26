package exception;

import java.util.List;

public class InsufficientBalanceException extends WalletException{

    public InsufficientBalanceException(List<ErrorDetails> errors) {
        super(errors);
    }

    public InsufficientBalanceException(String s, List<ErrorDetails> errors) {
        super(s, errors);
    }

    public InsufficientBalanceException(String s, Throwable throwable, List<ErrorDetails> errors) {
        super(s, throwable, errors);
    }

    public InsufficientBalanceException(Throwable throwable, List<ErrorDetails> errors) {
        super(throwable, errors);
    }
}
