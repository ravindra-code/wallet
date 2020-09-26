package exception;

import java.util.List;

public class UserNotFoundException extends WalletException{

    public UserNotFoundException(List<ErrorDetails> errors) {
        super(errors);
    }

    public UserNotFoundException(String s, List<ErrorDetails> errors) {
        super(s, errors);
    }

    public UserNotFoundException(String s, Throwable throwable, List<ErrorDetails> errors) {
        super(s, throwable, errors);
    }

    public UserNotFoundException(Throwable throwable, List<ErrorDetails> errors) {
        super(throwable, errors);
    }
}
