package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.QueueDTO;
import com.project.eatTogether.application.dto.QueueOrderDTO;
import com.project.eatTogether.application.dto.RsMenusDTO;
import com.project.eatTogether.domain.entity.*;
import com.project.eatTogether.domain.entity.differed.Member;
import com.project.eatTogether.infrastructure.RsMenuRepository;
import com.project.eatTogether.infrastructure.differed.MemberRepository;
import com.project.eatTogether.infrastructure.repository.QueueRepository;
import com.project.eatTogether.infrastructure.repository.WriteRsRestaurantRepository;
import com.project.eatTogether.infrastructure.restaurantInfra.QueueOrderItemRepository;
import com.project.eatTogether.infrastructure.restaurantInfra.QueueOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueueService {

    private final QueueRepository queueRepository;
    private final MemberRepository memberRepository;
    private final WriteRsRestaurantRepository writeRsRestaurantRepository;
    private final QueueOrderRepository queueOrderRepository;
    private final QueueOrderItemRepository queueOrderItemRepository;
    private final RsMenuRepository rsMenuRepository;

    // 대기열에 들어가는 메서드
    public QueueDTO save(QueueDTO queueDTO) {
        RsRestaurant rsRestaurant = writeRsRestaurantRepository.findById(queueDTO.getRsId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid restaurant ID"));
        Member member = memberRepository.findById(queueDTO.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Queue queue = queueRepository.save(queueDTO.toEntity(rsRestaurant, member));

        return QueueDTO.builder()
                .queueId(queue.getQueueId())
                .queueNumber(queue.getQueueNumber())
                .queueDate(queue.getQueueDate())
                .queueTime(queue.getQueueTime())
                .queueState(queue.getQueueState())
                .createdAt(queue.getCreatedAt())       // 변경
                .modifiedAt(queue.getModifiedAt())     // 변경
                .deletedAt(queue.getDeletedAt())       // 변경
                .rsId(queue.getRsRestaurant().getRsId())
                .memberId(queue.getMember().getId())
                .rsName(queue.getRsRestaurant().getRsName())
                .build();
    }

    // 대기열 조회 메서드
    public List<QueueDTO> getWaitingQueues() {
        return queueRepository.findByQueueState("waiting").stream()
                .map(queue -> QueueDTO.builder()
                        .queueId(queue.getQueueId())
                        .queueNumber(queue.getQueueNumber())
                        .queueDate(queue.getQueueDate())
                        .queueTime(queue.getQueueTime())
                        .queueState(queue.getQueueState())
                        .createdAt(queue.getCreatedAt())       // 변경
                        .modifiedAt(queue.getModifiedAt())     // 변경
                        .deletedAt(queue.getDeletedAt())       // 변경
                        .rsId(queue.getRsId())
                        .rsName(queue.getRsName())
                        .memberId(queue.getMemberId())
                        .build())
                .collect(Collectors.toList());
    }

    // 대기 중인 큐 개수 확인
    public Long countWaitingQueues() {
        return queueRepository.countByQueueState("waiting");
    }

    // 특정 큐의 상태를 'waiting'에서 'entered'로 변경하는 메서드
    @Transactional
    public int updateQueueStateToEntered(Long queueId) {
        return queueRepository.updateQueueStateToEntered(queueId);
    }

    // 특정 큐의 상태를 'waiting'에서 'canceled'로 변경하는 메서드
    @Transactional
    public int updateQueueStateToCancled(Long queueId) {
        return queueRepository.updateQueueStateToCancled(queueId);
    }

    // 대기열에 메뉴 선택 추가
    @Transactional
    public QueueOrderDTO selectMenuForQueue(Long queueId, List<CartItem> cartItems) {
        Queue queue = queueRepository.findById(queueId)
                .orElseThrow(() -> new IllegalArgumentException("Queue not found"));

        // QueueOrder 객체 생성
        QueueOrder queueOrder = new QueueOrder();
        queueOrder.setQueue(queue);

        // CartItem들을 QueueOrderItem으로 변환
        List<QueueOrderItem> queueOrderItems = cartItems.stream().map(cartItem -> {
            QueueOrderItem queueOrderItem = new QueueOrderItem();
            queueOrderItem.setQueueOrder(queueOrder);
            queueOrderItem.setRsMenus(cartItem.getRsMenus());
            queueOrderItem.setQueueOrderItemAmount(cartItem.getCartItemAmount());
            queueOrderItem.setQueueOrderItemPrice(Integer.parseInt(cartItem.getRsMenus().getRsMenuPrice())); // 가격 예시
            return queueOrderItem;
        }).collect(Collectors.toList());

        // QueueOrderItems를 QueueOrder에 설정
        queueOrder.setQueueOrderItems(queueOrderItems);

        // QueueOrder 저장
        queueOrderRepository.save(queueOrder);

        // QueueOrderItem 저장
        queueOrderItemRepository.saveAll(queueOrderItems);

        // QueueOrderDTO로 변환하여 반환
        return QueueOrderDTO.builder()
                .queueOrderId(queueOrder.getQueueOrderId())
                .totalAmount(queueOrderItems.stream().mapToInt(item -> item.getQueueOrderItemAmount()).sum())
                .orderDateTime(queueOrder.getOrderDateTime())
                .orderStatus(queueOrder.getOrderStatus())
                .queueId(queueOrder.getQueue().getQueueId())
                .rsId(queueOrder.getQueue().getRsRestaurant().getRsId())
                .rsName(queueOrder.getQueue().getRsRestaurant().getRsName())  // rsName 추가
                .memberId(queueOrder.getQueue().getMember().getId())
                .paymentId(queueOrder.getPayment().getPaymentId())
                .build();
    }

    // 대기열에 해당하는 식당의 메뉴 목록 조회
    public List<RsMenusDTO> getMenuListForQueue(Long queueId, int page, int size) {
        List<RsMenus> rsMenusList = rsMenuRepository.findByRsRestaurantRsId(queueId, PageRequest.of(page, size)).getContent();

        return rsMenusList.stream()
                .map(menu -> RsMenusDTO.builder()
                        .menuId(menu.getRsMenuId())
                        .rsId(menu.getRsRestaurant().getRsId())
                        .rsName(menu.getRsRestaurant().getRsName())
                        .menuName(menu.getRsMenuName())
                        .menuDesc(menu.getRsMenuDesc())
                        .menuPrice(menu.getRsMenuPrice())
                        .menuState(menu.getRsMenuState())
                        .menuAppear(menu.getRsMenuAppear())
                        .menuPhotoOrigin(menu.getRsMenuPhotoOrigin())
                        .menuPhotoPath(menu.getRsMenuPhotoPath())
                        .menuPhotoName(menu.getRsMenuPhotoName())
                        .createdAt(menu.getCreatedAt())       // 변경
                        .modifiedAt(menu.getModifiedAt())     // 변경
                        .deletedAt(menu.getDeletedAt())       // 변경
                        .build())
                .collect(Collectors.toList());
    }

    // 대기열에 메뉴 세부 정보 업데이트
    @Transactional
    public QueueOrderDTO updateMenuDetailsForQueue(Long queueId, List<CartItem> updatedCartItems) {
        Queue queue = queueRepository.findById(queueId)
                .orElseThrow(() -> new IllegalArgumentException("Queue not found"));

        // 기존 QueueOrder 조회
        QueueOrder queueOrder = queueOrderRepository.findByQueueQueueId(queueId)
                .orElseThrow(() -> new IllegalArgumentException("Queue order not found"));

        // 기존 주문 항목들을 업데이트
        List<QueueOrderItem> updatedQueueOrderItems = updatedCartItems.stream().map(cartItem -> {
            QueueOrderItem queueOrderItem = new QueueOrderItem();
            queueOrderItem.setQueueOrder(queueOrder);
            queueOrderItem.setRsMenus(cartItem.getRsMenus());
            queueOrderItem.setQueueOrderItemAmount(cartItem.getCartItemAmount());
            queueOrderItem.setQueueOrderItemPrice(Integer.parseInt(cartItem.getRsMenus().getRsMenuPrice())); // 가격 예시
            return queueOrderItem;
        }).collect(Collectors.toList());

        // 기존 QueueOrderItems를 업데이트
        queueOrder.setQueueOrderItems(updatedQueueOrderItems);

        // QueueOrder 업데이트 저장
        queueOrderRepository.save(queueOrder);

        // QueueOrderItem 저장
        queueOrderItemRepository.saveAll(updatedQueueOrderItems);

        // QueueOrderDTO 반환
        return QueueOrderDTO.builder()
                .queueOrderId(queueOrder.getQueueOrderId())
                .totalAmount(updatedQueueOrderItems.stream().mapToInt(item -> item.getQueueOrderItemAmount()).sum())
                .orderDateTime(queueOrder.getOrderDateTime())
                .orderStatus(queueOrder.getOrderStatus())
                .queueId(queueOrder.getQueue().getQueueId())
                .rsId(queueOrder.getQueue().getRsRestaurant().getRsId())
                .rsName(queueOrder.getQueue().getRsRestaurant().getRsName())  // rsName 추가
                .memberId(queueOrder.getQueue().getMember().getId())
                .paymentId(queueOrder.getPayment().getPaymentId())
                .build();
    }

    // 대기열에 메뉴 세부 정보 조회
    public QueueOrder getMenuDetailsForQueue(Long queueId) {
        return queueOrderRepository.findByQueueQueueId(queueId)
                .orElseThrow(() -> new IllegalArgumentException("QueueOrder not found for the given queueId"));
    }
}