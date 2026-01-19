package com.dogukantez.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoGallerist extends DtoBase {
    public String firstName;
    public String lastName;
    public DtoAddress address;
}
