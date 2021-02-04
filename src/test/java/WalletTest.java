import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WalletTest {
    @Test
    void shouldBeAbleToPutMoneyInWallet() {
        Wallet wallet = new Wallet();

        wallet.put(17.0);
        double moneyInRupees = wallet.amount();

        assertEquals(17, moneyInRupees, 0.0000001);
    }

    @Test
    void shouldBeAbleToTakeMoneyFromWallet() {
        Wallet wallet = new Wallet();

        wallet.put(100);

        assertDoesNotThrow(() -> wallet.take(50));
    }

    @Test
    void shouldBeAbleToTakeMoneyOnlyIfWalletHasEnoughMoney() {
        Wallet wallet = new Wallet();

        wallet.put(100);

        assertDoesNotThrow(() -> wallet.take(50));
        assertThrows(NotEnoughMoneyInWalletException.class, () -> wallet.take(100));
    }

}
