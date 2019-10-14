package com.botech.demoalgorithm.domain.attribute;

import java.io.Serializable;
import lombok.Data;

@Data
public class AttrResult implements Serializable {

    private static final long serialVersionUID = -2069966857541475775L;

    private Long time;
    private Long msTime;
    private String taskIdx;
    private Integer trackIdx;
    private Integer frameIdx;
    private Attributes attributes;
    
}