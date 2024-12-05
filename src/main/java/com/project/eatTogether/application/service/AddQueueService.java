package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.QueueDTO;
import com.project.eatTogether.domain.repository.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;


@Service
public class AddQueueService {

    @Autowired
    private QueueRepository queueRepository;
    //@Autowired
    //private PaymentRepository paymentRepository;
    @Transactional
    public <QueueDto> QueueDto createQueue(QueueDTO queueDto) {
        // Queue 생성 및 저장
        Queue queue = new Queue() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public Object[] toArray(Object[] a) {
                return new Object[0];
            }

            @Override
            public boolean add(Object o) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean addAll(Collection c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public boolean retainAll(Collection c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection c) {
                return false;
            }

            @Override
            public boolean containsAll(Collection c) {
                return false;
            }

            @Override
            public boolean offer(Object o) {
                return false;
            }

            @Override
            public Object remove() {
                return null;
            }

            @Override
            public Object poll() {
                return null;
            }

            @Override
            public Object element() {
                return null;
            }

            @Override
            public Object peek() {
                return null;
            }
        };
        // queue 설정
        QueueRepository.save(queue);
        // Payment 생성 및 저장 (결제가 필요한 경우)
        //if (queueDto.isPrepaid()) { Payment payment = new Payment();
            // payment 설정
            //paymentRepository.save(payment); }
        // DTO로 변환하여 반환
        return (QueueDto) queueDto; }

}
