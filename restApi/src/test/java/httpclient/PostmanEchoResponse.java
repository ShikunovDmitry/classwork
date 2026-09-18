package httpclient;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostmanEchoResponse {
  private Object args;
  private Object data;

  @JsonProperty("headers")
  private Map<String, String> headers;

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public void setHeaders(Map<String, String> headers) {
    this.headers = headers;
  }

  public Object getArgs() {
    return args;
  }
  public void setArgs(Object args) {}
}
