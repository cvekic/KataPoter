import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mladjan on 21.3.2014.
 */
public class BookStoreCheckout {

    public static final BigDecimal FULL_PRICE_PF_BOOKS = new BigDecimal(8.00);
    Map<String, Integer> items = new HashMap<String, Integer>();
    DiscountCalculator discountCalculator = new DiscountCalculatorImpl();

    public void buy(String isbm) {
        Integer itemsCount = items.get(isbm);
        if (itemsCount == null) {
            itemsCount = 0;
        }
        items.put(isbm, itemsCount +1);
    }

    public BigDecimal total() {
        BigDecimal fullPrie = calculatePrice();
        BigDecimal dicount = discountCalculator.calculateDiscount(new ArrayList<Integer>(items.values()));
        return fullPrie.subtract(dicount);
    }

    private BigDecimal calculatePrice() {
        Integer booksTotal = countBooks();
        return FULL_PRICE_PF_BOOKS.multiply(new BigDecimal(booksTotal));
    }

    private Integer countBooks() {
        Integer booksCount = 0;
        for(Map.Entry<String, Integer> item : items.entrySet()) {
            booksCount += item.getValue();
        }
        return booksCount;
    }
}
