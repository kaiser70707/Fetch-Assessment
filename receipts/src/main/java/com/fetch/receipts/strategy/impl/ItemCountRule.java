package com.fetch.receipts.strategy.impl;

import com.fetch.receipts.models.Receipt;
import com.fetch.receipts.strategy.PointsRule;
import org.springframework.stereotype.Component;

@Component
public class ItemCountRule implements PointsRule {
    public int apply(Receipt receipt){
        return (receipt.getItems().size() / 2) * 5;
    }
}
