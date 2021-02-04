
public class Wallet {

    private Double money;

    public Wallet() {
        money = 0.0;
    }

    public void put(double money) {
        this.money += money;
    }

    public double amount() {
        return money;
    }

    public void take(double money) throws NotEnoughMoneyInWalletException {
        if(money > this.money) throw new NotEnoughMoneyInWalletException("wallet.Wallet does not have enough money");
        this.money -= money;
    }
}
