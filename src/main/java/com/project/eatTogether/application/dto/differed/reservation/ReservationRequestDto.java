package com.project.eatTogether.application.dto.differed.reservation;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@Data
@NoArgsConstructor
public class ReservationRequestDto {
    private Long memberId;
    private Long rsId;
    private String guestName;
    private String guestPhone;
    private int rsReservationPartySize;
    private LocalDate rsReservationDate;
    private String rsReservationTime;
    private String rsReservationRequest;

    public LocalDateTime getReservationDateTime(LocalDate date) {
        return LocalDateTime.of(date,
                LocalTime.parse(rsReservationTime, DateTimeFormatter.ofPattern("HH:mm")));
    }
}

