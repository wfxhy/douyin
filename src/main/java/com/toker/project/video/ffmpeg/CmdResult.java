package com.toker.project.video.ffmpeg;

/**
 * Created by leo on 2017/2/10.
 * cmd命令执行后结果类
 */
public class CmdResult {
    private boolean success;
    private String msg;

    public CmdResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public CmdResult() {

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
