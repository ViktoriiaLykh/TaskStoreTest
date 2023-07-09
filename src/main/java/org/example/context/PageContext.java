package org.example.context;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.example.context.DriverFactory.getDriver;

public final class PageContext {

    private final Reflections pageReflections = new Reflections("org.example.pages");

    private final Map<Class<?>, Object> context = new HashMap<>();


    public void createContext(Object invoker) throws Exception {
        initDriver(invoker);

        Set<Class<?>> pages = pageReflections.getTypesAnnotatedWith(Page.class);

        for (Class<?> aClass : pages) {
            initPage(aClass);
        }

        Field[] declaredFields = invoker.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Wired.class)) {
                autowireField(invoker, field);
            }
        }
    }

    private void initDriver(Object invoker) throws Exception {
        Driver annotation = invoker.getClass().getAnnotation(Driver.class);
        if (annotation == null) {
            throw new RuntimeException("Driver type not declared");
        }

        Class<? extends WebDriver> value = annotation.value();
        WebDriver webDriver = value.getDeclaredConstructor().newInstance();
        DriverFactory.createDriver(webDriver);
    }

    private void initPage(Class<?> page) throws Exception {
        Object pageObject = page.getDeclaredConstructor().newInstance();
        PageFactory.initElements(getDriver(), pageObject);
        context.put(page, pageObject);
    }

    private void autowireField(Object invoker, Field field) throws Exception {
        Class<?> type = field.getType();
        Object pageObject = context.get(type);
        field.setAccessible(true);
        field.set(invoker, pageObject);
    }
}
