package com.guerra.service;


import com.guerra.model.dto.ConfigFelDto;
import lombok.extern.log4j.Log4j2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class ConfigFelServiceImpl implements ConfigFelService {

    private final String configFile;

    public ConfigFelServiceImpl(String configFile) {
        this.configFile = configFile;
    }

    /**
     * Obtiene la configuración de la FEL
     *
     * @return
     */
    @Override
    public ConfigFelDto readConfig() throws IOException {

        Properties properties = getProperties();
        log.info("Se ha leído el archivo de configuración " + configFile);

        return ConfigFelDto.builder()
                .headerLogoName(properties.getProperty("header.logo.name"))
                .footerSocialFacebook(properties.getProperty("footer.social.facebook"))
                .footerContactTels(properties.getProperty("footer.contact.tels"))
                .footerContactEmail(properties.getProperty("footer.contact.email"))
                .build();
    }

    /**
     * Actualiza la configuración de la FEL
     *
     * @param configFelDto
     */
    @Override
    public void saveConfig(ConfigFelDto configFelDto) throws IOException {

        Properties propertiesRead = getProperties();
        propertiesRead.setProperty("header.logo.name", configFelDto.getHeaderLogoName());
        propertiesRead.setProperty("footer.social.facebook", configFelDto.getFooterSocialFacebook());
        propertiesRead.setProperty("footer.contact.tels", configFelDto.getFooterContactTels());
        propertiesRead.setProperty("footer.contact.email", configFelDto.getFooterContactEmail());

        propertiesRead.store(new FileWriter(configFile), "Escritura de configuración");
        log.info("Se ha actualizado el archivo de configuración " + configFile);

    }

    private Properties getProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(configFile));
        return properties;
    }
}