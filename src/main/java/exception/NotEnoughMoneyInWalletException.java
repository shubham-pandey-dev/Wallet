package exception;

public class NotEnoughMoneyInWalletException extends Exception{
    public NotEnoughMoneyInWalletException(String message) {
        super(message);
    }
}
