package org.example.context;

import lombok.SneakyThrows;
import org.openqa.selenium.support.PageFactory;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.example.DriverFactory.getDriver;

public final class PageContext {

    private final Reflections pageReflections;
    private final Reflections testReflections;

    private final Map<Class<?>, Object> context = new HashMap<>();

    @SneakyThrows
    public PageContext() {
        pageReflections = new Reflections("org.example.pages");

        URL testClassesURL = Paths.get("target/test-classes").toUri().toURL();
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{testClassesURL},
                ClasspathHelper.staticClassLoader());

        testReflections = new Reflections(new ConfigurationBuilder()
                .addUrls(ClasspathHelper.forPackage("org.example", classLoader))
                .filterInputsBy(new FilterBuilder().includePackage("org.example"))
                .setScanners(Scanners.FieldsAnnotated));
    }

    @SneakyThrows
    public void createContext() {
        // 1. find all classes annotated as @Page
        Set<Class<?>> pages = pageReflections.getTypesAnnotatedWith(Page.class);

        for (Class<?> page : pages) {
            // 2. Create new instance (new LoginPage());
            Object pageObject = page.getDeclaredConstructor().newInstance();

            // 3. Init @FindBy
            PageFactory.initElements(getDriver(), pageObject);

            // 4. Push to context (e.g LoginPage.class, LoginPage())
            context.put(page, pageObject);
        }

        // 5. Find all by @Autowired
        Set<Field> fieldsAnnotatedWith = testReflections.getFieldsAnnotatedWith(Autowired.class);

        for (Field field : fieldsAnnotatedWith) {

            // 6. Find type of field (e.g. LoginPage.class)
            Class<?> type = field.getType();

            // 7. Get page object itself from context (LoginPage.class -> LoginPage())
            Object pageObject = context.get(type);

            // 8. Set field value as pageObject (e.g private static LoginPage loginpage = LoginPage())
            field.setAccessible(true);
            field.set(null, pageObject);
        }
    }
}
