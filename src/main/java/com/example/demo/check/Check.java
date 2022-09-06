package com.example.demo.check;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Check {
    int checkId;
    String guestName;
    double total;

    @Override
    public String toString() {
        return "Check{" +
                "checkId=" + checkId +
                ", guestName='" + guestName + '\'' +
                ", total=" + total +
                '}' + '\n';
    }
}
