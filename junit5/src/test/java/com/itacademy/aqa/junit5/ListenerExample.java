package com.itacademy.aqa.junit5;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.HashMap;
import java.util.Map;

public class ListenerExample implements TestWatcher, AfterAllCallback {

    private Map<String, String> testResultStatus = new HashMap<>();

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        System.out.printf("Test Successful for test %s", extensionContext.getDisplayName());
        System.out.println();
        testResultStatus.put(extensionContext.getUniqueId(), "Passed");
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        System.out.printf("Test Failed for test %s", extensionContext.getDisplayName());
        System.out.println();
        System.out.println("Reason: " + throwable.getMessage());
        testResultStatus.put(extensionContext.getUniqueId(), "Failed");
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
//        testResultStatus.entrySet()
//                .forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));

        testResultStatus.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}
