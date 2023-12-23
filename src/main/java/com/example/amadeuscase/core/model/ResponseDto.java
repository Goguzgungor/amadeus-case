package com.example.amadeuscase.core.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseDto extends ResponseEntity{
private String message;

private String status;

    public ResponseDto(String airPortCreated, String number) {
        super(airPortCreated, HttpStatus.OK);
        this.message = airPortCreated;
        this.status = number;
    }

    public static ResponseEntity<ResponseDto> ok(String message, String number) {
        return ResponseEntity.ok(new ResponseDto(message,number));
    }
}
