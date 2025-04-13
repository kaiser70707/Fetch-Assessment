package com.fetch.receipts.controllers;

import com.fetch.receipts.models.PointsResponse;
import com.fetch.receipts.models.Receipt;
import com.fetch.receipts.models.ReceiptReponse;
import com.fetch.receipts.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipts")
@RequiredArgsConstructor
public class ReceiptController {
    private final ReceiptService receiptService;

    @PostMapping("/process")
    public ResponseEntity<?> processReceipt(@RequestBody Receipt receipt){
        try{
            String id = receiptService.processReceipt(receipt);
            return ResponseEntity.ok(new ReceiptReponse(id));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Intenal Error");
        }
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<?> getPoints(@PathVariable String id) {
        Integer points = receiptService.getPoints(id);
        if (points == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Receipt not found");
        return ResponseEntity.ok(new PointsResponse(points));
    }
}
