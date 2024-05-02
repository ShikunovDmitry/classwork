package com.itacademy.aqa.cucumber.stepdefs;

import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.cucumberexpressions.ParameterType;
import io.cucumber.datatable.DataTableType;

import java.util.Arrays;
import java.util.List;

public class TypeRegistryConfiguration implements TypeRegistryConfigurer {

    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineParameterType(new ParameterType<>("stringList",
                ".+",
                List.class,
                this::transformStringsToList));
        typeRegistry.defineDataTableType(new DataTableType(Boolean.class,
                Boolean::parseBoolean));
    }

    private List<String> transformStringsToList(String strings) {
        return Arrays.asList(strings.split(","));
    }
}
