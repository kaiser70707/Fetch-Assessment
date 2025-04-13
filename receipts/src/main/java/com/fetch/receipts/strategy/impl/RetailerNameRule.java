package com.fetch.receipts.strategy.impl;

import com.fetch.receipts.models.Receipt;
import com.fetch.receipts.strategy.PointsRule;
import org.springframework.stereotype.Component;

@Component
public class RetailerNameRule implements PointsRule {
    public int apply(Receipt receipt){
        return receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();
    }
}
