package com.itacademy.aqa.reporting;

import io.qameta.allure.Attachment;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenShotMaker {

    @Attachment
    public static byte[] makeScreenSot(){
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/screenshot.png");
            byte[] screen = new byte[(int) Files.size(Paths.get("src/test/resources/screenshot.png"))];
            fis.read(screen);
            fis.close();
            return screen;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
