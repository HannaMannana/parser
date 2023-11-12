package org.example.method;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JsonDeserialization {

    public static <Cl extends Class, Obj extends Object> List<Obj> parseJsonArrayGen(String jsonArray, Cl clazz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        List<Obj> jsonObjects = new ArrayList<>();

        jsonArray = jsonArray.substring(1, jsonArray.length() - 1);

        String[] objectStrings = jsonArray.split("\\},\\{");

        for (String objectString : objectStrings) {
            objectString = objectString.substring(1, objectString.length() - 1);

            String[] keyValuePairs = objectString.split(",");
            Field[] fields = clazz.getFields();

            Object deserializedObj = clazz.getDeclaredConstructor().newInstance();

            for (int i = 0, keyValuePairsLength = keyValuePairs.length; i < keyValuePairsLength; i++) {
                String pair = keyValuePairs[i];
                String[] parts = pair.split(":");
                String key = parts[0].trim().replaceAll("\"", "");
                String valueStr = parts[1].trim();


                Field fieldToSave = getField(fields, key);

                Object value;
                if (valueStr.contains("null")) {
                    value = null;
                    fieldToSave.set(deserializedObj, value);
                } else if (valueStr.startsWith("[") && valueStr.endsWith("]")) {
                    fieldToSave.set(deserializedObj, parseJsonArrayGen(valueStr, fieldToSave.getType()));

                } else if (valueStr.startsWith("\"") && valueStr.endsWith("\"")) {
                    String strWitoutKavichki = valueStr.substring(1, valueStr.length() - 1);
                    if (fieldToSave.getType().isAssignableFrom(UUID.class)) {
                        fieldToSave.set(deserializedObj, UUID.fromString(strWitoutKavichki));

                    } else {
                        fieldToSave.set(deserializedObj, strWitoutKavichki);
                    }
                } else if (valueStr.equals("true") || valueStr.equals("false")) {
                    fieldToSave.set(deserializedObj, Boolean.parseBoolean(valueStr));
                } else if (valueStr.contains(".")) {
                    fieldToSave.set(deserializedObj, BigDecimal.valueOf(Double.parseDouble(valueStr)));
                } else {
                    fieldToSave.set(deserializedObj, Integer.parseInt(valueStr));
                }
            }


            jsonObjects.add((Obj) deserializedObj);
        }

        return jsonObjects;
    }


    private static Field getField(Field[] fields, String key) {
        Field fieldToSave = null;
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];

            if (field.getName().equals(key)) {
                fieldToSave = field;
            }
        }
        return fieldToSave;
    }

}





