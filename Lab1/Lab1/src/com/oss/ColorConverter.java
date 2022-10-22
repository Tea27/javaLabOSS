package com.oss;

import com.oss.MyColor.MyColor;

import java.awt.*;

public class ColorConverter {
    public static void main(String[] args) {
        String hexColor = "0x1FF0FF";

        MyColor c = MyColor.decode(hexColor);

        float[] hsbCode = new float[3];

        var hsb = MyColor.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsbCode);
        System.out.println("hue" + hsb[0] * 360);
        System.out.println("Boja u HEX formatu: 0x" +
                Integer.toHexString(c.getRGB() & 0x00FFFFFF));
        System.out.println("Boja u RGB formatu: " + c.getRed() + ", " +
                c.getGreen() + ", " + c.getBlue());
        System.out.println("Boja u HSB formatu: " + hsbCode[0] * 360 + "°, " +
                hsbCode[1] * 100 + "%, " + hsbCode[2] * 100 + "%");

        var hsl = MyColor.RGBtoHSL(c);//RGBtoHSL(c);

        c.printHSL(hsl);

        var cmyk = MyColor.rgbToCmyk(c.getRed(), c.getGreen(), c.getBlue());

        c.printCYMK(cmyk);
    }
}
