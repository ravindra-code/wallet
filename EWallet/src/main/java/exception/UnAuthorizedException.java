package exception;

import java.util.List;

public class UnAuthorizedException extends WalletException {

    public UnAuthorizedException(List<ErrorDetails> errors) {
        super(errors);
    }

    public UnAuthorizedException(String s, List<ErrorDetails> errors) {
        super(s, errors);
    }

    public UnAuthorizedException(String s, Throwable throwable, List<ErrorDetails> errors) {
        super(s, throwable, errors);
    }

    public UnAuthorizedException(Throwable throwable, List<ErrorDetails> errors) {
        super(throwable, errors);
    }
}
