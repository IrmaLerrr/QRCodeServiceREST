package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class QRCodeController {

    @GetMapping("api/health")
    public ResponseEntity<Void> healthCheck() {
        return ResponseEntity.ok().build();
    }
    @GetMapping("api/qrcode")
    public ResponseEntity<Void> getQRCode() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}

