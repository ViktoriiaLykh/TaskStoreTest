package org.example.screenshots;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class ScreenshotUtil {

    private static final Path screenshotsFolder = Paths.get("target", "screenshots");

    public static File takeScreenshot(WebDriver driver, String screenshotName) throws Exception {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path screenshotPath = Paths.get("target", "screenshots", screenshotName + ".png");
        if (!Files.exists(screenshotsFolder)) {
            Files.createDirectories(screenshotsFolder);
        }

        File file = screenshotPath.toFile();
        FileHandler.copy(screenshot, file);
        return screenshot;
    }
}
