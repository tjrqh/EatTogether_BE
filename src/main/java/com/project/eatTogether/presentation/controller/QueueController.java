package com.project.eatTogether.presentation.controller;

import com.project.eatTogether.application.dto.QueueDTO;
import com.project.eatTogether.application.service.AddQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/queues")
public class QueueController {

    @Autowired
    private AddQueueService queueService;

    @PostMapping
    public <QueueDto> ResponseEntity<QueueDto> createQueue(@RequestBody QueueDto queueDto) {
        QueueDto createdQueue = queueService.createQueue((QueueDTO) queueDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQueue);
    }

    // 기타 필요한 엔드포인트
}
