package com.botech.demoalgorithm.domain.distinguish;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class RecogResult implements Serializable {

    private static final long serialVersionUID = -3332654233472088455L;

    private Long time;
    private Long msTime;
    private Integer frameIdx;
    private String taskIdx;
    private Integer trackIdx;
    private String feature;
    private Integer imgMode;
    private Integer imgWidth;
    private Integer imgHeight;
    private String imgUrl;
    private Float qualityScore;
    private FaceAngle faceAngle;
    private FaceRect  faceRect;
    private List<Similar> similars;

}