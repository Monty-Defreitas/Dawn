package com.leonde.seconddawn.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Builder
@Data
public class UpdateDockOrder {

    @NotNull
    @Length(max = 45)
    private String orderKey;

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

//    private List<@NotNull @Length(max = 30) @Pattern(regexp = "[A-Z0-9_]*") Missiles> missiles;
}
