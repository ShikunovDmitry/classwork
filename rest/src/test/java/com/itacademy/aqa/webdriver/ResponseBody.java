package com.itacademy.aqa.webdriver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseBody {
    private Object args;
    @JsonProperty(value = "data")
    private Object jsonBody;
//    @JsonProperty(value = "file_data")
    private Object files;
    private Object field;
    private Object form;
    private Map<String, String> headers;
    private Object json;

    public String getVersion() {
        return ver;
    }

    public void setVersion(String version) {
        this.ver = version;
    }

    private String ver;

    public Object getFiles() {
        return files;
    }

    public void setFiles(Object files) {
        this.files = files;
    }

    public Object getField() {
        return field;
    }

    public void setField(Object field) {
        this.field = field;
    }

    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Object getForm() {
        return form;
    }

    public void setForm(Object form) {
        this.form = form;
    }

    public Object getJsonBody() {
        return jsonBody;
    }

    public void setJsonBody(Object jsonBody) {
        this.jsonBody = jsonBody;
    }

    public Object getArgs() {
        return args;
    }

    public void setArgs(Object args) {
        this.args = args;
    }

    private String url;
}
