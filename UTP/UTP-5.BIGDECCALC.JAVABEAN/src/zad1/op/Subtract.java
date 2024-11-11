package zad1.op;

import java.math.BigDecimal;

public class Subtract implements Operation {
    @Override
    public BigDecimal execute(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }
}
