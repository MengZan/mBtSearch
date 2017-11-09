package cn.mayzan.mBtSearch.common;

public class ResponseResult {

    /**
     * 0：正常；1：错误；2：未登录；3：其他
     */
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public static ResponseResult build(Integer status, String msg, Object data) {
        return new ResponseResult(status, msg, data);
    }

    public static ResponseResult ok(Object data) {
        return new ResponseResult(data);
    }

    public static ResponseResult ok() {
        return new ResponseResult(null);
    }

    public ResponseResult() {  }

    public static ResponseResult build(Integer status, String msg) {
        return new ResponseResult(status, msg, null);
    }

    public ResponseResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(Object data) {
        this.status = 0;
        this.msg = null;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
