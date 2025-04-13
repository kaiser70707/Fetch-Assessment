package com.fetch.receipts.service;

import com.fetch.receipts.models.Item;
import com.fetch.receipts.models.Receipt;
import com.fetch.receipts.strategy.PointsRule;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ReceiptService {
    private final Map<String, Integer> receiptPointsStore = new ConcurrentHashMap<>();
    private final List<PointsRule> rules;

    public ReceiptService(List<PointsRule> rules) {
        this.rules = rules;
    }

    public String processReceipt(Receipt receipt){
        validateReceipt(receipt);
        int points = rules.stream().mapToInt(rule -> rule.apply(receipt)).sum();
        String id = UUID.randomUUID().toString();
        receiptPointsStore.put(id,points);
        return id;
    }

    public Integer getPoints(String id){
        return receiptPointsStore.get(id);
    }

    private void validateReceipt(Receipt receipt){
        if (receipt == null || receipt.getRetailer() == null || receipt.getPurchaseDate() == null ||
                receipt.getPurchaseTime() == null || receipt.getTotal() == null || receipt.getItems() == null) {
            throw new IllegalArgumentException("Invalid receipt: missing required fields");
        }

        for(Item item : receipt.getItems()){
            if(item == null || item.getShortDescription() == null || item.getPrice() == null){
                throw new IllegalArgumentException("Invalid item : Missing fields in the receipt");
            }
        }
    }
}

