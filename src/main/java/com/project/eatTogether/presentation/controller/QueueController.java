package com.project.eatTogether.presentation.controller;

import com.project.eatTogether.application.dto.QueueDTO;
import com.project.eatTogether.application.service.QueueService;
import com.project.eatTogether.domain.entity.Queue;
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
    public ResponseEntity<Queue> addQueue(@RequestBody QueueDTO queueDTO) {
        Queue saveQueue = queueService.save(queueDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveQueue);
    }

    // PUT : 대기 취소 (waiting -> cancled) ============================================================
    @PutMapping("/update-state/{queueId}/cancel")
    public ResponseEntity<Integer> updateQueueStateToCancled(@PathVariable Long queueId) {
        int updatedRows = queueService.updateQueueStateToCancled(queueId);
        if (updatedRows > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedRows); }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updatedRows); } }

    // PUT  : 입장 (waiting -> entered) ============================================================
    @PutMapping("/update-state/{queueId}")
    public ResponseEntity<Integer> updateQueueStateToEntered(@PathVariable Long queueId) {
        int updatedRows = queueService.updateQueueStateToEntered(queueId);
        if (updatedRows > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedRows); }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updatedRows);
        }
    }


    // GET  : 대기열보기(유저, 식당) ============================================================
    // queue_state가 'waiting'인 행만 출력
    @GetMapping("/waiting")
    public List<Queue> getWaitingQueues() {
        return queueService.getWaitingQueues();
    }
    // 대기 상태인 큐의 개수 조회하는 엔드포인트
    @GetMapping("/waiting/count")
    public Long countWaitingQueues() {
        return queueService.countWaitingQueues();
    }



}
