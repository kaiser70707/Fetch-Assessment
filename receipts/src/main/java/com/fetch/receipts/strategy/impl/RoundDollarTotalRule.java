package com.fetch.receipts.strategy.impl;

import com.fetch.receipts.models.Receipt;
import com.fetch.receipts.strategy.PointsRule;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RoundDollarTotalRule implements PointsRule {
    public int apply(Receipt receipt){
        try {
            BigDecimal total = new BigDecimal(receipt.getTotal());
            boolean isWhole = total.stripTrailingZeros().scale() <= 0;
            int points = isWhole ? 50 : 0;
            return points;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
