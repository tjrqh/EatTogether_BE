package com.project.eatTogether.application.service.differed;

import com.project.eatTogether.application.dto.differed.admin.OwnerApprovalDto;
import com.project.eatTogether.application.dto.differed.admin.OwnerApprovalListDto;
import com.project.eatTogether.domain.enums.MemberRole;
import com.project.eatTogether.domain.enums.OwnerStatus;
import com.project.eatTogether.infrastructure.differed.MemberRepository;
import com.project.eatTogether.infrastructure.differed.OwnerApprovalHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminApprovalService {

    private final OwnerApprovalHistoryRepository ownerApprovalHistoryRepository;
    private final MemberRepository memberRepository;

    public OwnerApprovalListDto findPendingOwners() {
        List<OwnerApprovalDto> ownerList = memberRepository
                .findByRoleAndOwnerStatusWithRestaurants(
                        MemberRole.OWNER,
                        OwnerStatus.PENDING
                )
                .stream()
                .map(OwnerApprovalDto::convertToDto)
                .collect(Collectors.toList());
        return new OwnerApprovalListDto(ownerList);
    }


}
