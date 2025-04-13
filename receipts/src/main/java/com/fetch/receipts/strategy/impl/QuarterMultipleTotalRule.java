package com.fetch.receipts.strategy.impl;

import com.fetch.receipts.models.Receipt;
import com.fetch.receipts.strategy.PointsRule;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class QuarterMultipleTotalRule implements PointsRule {
    public int apply(Receipt receipt){
        try {
            BigDecimal total = new BigDecimal(receipt.getTotal());
            return total.remainder(new BigDecimal("0.25")).compareTo(BigDecimal.ZERO) == 0 ? 25 : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
