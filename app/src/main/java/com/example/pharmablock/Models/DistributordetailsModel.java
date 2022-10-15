package com.example.pharmablock.Models;

import com.example.pharmablock.Distributor.DistributorDetails;

public class DistributordetailsModel {
    private String company_name, dist_date, product_disc, contact;

    public DistributordetailsModel(){

    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getDist_date() {
        return dist_date;
    }

    public void setDist_date(String dist_date) {
        this.dist_date = dist_date;
    }

    public String getProduct_disc() {
        return product_disc;
    }

    public void setProduct_disc(String product_disc) {
        this.product_disc = product_disc;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
