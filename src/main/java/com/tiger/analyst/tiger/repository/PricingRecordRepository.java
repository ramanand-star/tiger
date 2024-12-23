package com.tiger.analyst.tiger.repository;

import com.tiger.analyst.tiger.model.PricingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PricingRecordRepository extends JpaRepository<PricingRecord, Long> {
    List<PricingRecord> findByStoreId(String storeId);
    List<PricingRecord> findBySku(String sku);
    List<PricingRecord> findByProductName(String productName);
    List<PricingRecord> findByDate(LocalDate date);
}
