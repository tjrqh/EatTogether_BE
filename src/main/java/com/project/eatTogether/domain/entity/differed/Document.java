package com.project.eatTogether.domain.entity.differed;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String businessRegistrationNumber;  // 사업자등록번호

    @Column(nullable = false)
    private String documentPath;                // 파일 경로

    @Column(nullable = false)
    private String documentName;                // 파일명

    @OneToOne(mappedBy = "document")
    private Restaurant restaurant;              // 식당과의 1:1 관계

    // 파일 경로 및 이름 업데이트
    public void updateDocument(String documentPath, String documentName) {
        this.documentPath = documentPath;
        this.documentName = documentName;
    }

    // 사업자등록번호 업데이트
    public void updateBusinessNumber(String businessRegistrationNumber) {
        this.businessRegistrationNumber = businessRegistrationNumber;
    }

    // 전체 파일 경로 조회
    public String getFullPath() {
        return documentPath + "/" + documentName;
    }
}