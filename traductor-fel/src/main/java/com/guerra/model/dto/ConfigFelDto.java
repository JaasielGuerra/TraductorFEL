package com.guerra.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ConfigFelDto {
    private String headerLogoName;
    private String footerSocialFacebook;
    private String footerContactTels;
    private String footerContactEmail;
}
