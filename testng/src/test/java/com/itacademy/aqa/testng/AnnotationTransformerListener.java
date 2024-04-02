package com.itacademy.aqa.testng;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.IConfigurationAnnotation;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformerListener implements IAnnotationTransformer {

    @Override
    public void transform(
            ITestAnnotation annotation,
            Class testClass,
            Constructor testConstructor,
            Method testMethod) {

        annotation.setRetryAnalyzer(RetryAnalyzer.class);

        if (annotation.getDescription() == null || annotation.getDescription().isEmpty()) {
            if (testMethod != null) {
                annotation.setDescription(testMethod.getName());
            }
        }

    }
}
