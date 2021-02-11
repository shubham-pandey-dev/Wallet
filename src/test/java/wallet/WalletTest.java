package wallet;

import exception.NotEnoughMoneyInWalletException;
import money.Currency;
import money.Money;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class WalletTest {

    private final double ERROR = 0.000001;

    @Test
    void shouldBeAbleToPutMoneyInWallet() {
        Wallet wallet = new Wallet(Currency.RUPEE);
        Money money = new Money(17.0, Currency.RUPEE);

        wallet.put(money);
        double moneyInWallet = wallet.amountInPreferredCurrency();

        assertThat(moneyInWallet, is(closeTo(17, ERROR)));
    }

    @Test
    void shouldBeAbleToTakeMoneyFromWallet() {
        Wallet wallet = new Wallet(Currency.RUPEE);
        Money moneyPut = new Money(1, Currency.DOLLAR);
        Money moneyTake = new Money(50, Currency.RUPEE);

        wallet.put(moneyPut);

        assertDoesNotThrow(() -> wallet.take(moneyTake));
    }

    @Test
    void shouldBeAbleToTakeMoneyOnlyIfWalletHasEnoughMoney() {
        Wallet wallet = new Wallet(Currency.RUPEE);
        Money money = new Money(50, Currency.RUPEE);
        Money moneyTake1 = new Money(50, Currency.RUPEE);
        Money moneyTake2 = new Money(100, Currency.RUPEE);

        wallet.put(money);

        assertDoesNotThrow(() -> wallet.take(moneyTake1));
        assertThrows(NotEnoughMoneyInWalletException.class, () -> wallet.take(moneyTake2));
    }

    @Test
    void shouldBeAbleToAddAndTakeMoneyInDifferentCurrency() throws NotEnoughMoneyInWalletException {
        Wallet wallet = new Wallet(Currency.DOLLAR);
        Money money1 = new Money(100, Currency.DOLLAR);
        Money money2 = new Money(485, Currency.RUPEE);

        wallet.put(money1);
        wallet.take(money2);
        double moneyInWallet = wallet.amountIn(Currency.RUPEE);

        assertThat(moneyInWallet, is(closeTo(7000, ERROR)));
    }

    @Test
    void requirementTestOne() {
        Wallet wallet = new Wallet(Currency.RUPEE);
        Money money = new Money(50, Currency.RUPEE);
        Money money2 = new Money(1, Currency.DOLLAR);

        wallet.put(money);
        wallet.put(money2);
        double amount = wallet.amountInPreferredCurrency();

        assertThat(amount, is(closeTo(124.85, ERROR)));
    }

    @Test
    void requirementTestTwo() {
        Wallet wallet = new Wallet(Currency.DOLLAR);
        Money money1 = new Money(74.85, Currency.RUPEE);
        Money money2 = new Money(1, Currency.DOLLAR);
        Money money3 = new Money(149.7, Currency.RUPEE);

        wallet.put(money1)
                .put(money2)
                .put(money3);

        double money = wallet.amountIn(Currency.DOLLAR);
        assertThat(money, is(closeTo(4, ERROR)));
    }

}
