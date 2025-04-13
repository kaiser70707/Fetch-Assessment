package com.fetch.receipts.strategy.impl;

import com.fetch.receipts.models.Receipt;
import com.fetch.receipts.strategy.PointsRule;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class OddDayPurchaseRule implements PointsRule {
    public int apply(Receipt receipt){
        try {
            int day = LocalDate.parse(receipt.getPurchaseDate()).getDayOfMonth();
            return (day % 2 == 1) ? 6 : 0;
        } catch (DateTimeParseException e) {
            return 0;
        }
    }
}
