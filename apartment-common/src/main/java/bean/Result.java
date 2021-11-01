package bean;

import java.io.Serializable;

/**
 * @author HXJ
 * @create 2021-10-18 10:40
 */

public class Result<T> {
    private Boolean flag;
    private Integer StatusCode;
    private String desc;
    private T result;

    public Result(boolean flag, Integer StatusCode, String desc) {
        this.flag = flag;
        this.StatusCode = StatusCode;
        this.desc = desc;
    }

    public Result(Boolean flag, Integer statusCode, String desc, T result) {
        this.flag = flag;
        StatusCode = statusCode;
        this.desc = desc;
        this.result = result;
    }

    public Result() {
        this.flag = true;
        this.StatusCode = 200;
        this.desc = "执行成功";
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public void setStatusCode(Integer statusCode) {
        StatusCode = statusCode;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Boolean getFlag() {
        return flag;
    }

    public Integer getStatusCode() {
        return StatusCode;
    }

    public String getDesc() {
        return desc;
    }

    public T getResult() {
        return result;
    }
}
