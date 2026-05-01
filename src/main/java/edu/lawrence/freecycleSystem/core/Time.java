package edu.lawrence.freecycleSystem.core;

public class Time {
    private String time_id;
    private int start_time;
    private int end_time;
    private String site_id;
    private int availability; 
    private String donor_id; 
    
    Time(){}

    public String getTimeId(){
        return time_id;
    }

    public void setTimeId(String time_id){
        this.time_id = time_id;
    }

    public int getStartTime(){
        return start_time;
    }

    public void setStartTime(int start_time){
        this.start_time = start_time;
    }

    public int getEndTime(){
        return end_time;
    }

    public void setEndTime(int end_time){
        this.end_time = end_time;
    }

    public String getSiteId(){
        return this.site_id;
    }

    public void setSiteId(String site_id){
        this.site_id = site_id;
    }

    public int getAvailability(){
        return this.availability;
    }

    public void setAvailability(int availability){
        this.availability = availability;
    }
    public String getDonorId(){
        return this.donor_id;
    }

    public void setDonorId(String donor_id){
        this.donor_id = donor_id;
    }
}
