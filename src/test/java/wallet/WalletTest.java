package wallet;

import exception.NotEnoughMoneyInWalletException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WalletTest {

    @Test
    void shouldBeAbleToPutMoneyInWallet() {
        Wallet wallet = new Wallet(Currency.Rupees);

        wallet.put(17.0, Currency.Rupees);
        double money = wallet.amountInPreferredCurrency();

        assertEquals(17, money, 0.0000001);
    }

    @Test
    void shouldBeAbleToTakeMoneyFromWallet() {
        Wallet wallet = new Wallet(Currency.Rupees);

        wallet.put(100, Currency.Rupees);

        assertDoesNotThrow(() -> wallet.take(50, Currency.Rupees));
    }

    @Test
    void shouldBeAbleToTakeMoneyOnlyIfWalletHasEnoughMoney() {
        Wallet wallet = new Wallet(Currency.Rupees);

        wallet.put(100, Currency.Rupees);

        assertDoesNotThrow(() -> wallet.take(50, Currency.Rupees));
        assertThrows(NotEnoughMoneyInWalletException.class, () -> wallet.take(100, Currency.Rupees));
    }

    @Test
    void shouldBeAbleToAddAndTakeMoneyInDifferentCurrency() throws NotEnoughMoneyInWalletException {
        Wallet wallet = new Wallet(Currency.Dollars);

        wallet.put(100, Currency.Dollars);
        wallet.take(485, Currency.Rupees);
        double money = wallet.amountInCurrency(Currency.Rupees);

        assertEquals(7000, money, 0.00001);
    }

    @Test
    void requirementTestOne() {
        Wallet wallet = new Wallet(Currency.Rupees);

        wallet.put(50, Currency.Rupees);
        wallet.put(1, Currency.Dollars);
        double money = wallet.amountInPreferredCurrency();

        assertEquals(124.85, money, 0.00001);
    }

    @Test
    void requirementTestTwo() {
        Wallet wallet = new Wallet(Currency.Dollars);

        wallet.put(74.85, Currency.Rupees);
        wallet.put(1, Currency.Dollars);
        wallet.put(149.7, Currency.Rupees);
        double money = wallet.amountInPreferredCurrency();

        assertEquals(4, money, 0.00001);
    }

}
