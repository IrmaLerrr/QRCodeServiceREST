package com.example.demo;

import java.awt.*;
import java.awt.image.BufferedImage;

public class QRCodeGenerator {
    private final int width = 250;
    private final int height = 250;

    public BufferedImage getQR() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        return image;
    }
}
