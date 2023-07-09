package com.example.newMock.Controller;

import com.example.newMock.Model.RequestDTo;
import com.example.newMock.Model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Random;

@RestController
@Slf4j
public class MainController {
    ObjectMapper mapper = new ObjectMapper();
    @PostMapping(value = "/info/postBalances",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object postBalances(@RequestBody RequestDTo requestDTo){
        try{
            String clientId = requestDTo.getClientId();
            char firstDigit = clientId.charAt(0);
            double balance;
            BigDecimal maxLimit;
            String currency;
            if(firstDigit == '8'){
                currency = "US";
                maxLimit = new BigDecimal("2000.00");
                balance = (Math.random() * 2000);
            }else if(firstDigit == '9'){
                currency = "EU";
                maxLimit = new BigDecimal("1000.00");
                balance = (Math.random() * 1000);
            }else{
                currency = "RU";
                maxLimit = new BigDecimal("10000.00");
                balance = (Math.random() * 10000);
            }
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCurrency(currency);
            responseDTO.setMaxLimit(maxLimit.toString());
            responseDTO.setRqUID(requestDTo.getRqUID());
            responseDTO.setClientId(requestDTo.getClientId());
            responseDTO.setAccount(responseDTO.getAccount());
            responseDTO.setBalance(String.valueOf(balance));

            log.error("*** Запрос ***" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));
            log.error("*** Ответ ***" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTo));

            return responseDTO;
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
