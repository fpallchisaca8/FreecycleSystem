package edu.lawrence.freecycleSystem.core;

public class Site {
    private String site_id;
    private String site_name;
    private String address;


    Site(){}

    public String getSiteId(){
        return site_id;
    }

    public void setSiteId(String site_id){
        this.site_id = site_id;
    }

    public String getSiteName(){
        return site_name;
    }

    public String setSiteName(String site_name){
        this.site_name = site_name;
        return site_name;
    }

    public String getAddress(){
        return address;
    }

    public String setAddress(String address){
        this.address = address;
        return address;
    }

}

