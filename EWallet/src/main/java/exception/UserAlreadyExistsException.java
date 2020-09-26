package exception;

import java.util.List;

public class UserAlreadyExistsException extends WalletException {

    public UserAlreadyExistsException(List<ErrorDetails> errors) {
        super(errors);
    }

    public UserAlreadyExistsException(String s, List<ErrorDetails> errors) {
        super(s, errors);
    }

    public UserAlreadyExistsException(String s, Throwable throwable, List<ErrorDetails> errors) {
        super(s, throwable, errors);
    }

    public UserAlreadyExistsException(Throwable throwable, List<ErrorDetails> errors) {
        super(throwable, errors);
    }
}
