package com.tiger.analyst.tiger.controllers;

import com.tiger.analyst.tiger.model.PricingRecord;
import com.tiger.analyst.tiger.service.PricingRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pricing")
@CrossOrigin
public class PricingRecordController {

    @Autowired
    private PricingRecordService service;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println("Uploading file: " + file.getOriginalFilename());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<PricingRecord> records = reader.lines().skip(1).map(line -> {
                String[] fields = line.split(",");
                if (fields.length < 5) {
                    throw new IllegalArgumentException("Invalid CSV format");
                }
                PricingRecord record = new PricingRecord();
                record.setStoreId(fields[0].trim());
                record.setSku(fields[1].trim());
                record.setProductName(fields[2].trim());
                record.setPrice(Double.parseDouble(fields[3].trim()));
                record.setDate(LocalDate.parse(fields[4].trim(), formatter));
                return record;
            }).collect(Collectors.toList());
            records.forEach(service::save);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            System.out.println("Error processing file: " + e.getMessage());
            return ResponseEntity.status(500).body("Error processing file: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    public List<PricingRecord> search(@RequestParam(required = false) String storeId,
                                      @RequestParam(required = false) String sku,
                                      @RequestParam(required = false) String productName,
                                      @RequestParam(required = false) LocalDate date) {
        if (storeId != null) {
            return service.searchByStoreId(storeId);
        } else if (sku != null) {
            return service.searchBySku(sku);
        } else if (productName != null) {
            return service.searchByProductName(productName);
        } else if (date != null) {
            return service.searchByDate(date);
        } else {
            return List.of();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PricingRecord> updateRecord(@PathVariable Long id, @RequestBody PricingRecord updatedRecord) {
        System.out.println("Updating record with ID: " + id);
        return service.findById(id).map(record -> {
            record.setStoreId(updatedRecord.getStoreId());
            record.setSku(updatedRecord.getSku());
            record.setProductName(updatedRecord.getProductName());
            record.setPrice(updatedRecord.getPrice());
            record.setDate(updatedRecord.getDate());
            return ResponseEntity.ok(service.save(record));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
