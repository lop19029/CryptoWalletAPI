package gft.cryptoWallet.exception;

public class CryptoWalletException extends RuntimeException{
    private String message;

    public CryptoWalletException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {this.message = message;}

}
