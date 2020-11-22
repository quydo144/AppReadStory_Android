package com.kizias.readstory.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MessageHistory {
    @SerializedName("success")
    @Expose
    private int success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private History data;
    @SerializedName("danhsach")
    @Expose
    private ArrayList<History> danhsach;

    public ArrayList<History> getDanhsach() {
        return danhsach;
    }

    public void setDanhsach(ArrayList<History> danhsach) {
        this.danhsach = danhsach;
    }

    public History getData() {
        return data;
    }

    public void setData(History data) {
        this.data = data;
    }


    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
