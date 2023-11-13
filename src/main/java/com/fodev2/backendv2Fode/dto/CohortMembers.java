package com.fodev2.backendv2Fode.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class CohortMembers {

    private Integer cohortid;

    private List<Integer> userids;

}
