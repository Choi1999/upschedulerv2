package com.sparta.upschedulerv2.schedule.dto;

public class ScheduleRequestDto {
    private String title;
    private String description;

    // Getter 및 Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}