package com.oss.impl;

import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import com.oss.MeterType.MeterType;
import org.junit.Before;
import org.junit.Test;

public class MeterTypeTest {
    MeterType meterType;

    @Before
    public void setup(){
        meterType = MeterType.deserialize("meter.json");
    }

    @Test
    public void deserialize(){
        assertEquals(meterType.getTopic(), "Trenutna temperatura vode");
        assertEquals(meterType.getDataType(), "int16");
        assertEquals(meterType.getFactor(), 10);
        assertEquals(meterType.getLowerBoundary(), -3266.8, 0.001);
        assertEquals(meterType.getUpperBoundary(), 3266.8, 0.001);
        assertEquals(meterType.getMeasuringUnit(), "C");
        assertEquals(meterType.getCurrentValue(), 0);
    }

    @Test
    public void setRandomCurrentVal(){
        meterType = new MeterType("Trenutna temperatura vode", "int16", 10, -3266.8f, 3266.8f, "C", 0);
        Assert.assertNotEquals(meterType.getCurrentValue(), meterType.setRandomCurrentVal().getCurrentValue());
    }
}

