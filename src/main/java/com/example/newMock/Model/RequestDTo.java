package com.example.newMock.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTo {
    private String rqUID;
    private String clientId;
    private String account;
    private String openDate;
    private String closeDate;
}
