package wallet;

import exception.NotEnoughMoneyInWalletException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WalletTest {

    @Test
    void shouldBeAbleToPutMoneyInWallet() {
        Wallet wallet = new Wallet();

        wallet.put(17.0, Currency.Rupees);
        double moneyInRupees = wallet.amount(Currency.Rupees);

        assertEquals(17, moneyInRupees, 0.0000001);
    }

    @Test
    void shouldBeAbleToTakeMoneyFromWallet() {
        Wallet wallet = new Wallet();

        wallet.put(100, Currency.Rupees);

        assertDoesNotThrow(() -> wallet.take(50, Currency.Rupees));
    }

    @Test
    void shouldBeAbleToTakeMoneyOnlyIfWalletHasEnoughMoney() {
        Wallet wallet = new Wallet();

        wallet.put(100, Currency.Rupees);

        assertDoesNotThrow(() -> wallet.take(50, Currency.Rupees));
        assertThrows(NotEnoughMoneyInWalletException.class, () -> wallet.take(100, Currency.Rupees));
    }

    @Test
    void shouldBeAbleToAddAndTakeMoneyInDifferentCurrency() throws NotEnoughMoneyInWalletException {
        Wallet wallet = new Wallet();

        wallet.put(100, Currency.Dollars);
        wallet.take(485, Currency.Rupees);
        double moneyInRupees = wallet.amount(Currency.Rupees);

        assertEquals(7000, moneyInRupees, 0.00001);
    }

}
