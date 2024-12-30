package com.project.eatTogether.application.service.adminService;

import com.project.eatTogether.application.dto.adminDto.OwnerApprovalDto;
import com.project.eatTogether.application.dto.adminDto.OwnerApprovalListDto;
import com.project.eatTogether.domain.enums.OwnerStatus;
import com.project.eatTogether.domain.enums.UserRole;
import com.project.eatTogether.infrastructure.OwnerApprovalHistoryRepository;
import com.project.eatTogether.infrastructure.UserRepository;
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
    private final UserRepository userRepository;

    public OwnerApprovalListDto findPendingOwners() {
        List<OwnerApprovalDto> ownerList = userRepository
                .findByRoleAndOwnerStatusWithRestaurants(
                        UserRole.OWNER,
                        OwnerStatus.PENDING
                )
                .stream()
                .map(OwnerApprovalDto::convertToDto)
                .collect(Collectors.toList());
        return new OwnerApprovalListDto(ownerList);
    }


}
