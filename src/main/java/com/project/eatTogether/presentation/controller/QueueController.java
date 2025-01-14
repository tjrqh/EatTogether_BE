package com.project.eatTogether.presentation.controller;

import com.project.eatTogether.application.dto.QueueDTO;
import com.project.eatTogether.application.dto.RsMenusDTO;
import com.project.eatTogether.application.service.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class QueueController {

    @Autowired
    private QueueService queueService;

    // POST : 대기열에 서기 ============================================================
    @PostMapping("/queueIn")
    public ResponseEntity<QueueDTO> addQueue(@RequestBody QueueDTO queueDTO) {
        QueueDTO saveQueue = queueService.save(queueDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveQueue);
    }

    // PUT : 대기 취소 (waiting -> canceled) ============================================================
    @PutMapping("/queue/{queueId}/cancel")
    public ResponseEntity<Integer> updateQueueStateToCancled(@PathVariable Long queueId) {
        int updatedRows = queueService.updateQueueStateToCancled(queueId);
        if (updatedRows > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedRows);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updatedRows);
        }
    }

    // PUT : 입장 (waiting -> entered) ============================================================
    @PutMapping("/queue/{queueId}/enter")
    public ResponseEntity<Integer> updateQueueStateToEntered(@PathVariable Long queueId) {
        int updatedRows = queueService.updateQueueStateToEntered(queueId);
        if (updatedRows > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedRows);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updatedRows);
        }
    }

    // GET : 대기열 보기 (유저, 식당) ============================================================
    @GetMapping("/waiting")
    public ResponseEntity<List<QueueDTO>> getWaitingQueues() {
        List<QueueDTO> queueList = queueService.getWaitingQueues();
        return ResponseEntity.status(HttpStatus.OK).body(queueList);
    }

    // 대기 상태인 큐의 개수 조회하는 엔드포인트
    @GetMapping("/waiting/count")
    public ResponseEntity<Long> countWaitingQueues() {
        Long count = queueService.countWaitingQueues();
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }

//    // POST : 대기열에 메뉴 선택 추가
//    @PostMapping("/queue/{rsId}/menuSelect")
//    public ResponseEntity<QueueOrderDTO> selectMenuForQueue(@PathVariable Long rsId, @RequestBody List<CartItem> cartItems) {
//        QueueOrderDTO queueOrder = queueService.selectMenuForQueue(rsId, cartItems);
//        return ResponseEntity.status(HttpStatus.CREATED).body(queueOrder);
//    }
//
//    // PUT : 대기열의 메뉴 상세 정보 수정
//    @PutMapping("/queue/{rsId}/menuDetails")
//    public ResponseEntity<QueueOrderDTO> updateMenuDetailsForQueue(@PathVariable Long rsId, @RequestBody List<CartItem> updatedCartItems) {
//        // List<CartItem> 형식으로 수정하여 queueService로 전달
//        QueueOrderDTO updatedOrder = queueService.updateMenuDetailsForQueue(rsId, updatedCartItems);
//        return ResponseEntity.status(HttpStatus.OK).body(updatedOrder);
//    }
//
//    // GET : 대기열의 메뉴 선택 정보 조회
//    @GetMapping("/queue/{rsId}/menuDetails")
//    public ResponseEntity<QueueOrder> getMenuDetailsForQueue(@PathVariable Long rsId) {
//        QueueOrder queueOrder = queueService.getMenuDetailsForQueue(rsId);
//        return ResponseEntity.status(HttpStatus.OK).body(queueOrder);
//    }

    // 대기열의 메뉴 목록 조회 (특정 식당 메뉴)
    @GetMapping("/queue/{rsId}/menuList")
    public ResponseEntity<List<RsMenusDTO>> getMenuListForQueue(@PathVariable Long rsId,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        // 특정 식당(rsId)에서 대기열에 선택할 수 있는 메뉴 리스트를 가져옵니다.
        List<RsMenusDTO> menuList = queueService.getMenuListForQueue(rsId, page, size);

        if (!menuList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(menuList);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(menuList);
        }
    }
}
