package com.example.onthi_app.Model;

public class Response <T>{
    int status;
    String masage;
    T data;

    public Response() {
    }

    public Response(int status, String masage, T data) {
        this.status = status;
        this.masage = masage;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMasage() {
        return masage;
    }

    public void setMasage(String masage) {
        this.masage = masage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
