package wallet;

import money.Currency;
import money.Money;
import exception.NotEnoughMoneyInWalletException;

public class Wallet {

    private Money money;

    public Wallet(Currency preferredCurrency) {
        money = new Money(0.0, preferredCurrency);
    }

    public Wallet put(Money moneyToAdd) {
        moneyToAdd = convertCurrency(moneyToAdd, this.money.getCurrency());
        this.money = new Money(this.money.getAmount() + moneyToAdd.getAmount(), this.money.getCurrency());
        return this;
    }

    public Wallet take(Money moneyToTake) throws NotEnoughMoneyInWalletException {
        moneyToTake = convertCurrency(moneyToTake, this.money.getCurrency());
        if (moneyToTake.getAmount() > this.money.getAmount())
            throw new NotEnoughMoneyInWalletException("Wallet does not have enough money");
        this.money = new Money(this.money.getAmount() - moneyToTake.getAmount(), this.money.getCurrency());
        return this;
    }

    private Money convertCurrency(Money money, Currency to) {
        return new Money((money.getAmount() * money.getCurrency().inRupees) / to.inRupees, to);
    }

    public double amountIn(Currency currency) {
        return (money.getAmount() * money.getCurrency().inRupees) / currency.inRupees;
    }

    public double amountInPreferredCurrency() {
        return money.getAmount();
    }

}

