package com.leonde.seconddawn.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Empires {
    @JsonIgnore
    int empireId;
    String empireName;
    String sector;
    String alliance;
}
