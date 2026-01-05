package com.example.demo;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.util.Map;

public class QRCodeGenerator {

    public BufferedImage getQR(int size, String data, String correction) {
        QRCodeWriter writer = new QRCodeWriter();
        BufferedImage bufferedImage = null;

        ErrorCorrectionLevel correctionLevel = switch (correction.toUpperCase()) {
            case "L" -> ErrorCorrectionLevel.L;
            case "M" -> ErrorCorrectionLevel.M;
            case "Q" -> ErrorCorrectionLevel.Q;
            case "H" -> ErrorCorrectionLevel.H;
            default -> throw new IllegalArgumentException("Permitted error correction levels are L, M, Q, H");
        };
        Map<EncodeHintType, ?> hints = Map.of(EncodeHintType.ERROR_CORRECTION, correctionLevel);

        try {
            BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, size, size, hints);
            bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }
}
