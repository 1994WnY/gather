package com.botech.demoalgorithm.domain;

import java.io.Serializable;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "data.frameheader")
@Data
public class FrameHeader implements Serializable {

    private static final long serialVersionUID = -968219643300864102L;

    private Long index;
    private Long time;
    private Integer rebackInfo;
    private Integer flag;
    private Integer width;
    private Integer height;
    private Integer dataLen;
    private Integer data;

}
