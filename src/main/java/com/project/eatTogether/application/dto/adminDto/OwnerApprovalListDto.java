package com.project.eatTogether.application.dto.adminDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OwnerApprovalListDto {
    private List<OwnerApprovalDto> owners;
}
