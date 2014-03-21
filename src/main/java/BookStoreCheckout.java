import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mladjan on 21.3.2014.
 */
public class BookStoreCheckout {

    DiscountCalculator discountCalculator = new DiscountCalculatorImpl();

    public static final BigDecimal PRICE_OF_BOOK = new BigDecimal(8.00);

    private Map<String, Integer> items = new HashMap<String, Integer>();


    public void buy(String isbn) {
        Integer itemCount = items.get(isbn);
        if(itemCount == null) {
            itemCount = 0;
        }
        items.put(isbn, itemCount + 1);
    }


    public BigDecimal total() {
        BigDecimal fullPrice = calculateFullPrice();
        BigDecimal discountPrice = discountCalculator.calculateDiscount(new ArrayList<Integer>(items.values()));
        return fullPrice.subtract(discountPrice);
    }

    private BigDecimal calculateFullPrice() {
        int bookCount = totalBookCount();
        return PRICE_OF_BOOK.multiply(new BigDecimal(bookCount));

    }

    private int totalBookCount() {
        int bookCount = 0;
        for (Map.Entry<String, Integer> item : items.entrySet()) {
            bookCount += item.getValue();
        }
        return bookCount;

    }
}
