package com.botech.demoalgorithm.domain.distinguish;

import java.io.Serializable;
import lombok.Data;

@Data
public class FaceAngle implements Serializable {

    private static final long serialVersionUID = -1261712155527298242L;

    private Float yaw;
    private Float pitch;
    private Float roll;

}
