package com.musicShare.musicShare.entities;

import java.time.LocalDateTime;

import java.sql.Timestamp;

public class RentPlayList {

    private Long renderer;
    private Long renderee;
    private float time;
    private Timestamp startDate;

    public RentPlayList(Long renderer, Long renderee) {
        this.renderer = renderer;
        this.renderee = renderee;
        this.startDate = Timestamp.valueOf(LocalDateTime.now());
    }

    public Long getRenderer() {
        return renderer;
    }

    public void setRenderer(Long renderer) {
        this.renderer = renderer;
    }

    public Long getRenderee() {
        return renderee;
    }

    public void setRenderee(Long renderee) {
        this.renderee = renderee;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }
}
