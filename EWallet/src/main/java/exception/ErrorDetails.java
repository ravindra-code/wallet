package exception;

public class ErrorDetails {

    private Integer code;
    private String description;
    private String parameter;
    private String message;

    public ErrorDetails() {
    }

    public ErrorDetails(Integer code, String description, String parameter, String message) {
        this.code = code;
        this.description = description;
        this.parameter = parameter;
        this.message = message;
    }

    @Override
    public String toString() {
        String str =  "ErrorDetails{code=" + code;
        if (message != null && !message.isEmpty()) {
            str = str.concat(", message='" + message + "'");
        } else {
            str = str.concat(", description='" + description + "'");
            str = str.concat(", parameter='" + parameter + "'");
        }
        str = str.concat("}");
        return str;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getParameter() {
        return parameter;
    }

    public String getMessage() {
        return message;
    }
}
