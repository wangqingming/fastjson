package com.alibaba.json.bvt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import junit.framework.TestCase;

import java.math.BigDecimal;

public class JSONObjectTest7 extends TestCase {

    String testJson = "{\n" +
            "   \"msisdn\" : 0769821,\n" +
            "   \"tracks\" : [\n" +
            "      {\n" +
            "         \"cellsite\" : \"19D8901\",\n" +
            "         \"Time\" : 1551370228,\n" +
            "         \"Timeslot\" : 1551370200,\n" +
            "         \"Interval\" : 26343\n" +
            "      },\n" +
            "      {\n" +
            "         \"cellsite\" : \"1972D52\",\n" +
            "         \"Time\" : 1551396571,\n" +
            "         \"Timeslot\" : 1551396300,\n" +
            "         \"Interval\" : 753\n" +
            "      }]}";

    public void test() throws Exception {
        JSONObject jsonObject = JSON.parseObject("{\"test\":null,\"a\":\"cc\"}");
        assertEquals(2, jsonObject.entrySet().size());
        assertTrue(jsonObject.containsKey("test"));
    }

    public void testStartWithZero_LongValue() throws Exception {
        JSONObject jsonObject = JSON.parseObject(testJson, Feature.NumericValueToString);
        long actuaLongValue = jsonObject.getLongValue("msisdn");
        assertEquals(769821, actuaLongValue);//Expected :000123 Actual   :123
    }

    public void testStartWithZero_LongString() throws Exception {
        JSONObject jsonObject = JSON.parseObject(testJson, Feature.NumericValueToString);
        String actualStringValue = jsonObject.getString("msisdn");
        System.out.println(jsonObject.get("tracks"));
        assertEquals("0769821", actualStringValue);//Expected :000123 Actual   :123
    }

    public void testStartWithZero_FloatValue() throws Exception {
        JSONObject jsonObject = JSON.parseObject("{id:0.000123}", Feature.NumericValueToString);
        float actualStringValue = jsonObject.getFloatValue("id");
        BigDecimal expectedValue = new BigDecimal(Double.toString(0.000123f));
        BigDecimal actualValue = new BigDecimal(Double.toString(actualStringValue));


        assertEquals(expectedValue, actualValue);//Expected :000123 Actual   :123
    }


    public void testStartWithZero_FloatString() throws Exception {
        JSONObject jsonObject = JSON.parseObject("{id:0.000123}", Feature.NumericValueToString);
        String actualStringValue = jsonObject.getString("id");
        assertEquals("0.000123", actualStringValue);//Expected :000123 Actual   :123
    }

}
