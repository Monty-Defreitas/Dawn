package com.leonde.seconddawn.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Validated
@Builder
public class DockOrderRequest {

    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "[^[A-Za-z]\\w\\s]*")
    private String empireName;

    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "[^[A-Za-z]\\w\\s]*")
    private String hullName;

    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "[^[A-Za-z]\\w\\s]*")
    private String shieldType;

    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "[^[A-Za-z]\\w\\s]*")
    private String weaponType;

    private List<@NotNull @Length(max = 30) @Pattern(regexp = "[A-Z0-9_]*") String> missiles;
}
