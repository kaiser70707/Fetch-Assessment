package com.fetch.receipts.strategy.impl;

import com.fetch.receipts.models.Receipt;
import com.fetch.receipts.strategy.PointsRule;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

@Component
public class AfterNoonPurchaseRule implements PointsRule {
    public int apply(Receipt receipt){
        try {
            LocalTime time = LocalTime.parse(receipt.getPurchaseTime());
            return (!time.isBefore(LocalTime.of(14, 0)) && time.isBefore(LocalTime.of(16, 0))) ? 10 : 0;
        } catch (DateTimeParseException e) {
            return 0;
        }
    }
}
