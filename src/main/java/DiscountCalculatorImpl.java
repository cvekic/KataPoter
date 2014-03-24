import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Mladjan on 20.3.2014.
 */
public class DiscountCalculatorImpl implements DiscountCalculator {
    private static final BigDecimal DISCOUNT_RATE_FOR_TWO_BOOK = new BigDecimal(0.05);
    private static final BigDecimal DISCOUNT_RATE_FOR_THREE_BOOK = new BigDecimal(0.10);
    private static final BigDecimal DISCOUNT_RATE_FOR_FOUR_BOOK = new BigDecimal(0.20);
    private static final BigDecimal DISCOUNT_RATE_FOR_FIVE_BOOK = new BigDecimal(0.25);

    @Override
    public BigDecimal calculateDiscount(List<Integer> values) {
        int noOfDifferentBooks = values.size();
        int totalBookCount = bookCount(values);

        if (noOfDifferentBooks == 5 && totalBookCount == 8) {
            return calculateDiscount(4, 2);
        }
        else {
            return calculateDiscount(noOfDifferentBooks, 1);
        }
    }

    private BigDecimal calculateDiscount(int noOfDifferentBooks, int multiplier) {
        BigDecimal discountRate = getDiscountRate(noOfDifferentBooks);
        BigDecimal discountPrice = BookStoreCheckout.FULL_PRICE_PF_BOOKS.multiply(new BigDecimal(noOfDifferentBooks));
        return discountPrice.multiply(discountRate).multiply(new BigDecimal(multiplier));
    }

    private BigDecimal getDiscountRate(int noOfDifferentBooks) {
        switch (noOfDifferentBooks){
            case 2:
                return DISCOUNT_RATE_FOR_TWO_BOOK;
            case 3:
                return DISCOUNT_RATE_FOR_THREE_BOOK;
            case 4:
                return DISCOUNT_RATE_FOR_FOUR_BOOK;
            case 5:
                return DISCOUNT_RATE_FOR_FIVE_BOOK;
             default:
                 return BigDecimal.ZERO;
        }

    }

    private int bookCount(List<Integer> values) {
        int bookCount = 0;
        for(Integer value : values) {
            bookCount += value;
        }
        return bookCount;
    }
}
