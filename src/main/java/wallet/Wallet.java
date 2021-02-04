package wallet;

import exception.NotEnoughMoneyInWalletException;

public class Wallet {

    private Double money;

    public Wallet() {
        money = 0.0;
    }

    public void put(double money, Currency currency) {
        this.money += money * currency.inRupees;
    }

    public double amount(Currency preferredCurrency) {
        return money / preferredCurrency.inRupees;
    }

    public void take(double money, Currency currency) throws NotEnoughMoneyInWalletException {
        if(money > this.money) throw new NotEnoughMoneyInWalletException("wallet.Wallet does not have enough money");
        this.money -= money;
    }
}

