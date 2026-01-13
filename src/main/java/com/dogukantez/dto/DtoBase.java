package com.dogukantez.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DtoBase {
    private long id;
    private Date createTime;
}
