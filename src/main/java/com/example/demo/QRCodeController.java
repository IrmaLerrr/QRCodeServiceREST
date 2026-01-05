package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.util.List;

@RestController
public class QRCodeController {
    private static final QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();

    @GetMapping("api/health")
    public ResponseEntity<Void> healthCheck() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/qrcode")
    public ResponseEntity<BufferedImage> getImage(
            @RequestParam(defaultValue = "250") int size,
            @RequestParam(defaultValue = "png") String type,
            @RequestParam(defaultValue = "L") String correction,
            @RequestParam(required = false) String contents) {

        if (contents == null || contents.isBlank()) {
            throw new IllegalArgumentException("Contents cannot be null or blank");
        }

        if (size < 150 || size > 350) {
            throw new IllegalArgumentException("Image size must be between 150 and 350 pixels");
        }

        correction = correction.toUpperCase();
        if (!List.of("L", "M", "Q", "H").contains(correction)) {
            throw new IllegalArgumentException("Contents cannot be null or blank");
        }

        MediaType mediaType = switch (type.toLowerCase()) {
            case "png" -> MediaType.IMAGE_PNG;
            case "jpeg" -> MediaType.IMAGE_JPEG;
            case "gif" -> MediaType.IMAGE_GIF;
            default -> throw new IllegalArgumentException("Only png, jpeg and gif image types are supported");
        };

        BufferedImage bufferedImage = qrCodeGenerator.getQR(size, contents, correction);
        return ResponseEntity
                .ok()
                .contentType(mediaType)
                .body(bufferedImage);
    }

}

