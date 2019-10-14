package com.botech.demoalgorithm.domain.distinguish;

import java.io.Serializable;
import lombok.Data;

@Data
public class FaceRect implements Serializable {

    private static final long serialVersionUID = 3389726124323355916L;

    private Integer left;
    private Integer top;
    private Integer right;
    private Integer bottom;
    
}
