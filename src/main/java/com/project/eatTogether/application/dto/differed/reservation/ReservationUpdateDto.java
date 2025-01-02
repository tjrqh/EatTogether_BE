package com.project.eatTogether.application.dto.differed.reservation;

import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Log4j2
@Builder
@Data
public class ReservationUpdateDto {

    private String guestName;
    private int rsReservationPartySize;
    private String rsReservationRequest;
    private LocalDate rsReservationDate;
    private String rsReservationTime;

    public LocalDateTime getReservationTime(LocalDate date) {
        return LocalDateTime.of(date,
                LocalTime.parse(rsReservationTime, DateTimeFormatter.ofPattern("HH:mm")));
    }




}
