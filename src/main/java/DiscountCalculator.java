import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Mladjan on 21.3.2014.
 */
public interface DiscountCalculator {
    public BigDecimal calculateDiscount (List<Integer> values);
}
