package zad1.op;

import java.math.BigDecimal;

public class Add implements Operation {
    @Override
    public BigDecimal execute(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }
}
