package com.guerra.service;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class FelScanServiceImpl implements FelScanService {

    private final String pathToScan;

    public FelScanServiceImpl(String pathToScan) {
        this.pathToScan = pathToScan;
    }


    @Override
    public List<String> scanAndListXml() throws IOException {
        log.info("Scaneando directorio: " + pathToScan);

        try (var stream = Files.list(Path.of(pathToScan))) {
            return stream.map(path -> path.getFileName().toString())
                    .filter(s -> s.endsWith(".xml"))
                    .collect(Collectors.toList());
        }
    }
}
