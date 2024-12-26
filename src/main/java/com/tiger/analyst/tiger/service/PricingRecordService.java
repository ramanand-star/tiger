package com.tiger.analyst.tiger.service;

import com.tiger.analyst.tiger.model.PricingRecord;
import com.tiger.analyst.tiger.repository.PricingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PricingRecordService {

    @Autowired
    private PricingRecordRepository repository;

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public PricingRecord save(PricingRecord record) {
        return repository.save(record);
    }

    public List<PricingRecord> searchByStoreId(String storeId) {
        return repository.findByStoreId(storeId);
    }

    public List<PricingRecord> searchBySku(String sku) {
        return repository.findBySku(sku);
    }

    public List<PricingRecord> searchByProductName(String productName) {
        return repository.findByProductName(productName);
    }

    public List<PricingRecord> searchByDate(LocalDate date) {
        return repository.findByDate(date);
    }
    public Optional<PricingRecord> findById(Long id) {
        return repository.findById(id);
    }

//    public Object search(String s) {
//    }
}