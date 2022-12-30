package com.guerra.service;


import com.guerra.util.AppProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FelScanServiceImplTest {

    private FelScanService felScanService;

    @BeforeEach
    void setUp() {
        felScanService = new FelScanServiceImpl(AppProperties.getPathInputDirectory());
    }

    @Test
    void scan() {
        try {
            felScanService.scanAndListXml().forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}