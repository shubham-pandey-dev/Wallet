package wallet;

import Money.Currency;
import Money.Money;
import exception.NoMoneyInWalletException;
import exception.NotEnoughMoneyInWalletException;

public class Wallet {

    private Money money;

    public Wallet(Currency preferredCurrency) {
        money = new Money(0.0, preferredCurrency);
    }

    public void put(double money, Currency currency) throws NoMoneyInWalletException {
        if (this.money == null) throw new NoMoneyInWalletException("Wallet is not created");
        money = convertCurrency(money, currency, this.money.getCurrency());
        this.money = new Money(this.money.getAmount() + money, this.money.getCurrency());
    }

    public void take(double money, Currency currency) throws NotEnoughMoneyInWalletException, NoMoneyInWalletException {
        if (this.money == null) throw new NoMoneyInWalletException("Wallet is not created");
        money = convertCurrency(money, currency, this.money.getCurrency());
        if (money > this.money.getAmount())
            throw new NotEnoughMoneyInWalletException("wallet.Wallet does not have enough money");
        this.money = new Money(this.money.getAmount() - money, this.money.getCurrency());
    }

    private double convertCurrency(double money, Currency from, Currency to) {
        return (money * from.inRupees) / to.inRupees;
    }

    public double amountInCurrency(Currency currency) throws NoMoneyInWalletException {
        if(money == null) throw new NoMoneyInWalletException("Wallet is not created");
        return (money.getAmount() * money.getCurrency().inRupees) / currency.inRupees;
    }

    public double amountInPreferredCurrency() throws NoMoneyInWalletException {
        if (money == null) throw new NoMoneyInWalletException("Wallet is not created");
        return money.getAmount();
    }

}

