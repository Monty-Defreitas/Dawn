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
    @Pattern(regexp = "^[^\s]+[-a-zA-Z\s]+([-a-zA-Z]+)*$")
    String empireName;
    @NotNull
    @Length(max = 20)
    @Pattern(regexp = "^[^\s]+[-a-zA-Z\s]+([-a-zA-Z]+)*$")
    String sector;
    @NotNull
    @Length(max = 20)
    @Pattern(regexp = "^[^\s]+[-a-zA-Z\s]+([-a-zA-Z]+)*$")
    String alliance;
}
