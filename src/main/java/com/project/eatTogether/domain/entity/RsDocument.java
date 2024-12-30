package com.project.eatTogether.domain.entity;

import com.project.eatTogether.domain.entity.baseentity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class RsDocument extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rsDocumentId;

    @Column
    private String rsDocumentCertificate;

    @Column
    private String rsDocumentBusinessId; //사업자 등록번호

    @Column(nullable = false)
    private String documentPath;                // 파일 경로

    @Column(nullable = false)
    private String documentName;                // 파일명

    // RsRestaurant와의 관계
    @OneToOne(mappedBy = "rsDocument")  // RsRestaurant 클래스의 rsDocument 필드를 참조
    private RsRestaurant rsRestaurant;

    // 파일 경로 및 이름 업데이트
    public void updateDocument(String documentPath, String documentName) {
        this.documentPath = documentPath;
        this.documentName = documentName;
    }

    // 사업자등록번호 업데이트
    public void updateBusinessNumber(String rsDocumentBusinessId) {
        this.rsDocumentBusinessId = rsDocumentBusinessId;
    }

    // 전체 파일 경로 조회
    public String getFullPath() {
        return documentPath + "/" + documentName;
    }
}
