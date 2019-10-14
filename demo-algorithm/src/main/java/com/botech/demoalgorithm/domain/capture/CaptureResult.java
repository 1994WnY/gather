package com.botech.demoalgorithm.domain.capture;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class CaptureResult implements Serializable {

    private static final long serialVersionUID = -8691193147998147375L;

    private Long time;
    private Long msTime;
    private Integer frameIdx;
    private String taskIdx;
    private Integer imgMode;
    private Integer imgWidth;
    private Integer imgHeight;
    private String imgUrl;
    private List<FaceInfo> faceInfos;
    
}