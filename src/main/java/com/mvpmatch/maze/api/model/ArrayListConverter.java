package com.mvpmatch.maze.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.json.JSONArray;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

// @Converter(autoApply = true)
// public class JSONArrayConverter implements AttributeConverter<JSONArray, String> {

//     private static final Logger logger = (Logger) LoggerFactory.getLogger(JSONArrayConverter.class);

//     @Override
//     public String convertToDatabaseColumn(JSONArray array)
//     {
//         String data = null;
//         try
//         {
//             data = array.toString();
//         }
//         catch (final Exception e)
//         {
//             logger.error("JSON writing error", e);
//         }

//         return data;
//     }

//     @Override
//     public JSONArray convertToEntityAttribute(String data)
//     {
//         JSONArray array = null;

//         try
//         {
//             array = new JSONArray(data);
//         }
//         catch (final Exception e)
//         {
//             logger.error("JSON reading error", e);
//         }

//         return array;
//     }
// }

@Converter(autoApply = true)
public class ArrayListConverter implements AttributeConverter<JSONArray, String> {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(JSONArrayConverter.class);

    @Override
    public String convertToDatabaseColumn(JSONArray array)
    {
        String data = null;
        JSONArray jsonArray = new JSONArray();
        String res = null;
        try
        {
            // data = array.toString();
            for(int i=0;i<array.length();i++) {
                jsonArray.put(array.get(i));
            }
            res = jsonArray.toString();
            System.out.println("JSON array final length: "+jsonArray.toString());
        }
        catch (final Exception e)
        {
            logger.error("JSON writing error", e);
        }


        return res;
    }

    @Override
    public JSONArray convertToEntityAttribute(String data)
    {
        JSONArray  array = new JSONArray();

        try
        {
            JSONArray jsonArray = new JSONArray(data);
            for(int i=0;i<jsonArray.length();i++) {
                array.put(jsonArray.getString(i));
            }
            // array = new List<String>();
            // array = new List<String>();
        }
        catch (final Exception e)
        {
            logger.error("JSON reading error", e);
        }
        System.out.println("array output: "+array);
        return array;
    }
}