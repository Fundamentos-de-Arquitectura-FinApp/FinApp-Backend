package com.example.finappapirest.finances.domain.model.valueobjects;

public enum PeriodType {
    DAILY (1),
    WEEKLY (7),
    MONTHLY (30),
    BIMONTHLY (60),
    TRIMONTHLY (90),
    QUARTERLY (120),
    SEMIANNUALLY (180),
    ANNUALLY (360);

    private final int value;

    // Constructor
    PeriodType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
