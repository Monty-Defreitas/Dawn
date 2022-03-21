package com.leonde.seconddawn.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Builder
@Data
public class UpdateDockOrder {

    @NotNull
    @Length(max = 45)
    private String orderKey;

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

}
