package com.example.vmm408.messenger.models;

import java.util.Date;

public class MessageModel {
    private String loginM;
    private String message;
    private Date createDate;

    public MessageModel(String login, String message, Date createDate) {
        this.loginM = login;
        this.message = message;
        this.createDate = createDate;
    }

    public MessageModel() {
    }

    public void setLoginM(String loginM) {
        this.loginM = loginM;
    }

    public String getLoginM() {
        return loginM;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageModel that = (MessageModel) o;

        if (loginM != null ? !loginM.equals(that.loginM) : that.loginM != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        return createDate != null ? createDate.equals(that.createDate) : that.createDate == null;
    }

    @Override
    public int hashCode() {
        int result = loginM != null ? loginM.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
