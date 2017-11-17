package person.andy.common;

/**
 * @Author: AndyCui
 * @Date: 2017/11/11 17:45
 * @Description:
 */
public class ResponseObject<T> {
    private int code;
    private String message;
    private T data;

    public ResponseObject() {
    }

    public ResponseObject(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    /**
     * 成功返回
     */
    public static <T> ResponseObject successResponse(T data,String message){
        return new ResponseObject(200,message,data);
    }
    /**
     * 失败返回 不带返回数据
     */
    public static <T> ResponseObject errorResponseWithOutData(int code,String message){
        return new ResponseObject(code,message,null);
    }

    public ResponseObject(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**

     * 失败返回 带返回数据
     */
    public static <T> ResponseObject errorResponseWithData(int code,String message,T data){
        return new ResponseObject(code,message,data);
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
}
