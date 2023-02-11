package com.leonde.seconddawn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Validated
@Builder
@Slf4j
public class DockOrderRequest {



    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "^[^\s]+[-a-zA-Z\s]+([-a-zA-Z]+)*$")
    private String empireName;

    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "^[^\s]+[-a-zA-Z\s]+([-a-zA-Z]+)*$")
    private String hullName;

    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "^[^\s]+[-a-zA-Z\s]+([-a-zA-Z]+)*$")
    private String shieldType;

    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "^[^\s]+[-a-zA-Z\s]+([-a-zA-Z]+)*$")
    private String weaponType;


    private List<@NotNull @Length(max = 200) String> missiles;

    public void logging(){
        log.info("inside Entity = {}", getMissiles());
    }

}
