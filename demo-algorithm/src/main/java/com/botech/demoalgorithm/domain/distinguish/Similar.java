package com.botech.demoalgorithm.domain.distinguish;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class Similar implements Serializable {

    private static final long serialVersionUID = -683050327457692377L;

    private String dbId;
    private List<Users> users;
   
}