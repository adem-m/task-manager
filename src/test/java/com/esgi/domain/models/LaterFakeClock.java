package com.esgi.domain.models;

import com.esgi.domain.Clock;

import java.time.LocalDate;

public class LaterFakeClock implements Clock {
    @Override
    public LocalDate now() {
        return LocalDate.of(2020, 1, 2);
    }
}
