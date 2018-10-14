package com.lzahumna.property.loader;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * Property loader.
 * <p>
 * Fetch app.properties from classpath and load to properties
 * Return property value by name if exists, otherwise throw exception
 *
 * @author Liudmyla Zahumna
 * since 12/10/2018.
 */
public class PropertyLoader {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream stream = PropertyLoader.class.getClassLoader().getResourceAsStream("app.properties")) {
            PROPERTIES.load(stream);
        } catch (Exception e) {
            throw new IllegalStateException("cannot load properties from app.properties", e);
        }
    }

    public static String getPropertyByName(String name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("Property name is missing");
        }

        return PROPERTIES.getProperty(name);
    }

}
