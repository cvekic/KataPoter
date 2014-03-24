import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mladjan on 20.3.2014.
 */
public class BookStoreCheckoutTest {

    MathContext mc = new MathContext(4, RoundingMode.HALF_UP);
    BookStoreCheckout store;

    @Before
    public void setUp() {
        store = new BookStoreCheckout();
    }

    @Test
    public void mustReturn0EuroForZeroBooks() {
        assertEquals(new BigDecimal(0.00).round(mc), store.total().round(mc));
    }

    @Test
    public void mustReturn8EuroForOneBooks() {
        store.buy("1");
        assertEquals(new BigDecimal(8.00).round(mc), store.total().round(mc));
    }

    @Test
    public void mustReturn16EuroForTwoSameBooks() {
        store.buy("1");
        store.buy("1");
        assertEquals(new BigDecimal(16.00).round(mc), store.total().round(mc));

    }
    @Test
    public void mustReturn21_6EuroForThreeDifferentBooks() {
        store.buy("1");
        store.buy("2");
        store.buy("5");
        assertEquals(new BigDecimal(21.60).round(mc), store.total().round(mc));

    }

    @Test
    public void mustReturn51_2EuroFor8BooksAnd5Different() {
        store.buy("1");
        store.buy("1");
        store.buy("2");
        store.buy("2");
        store.buy("3");
        store.buy("3");
        store.buy("4");
        store.buy("5");
        assertEquals(new BigDecimal(51.2).round(mc), store.total().round(mc));

    }


}
