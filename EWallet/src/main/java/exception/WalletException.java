package exception;

import java.util.List;

public class WalletException extends Exception{

    private List<ErrorDetails> errors;

    public WalletException(List<ErrorDetails> errors) {
        this.errors = errors;
    }

    public WalletException(String s, List<ErrorDetails> errors) {
        super(s);
        this.errors = errors;
    }

    public WalletException(String s, Throwable throwable, List<ErrorDetails> errors) {
        super(s, throwable);
        this.errors = errors;
    }

    public WalletException(Throwable throwable, List<ErrorDetails> errors) {
        super(throwable);
        this.errors = errors;
    }

    public List<ErrorDetails> getErrors() {
        return errors;
    }
}
