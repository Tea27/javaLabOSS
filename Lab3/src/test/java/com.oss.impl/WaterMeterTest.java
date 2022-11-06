package com.oss.impl;

import com.oss.MeterType.MeterType;
import com.oss.WaterMeter.WaterMeter;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class WaterMeterTest{

    @Test
    public void deserialize() throws IOException {

        WaterMeter example = WaterMeter.deserialize("watermeter.json");
        MeterType firstMeter = example.getMeterTypes().get(0);

        assertEquals(example.getMeterTypes().size(), 4);

        assertEquals(firstMeter.getTopic(), "Trenutna temperatura vode");
        assertEquals(firstMeter.getDataType(), "int16");
        assertEquals(firstMeter.getFactor(), 10);
        assertEquals(firstMeter.getLowerBoundary(), -3266.8, 0.001);
        assertEquals(firstMeter.getUpperBoundary(), 3266.8, 0.001);
        assertEquals(firstMeter.getMeasuringUnit(), "C");
        assertEquals(firstMeter.getCurrentValue(), 0);
    }


}
