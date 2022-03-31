package com.example.springboot.model.dto.local.sscsi;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_vel_brake_log", schema = "sscsi-local", catalog = "")
public class TVelBrakeLogDto {
    private int id;
    private String bookingid;
    private String plateno;
    private String parkid;
    private int camid;
    private String action;
    private Timestamp time;
    private Timestamp createTime;
    private String remark;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "BOOKINGID")
    public String getBookingid() {
        return bookingid;
    }

    public void setBookingid(String bookingid) {
        this.bookingid = bookingid;
    }

    @Basic
    @Column(name = "PLATENO")
    public String getPlateno() {
        return plateno;
    }

    public void setPlateno(String plateno) {
        this.plateno = plateno;
    }

    @Basic
    @Column(name = "PARKID")
    public String getParkid() {
        return parkid;
    }

    public void setParkid(String parkid) {
        this.parkid = parkid;
    }

    @Basic
    @Column(name = "CAMID")
    public int getCamid() {
        return camid;
    }

    public void setCamid(int camid) {
        this.camid = camid;
    }

    @Basic
    @Column(name = "ACTION")
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Basic
    @Column(name = "TIME")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "CREATE_TIME")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TVelBrakeLogDto that = (TVelBrakeLogDto) o;
        return id == that.id && camid == that.camid && Objects.equals(bookingid, that.bookingid) && Objects.equals(plateno, that.plateno) && Objects.equals(parkid, that.parkid) && Objects.equals(action, that.action) && Objects.equals(time, that.time) && Objects.equals(createTime, that.createTime) && Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookingid, plateno, parkid, camid, action, time, createTime, remark);
    }
}
