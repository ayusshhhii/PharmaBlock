package com.example.pharmablock.Models;

public class DetailsModel {
    private  String name, date, cost, amt, desc, cont, mname;
    private int hashvalue;

    public DetailsModel(){

    }

    public int getHashvalue() {
        return hashvalue;
    }

    public void setHashvalue(int hashvalue) {
        this.hashvalue = hashvalue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getMname() {return mname;}

    public void  setMname(String mname){ this.mname=mname;}
}
