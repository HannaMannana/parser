package method;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.Product;
import org.example.method.JsonDeserialization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.example.method.JsonDeserialization.parseJsonArrayGen;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonDeserializationTest {
    private JsonDeserialization deserialization;

    @BeforeEach
    void setup (){
        deserialization = new JsonDeserialization();
    }

    @Test
    void getJsonStringFromObject() throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        // given
        String jsonArray = "[{\"id\":\"e18628d0-2738-4f57-bfb1-9b2e7967d318\",\"name\": \"Butter\",\"price\":1.0},{\"id\":\"f0445043-1fcc-4229-ba73-809c49e9e154\",\"name\": \"Water\",\"price\":1.12}]";

        ObjectMapper mapper = new ObjectMapper();
        List<Product> expected = mapper.readValue(jsonArray, new TypeReference<>() {
        });

        // when
        List<Product> actual = parseJsonArrayGen(jsonArray, Product.class);

        // then
        assertEquals(expected, actual);

    }

}