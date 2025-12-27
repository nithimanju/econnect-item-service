package com.e_connect.part_service.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode
public class Media implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long mediaId;
    private String mediaName;
    private String mediaPath;
    private String sequence;
}
