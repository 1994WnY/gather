package com.botech.demoalgorithm.domain.capture;

import java.io.Serializable;
import lombok.Data;

@Data
public class FaceInfo implements Serializable {

    private static final long serialVersionUID = 3538832850703416804L;

    private Integer trackIdx;
    private Float qualityScore;
    private Integer left;
    private Integer top;
    private Integer right;
    private Integer bottom;
    private Integer end;
    private Float yaw;
    private Float pitch;
    private Float roll;
    private Integer objLeft;
    private Integer objTop;
    private Integer objRight;
    private Integer objBottom;
    private String objImgUrl;

}
