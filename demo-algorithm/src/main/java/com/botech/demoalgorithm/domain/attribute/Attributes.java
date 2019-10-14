package com.botech.demoalgorithm.domain.attribute;

import java.io.Serializable;
import lombok.Data;

@Data
public class Attributes implements Serializable {

    private static final long serialVersionUID = 3388810799362286324L;

    private Integer age;
    private Integer gender;
    private Integer glass;
    private Integer mask;
    private Integer race;
    private Integer beard;
    private Integer emotion;
    private Integer eyeOpen;
    private Integer mouthOpen;
    
}
