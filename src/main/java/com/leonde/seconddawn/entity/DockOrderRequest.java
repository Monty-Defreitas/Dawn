package com.leonde.seconddawn.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.List;

@Data
@Builder
public class DockOrderRequest {


    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "[\\w\\s]*")
    private String empireName;

    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "[\\w\\s]*")
    private String hullName;

    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "[\\w\\s]*")
    private String shieldType;

    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "[\\w\\s]*")
    private String weaponType;

    private List<@NotNull @Length(max = 30) @Pattern(regexp = "[A-Z0-9_]*") String> missiles;
}
