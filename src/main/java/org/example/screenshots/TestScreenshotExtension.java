package org.example.screenshots;

import org.example.context.DriverFactory;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.qameta.allure.Allure.addAttachment;

public class TestScreenshotExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        try {
            String testName = context.getRequiredTestMethod().getName();
            File screenshot = ScreenshotUtil.takeScreenshot(DriverFactory.getDriver(), testName);

            Path screenshotPath = Path.of(screenshot.getPath());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Files.readAllBytes(screenshotPath));
            addAttachment("Screenshot", byteArrayInputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}