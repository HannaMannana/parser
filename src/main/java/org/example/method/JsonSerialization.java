package org.example.method;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

public class JsonSerialization {

    static StringBuilder json = new StringBuilder();


    public static String getJson(Object obj) throws IllegalAccessException {
        boolean objIsList = obj instanceof List;
        char dm = (char) 34;


        if (objIsList) {
            json.append("[");

            List list = (List) obj;

            for (int j = 0, listSize = list.size(); j < listSize; j++) {
                Object obj2 = list.get(j);
                getJson(obj2);
                json.append(j + 1 != listSize ? "," : "");
            }
            json.append("]");
        } else {
            json.append("{");

            Field[] fields = obj.getClass().getFields();

            for (int i = 0, fieldsLength = fields.length; i < fieldsLength; i++) {
                Field field = fields[i];
                Object fieldObj = field.get(obj);
                json.append(dm + field.getName() + dm + ":");
                if (fieldObj instanceof List) {
                    getJson(fieldObj);
                } else {
                    boolean withoutKavichki = field.getType().isAssignableFrom(BigDecimal.class) || fieldObj == null || field.getType().isAssignableFrom(Boolean.class);

                    String kavichka = withoutKavichki ? "" : String.valueOf(dm);

                    json.append(kavichka + fieldObj);
                    json.append(kavichka);
                }
                json.append(i + 1 != fieldsLength ? "," : "");
            }
            json.append("}");
        }
        return json.toString();
    }

}
