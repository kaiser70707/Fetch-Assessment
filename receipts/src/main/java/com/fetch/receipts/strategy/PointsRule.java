package com.fetch.receipts.strategy;

import com.fetch.receipts.models.Receipt;

public interface PointsRule {
    int apply(Receipt receipt);
}
