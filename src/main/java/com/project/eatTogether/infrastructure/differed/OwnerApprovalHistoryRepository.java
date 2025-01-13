package com.project.eatTogether.infrastructure.differed;


import com.project.eatTogether.domain.entity.differed.OwnerApprovalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerApprovalHistoryRepository extends JpaRepository<OwnerApprovalHistory, Long> {

}
