package com.leonde.seconddawn.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@Validated
public class Empires {
    @JsonIgnore
    int empireId;
    @NotNull
    @Length(max = 20)
    @Pattern(regexp = "[^[A-Za-z]\\w\\s]*")
    String empireName;
    @NotNull
    @Length(max = 20)
    @Pattern(regexp = "[^[A-Za-z]\\w\\s]*")
    String sector;
    @NotNull
    @Length(max = 20)
    @Pattern(regexp = "[^[A-Za-z]\\w\\s]*")
    String alliance;
}
