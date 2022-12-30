package com.guerra.service;

import java.io.IOException;
import java.util.List;

public interface FelScanService {

    List<String> scanAndListXml() throws IOException;
}
