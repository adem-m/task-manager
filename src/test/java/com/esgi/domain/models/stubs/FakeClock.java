package com.esgi.domain.models.stubs;

import com.esgi.domain.Clock;

import java.time.LocalDate;

public class FakeClock implements Clock {
    @Override
    public LocalDate now() {
        return LocalDate.of(2020, 1, 1);
    }
}
