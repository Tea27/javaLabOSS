package com.oss.MyColor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyColor{
    private int red = 0, green = 0, blue = 0, value = 0;
    private float[] frgbvalue = null;
    public MyColor(int r, int g, int b) {
        red = r;
        green = g;
        blue = b;
        var a = 255;

        value = ((a & 0xFF) << 24) |
                ((r & 0xFF) << 16) |
                ((g & 0xFF) << 8)  |
                ((b & 0xFF) << 0);

        testColorValueRange(r,g,b,a);
    }

    private static void testColorValueRange(int r, int g, int b, int a) {
        boolean rangeError = false;
        String badComponentString = "";

        if ( a < 0 || a > 255) {
            rangeError = true;
            badComponentString = badComponentString + " Alpha";
        }
        if ( r < 0 || r > 255) {
            rangeError = true;
            badComponentString = badComponentString + " Red";
        }
        if ( g < 0 || g > 255) {
            rangeError = true;
            badComponentString = badComponentString + " Green";
        }
        if ( b < 0 || b > 255) {
            rangeError = true;
            badComponentString = badComponentString + " Blue";
        }
        if ( rangeError == true ) {
            throw new IllegalArgumentException("Color parameter outside of expected range:"
                    + badComponentString);
        }
    }
    public static MyColor decode(String nm) throws NumberFormatException {
        Integer intval = Integer.decode(nm);
        int i = intval.intValue();
        return new MyColor((i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF);
    }

    public static float[] RGBtoHSB(int r, int g, int b, float[] hsbvals) {
        float hue, saturation, brightness;
        if (hsbvals == null) {
            hsbvals = new float[3];
        }
        int cmax = (r > g) ? r : g;
        if (b > cmax) cmax = b;
        int cmin = (r < g) ? r : g;
        if (b < cmin) cmin = b;

        brightness = ((float) cmax) / 255.0f;
        if (cmax != 0)
            saturation = ((float) (cmax - cmin)) / ((float) cmax);
        else
            saturation = 0;
        if (saturation == 0)
            hue = 0;
        else {
            float redc = ((float) (cmax - r)) / ((float) (cmax - cmin));
            float greenc = ((float) (cmax - g)) / ((float) (cmax - cmin));
            float bluec = ((float) (cmax - b)) / ((float) (cmax - cmin));
            if (r == cmax)
                hue = bluec - greenc;
            else if (g == cmax)
                hue = 2.0f + redc - bluec;
            else
                hue = 4.0f + greenc - redc;
            hue = hue / 6.0f;
            if (hue < 0)
                hue = hue + 1.0f;
        }
        hsbvals[0] = hue;
        hsbvals[1] = saturation;
        hsbvals[2] = brightness;

        return hsbvals;
    }

    public int getRGB() {
        return value;
    }
    public float[] getRGBColorComponents(float[] compArray) {
        float[] f;
        if (compArray == null) {
            f = new float[3];
        } else {
            f = compArray;
        }
        if (frgbvalue == null) {
            f[0] = ((float)getRed())/255f;
            f[1] = ((float)getGreen())/255f;
            f[2] = ((float)getBlue())/255f;
        } else {
            f[0] = frgbvalue[0];
            f[1] = frgbvalue[1];
            f[2] = frgbvalue[2];
        }
        return f;
    }

    public static float[] RGBtoHSL(MyColor color)
    {

        float[] rgb = color.getRGBColorComponents( null );
        float r = rgb[0];
        float g = rgb[1];
        float b = rgb[2];


        float min = Math.min(r, Math.min(g, b));
        float max = Math.max(r, Math.max(g, b));


        float hue = 0;

        if (max == min)
            hue = 0;
        else if (max == r)
            hue = ((60 * (g - b) / (max - min)) + 360) % 360;
        else if (max == g)
            hue = (60 * (b - r) / (max - min)) + 120;
        else if (max == b)
            hue = (60 * (r - g) / (max - min)) + 240;

        float luminance = (max + min) / 2;

        float saturation = 0;

        if (max == min)
            saturation = 0;
        else if (luminance <= .5f)
            saturation = (max - min) / (max + min);
        else
            saturation = (max - min) / (2 - max - min);

        return new float[] {hue, saturation * 100, luminance * 100};
    }

    public static int[] rgbToCmyk(int r, int g, int b) {
        double percentageR = r / 255.0 * 100;
        double percentageG = g / 255.0 * 100;
        double percentageB = b / 255.0 * 100;

        double k = 100 - Math.max(Math.max(percentageR, percentageG), percentageB);

        if (k == 100) {
            return new int[]{ 0, 0, 0, 100 };
        }

        int c = (int)((100 - percentageR - k) / (100 - k) * 100);
        int m = (int)((100 - percentageG - k) / (100 - k) * 100);
        int y = (int)((100 - percentageB - k) / (100 - k) * 100);

        return new int[]{ c, m, y, (int)k };
    }
    public void printHSL(float[] hsl)
    {
        String HSL =
                "HSL Color[h=" + hsl[0] +
                        ",s=" + hsl[1] +
                        ",l=" + hsl[2] + "]";

        System.out.println(HSL);
    }
    public void printCYMK(int[] cymk)
    {
        String CYMK =
                "CYMK Color: cyan = " + cymk[0] + "%" +
                        ", magenta = " + cymk[1] + "%" +
                        ", yellow = " + cymk[2] + "%" +
                        ", key = " + cymk[3] + "%";

        System.out.println(CYMK);
    }
}
