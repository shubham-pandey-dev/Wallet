package wallet;

import exception.NotEnoughMoneyInWalletException;

public class Wallet {

    private Double money;
    private Currency currency;

    public Wallet(Currency currency) {
        this.currency = currency;
        money = 0.0;
    }

    public void put(double money, Currency currency) {
        this.money += (money * currency.inRupees) / this.currency.inRupees;
    }

    public double amountInPreferredCurrency() {
        return money;
    }

    public void take(double money, Currency currency) throws NotEnoughMoneyInWalletException {
        money = (money * currency.inRupees) / this.currency.inRupees;
        if (money > this.money) throw new NotEnoughMoneyInWalletException("wallet.Wallet does not have enough money");
        this.money -= money;
    }

    public double amountInCurrency(Currency currency) {
        return money * this.currency.inRupees / currency.inRupees;
    }
}

