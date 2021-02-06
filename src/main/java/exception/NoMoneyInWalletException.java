package exception;

public class NoMoneyInWalletException extends Exception{
    public NoMoneyInWalletException(String message) {
        super(message);
    }
}
