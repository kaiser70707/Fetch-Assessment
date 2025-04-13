package com.fetch.receipts.strategy.impl;

import com.fetch.receipts.models.Item;
import com.fetch.receipts.models.Receipt;
import com.fetch.receipts.strategy.PointsRule;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class DescriptionLengthRule implements PointsRule {
    public int apply(Receipt receipt){
        int points = 0;

        for (Item item : receipt.getItems()) {
            if (item.getShortDescription() == null || item.getPrice() == null)
                continue;

            String desc = item.getShortDescription().trim();
            int length = desc.replaceAll("\\s+", "").length();

            if (length % 3 == 0) {
                try {
                    BigDecimal price = new BigDecimal(item.getPrice());
                    int itemPoints = price.multiply(new BigDecimal("0.2")).setScale(0, RoundingMode.UP).intValue();
                    points += itemPoints;
                } catch (NumberFormatException ignored) {}
            }
        }

        return points;
    }
}
