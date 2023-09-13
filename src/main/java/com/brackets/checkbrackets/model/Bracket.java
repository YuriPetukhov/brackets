package com.brackets.checkbrackets.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Bracket {
    private String opening;
    private String closing;
}