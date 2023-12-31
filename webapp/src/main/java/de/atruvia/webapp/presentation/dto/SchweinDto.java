package de.atruvia.webapp.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement
public class SchweinDto {

    @NotNull
    @Size(min = 36, max =36)
    private String id;

    @NotNull
    @Size(min = 2, max =30)
    private String name;

    @NotNull
    @DecimalMin("10")
    private int gewicht;
}
