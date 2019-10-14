package com.botech.demoalgorithm.domain.distinguish;

import java.io.Serializable;
import lombok.Data;

@Data
public class Users implements Serializable {

    private static final long serialVersionUID = 4132933926766372649L;

    private Float score;
    private String user_idx;

}
