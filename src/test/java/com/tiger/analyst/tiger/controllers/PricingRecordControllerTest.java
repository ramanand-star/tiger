package com.tiger.analyst.tiger.controllers;

import com.tiger.analyst.tiger.model.PricingRecord;
import com.tiger.analyst.tiger.service.PricingRecordService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.xml.crypto.Data;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static java.lang.reflect.Array.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PricingRecordController.class)
class PricingRecordControllerTest {
    @MockBean
    private PricingRecordService service;
    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        doNothing().when(service).save(new PricingRecord());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void uploadFile() throws Exception {
//        String csvContent = "storeId,sku,productName,price,date\n" +
//                "1,SKU123,Product Name,10.0,01/01/21";
//        MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", csvContent.getBytes(StandardCharsets.UTF_8));
//
//        mockMvc.perform(multipart("/pricing/upload")
//                        .file(file)
//                        .contentType(MediaType.MULTIPART_FORM_DATA))
//                .andExpect(status().isOk());
    }

    @Test
    void search() throws Exception {
        // Mock the service method
//        LocalDate date = LocalDate.of(2021, 1, 1);
//        List<PricingRecord> records = Collections.singletonList(new PricingRecord(1L, "SKU123", "Product Name","", 10.0, date));
//        when(service.search(anyString())).thenReturn(records);
//
//        // Perform the request
//        mockMvc.perform(get("/pricing/search")
//                        .param("query", "Product Name")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//                .andExpect(jsonPath("$[0].sku").value("SKU123"))
//                .andExpect(jsonPath("$[0].productName").value("Product Name"))
//                .andExpect(jsonPath("$[0].price").value(10.0))
//                .andExpect(jsonPath("$[0].date").value("2021-01-01"));

    }

    @Test
    void updateRecord() {
    }
}