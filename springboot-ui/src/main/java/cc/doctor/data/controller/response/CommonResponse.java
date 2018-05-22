package cc.doctor.data.controller.response;


import java.io.Serializable;

/**
 * Created by doctor on 2018/1/21.
 */
public class CommonResponse<T> implements Serializable {
    private static final long serialVersionUID = 7772215336035715481L;

    private boolean success;
    private Integer code;
    private String message;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> CommonResponse<T> successResponse(T data) {
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setSuccess(true);
        commonResponse.setData(data);
        return commonResponse;
    }

    public static CommonResponse failedResponse() {
        CommonResponse commonResponse = new CommonResponse<>();
        commonResponse.setSuccess(false);
        return commonResponse;
    }

    public static CommonResponse invalidParamResponse(String message) {
        CommonResponse commonResponse = failedResponse();
        commonResponse.setMessage(message);
        return commonResponse;
    }
}
