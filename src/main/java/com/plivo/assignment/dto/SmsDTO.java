package com.plivo.assignment.dto;

import javax.validation.constraints.*;

import lombok.*;

@Data
public class SmsDTO {

    @NotNull(message = "from is missing")
    @NotEmpty(message = "from is missing")
    @Size(min = 6, max = 16,message = "from is invalid")
    @Pattern(regexp="[0-9]+",message = "from is invalid")
    private String from;
    @NotNull(message = "to is missing")
    @NotEmpty(message = "to is missing")
    @Size(min = 6, max = 16,message = "to is invalid")
    @Pattern(regexp="[0-9]+",message = "to is invalid")
    private String to;
    @NotNull(message = "text is missing")
    @NotEmpty(message = "text is missing")
    @Size(min = 1, max = 120,message = "text in invalid")
    private String text;
}
