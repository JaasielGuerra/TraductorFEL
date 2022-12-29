package com.guerra.service;

import com.guerra.model.dto.ConfigFelDto;

import java.io.IOException;

public interface ConfigFelService {

    /**
     * Obtiene la configuración de la FEL
     * @return
     */
    ConfigFelDto readConfig() throws IOException;

    /**
     * Actualiza la configuración de la FEL
     * @param configFelDto
     */
    void saveConfig(ConfigFelDto configFelDto) throws IOException;

}
