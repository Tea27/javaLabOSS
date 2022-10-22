package com.oss.impl;

import com.oss.MyColor.MyColor;
import org.junit.Assert;
import org.junit.Test;

public class ColorTest {
    @Test
    public void decodeTest(){
        String hexColor = "0x1FF0FF";
        MyColor actualColor = MyColor.decode(hexColor);
        final int red = 31, blue = 255, green = 240;

        Assert.assertEquals(actualColor.getRed(), red);
        Assert.assertEquals(actualColor.getBlue(), blue);
        Assert.assertEquals(actualColor.getGreen(), green);
    }

    @Test
    public void rgbToCMYKTest(){
        String hexColor = "0x1FF0FF";
        MyColor c = MyColor.decode(hexColor);

        int[] cymkActual = new int[]{ 87, 5, 0, 0 };
        int[] cymkExpected = MyColor.rgbToCmyk(
                c.getRed(),
                c.getGreen(),
                c.getBlue());

        Assert.assertArrayEquals(cymkActual, cymkExpected);
    }

    @Test
    public void rgbToHslTest() {
        String hexColor = "0x1FF0FF";
        MyColor c = MyColor.decode(hexColor);

        float[] actualHsl = MyColor.RGBtoHSL(c);
        float[] expectedHsl = new float[] {184.01785f, 100.0f, 56.078434f};
        Assert.assertArrayEquals(actualHsl, expectedHsl, 0.001f);
    }

    @Test
    public void RGBtoHSBTest(){
        String hexColor = "0x1FF0FF";
        MyColor c = MyColor.decode(hexColor);
        float[] expectedHsb = new float[3];
        float[] actualHsb = MyColor.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), expectedHsb);

        Assert.assertArrayEquals(actualHsb, expectedHsb, 0.001f);
    }

}
