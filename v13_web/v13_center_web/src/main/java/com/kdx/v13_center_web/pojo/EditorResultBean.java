package com.kdx.v13_center_web.pojo;

/**
 * @author kdx
 * @Date 2019/6/14
 */
public class EditorResultBean {

    private String errno;
    private String[] data;

    public EditorResultBean() {
    }

    public EditorResultBean(String errno, String[] data) {
        this.errno = errno;
        this.data = data;
    }

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
