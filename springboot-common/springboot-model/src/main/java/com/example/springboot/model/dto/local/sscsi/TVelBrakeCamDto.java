package com.example.springboot.model.dto.local.sscsi;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_vel_brake_cam", schema = "sscsi-local", catalog = "")
public class TVelBrakeCamDto {
    private int id;
    private String cloudId;
    private String parkid;
    private String gate;
    private String producerType;
    private String version;
    private Integer ipv4;
    private Integer port;
    private String userName;
    private String passward;
    private String direction;
    private String ionum;
    private String remark;
    private Integer status;
    private Timestamp createTime;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CLOUD_ID")
    public String getCloudId() {
        return cloudId;
    }

    public void setCloudId(String cloudId) {
        this.cloudId = cloudId;
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
    @Column(name = "GATE")
    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    @Basic
    @Column(name = "PRODUCER_TYPE")
    public String getProducerType() {
        return producerType;
    }

    public void setProducerType(String producerType) {
        this.producerType = producerType;
    }

    @Basic
    @Column(name = "VERSION")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Basic
    @Column(name = "IPV4")
    public Integer getIpv4() {
        return ipv4;
    }

    public void setIpv4(Integer ipv4) {
        this.ipv4 = ipv4;
    }

    @Basic
    @Column(name = "PORT")
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Basic
    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "PASSWARD")
    public String getPassward() {
        return passward;
    }

    public void setPassward(String passward) {
        this.passward = passward;
    }

    @Basic
    @Column(name = "DIRECTION")
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Basic
    @Column(name = "IONUM")
    public String getIonum() {
        return ionum;
    }

    public void setIonum(String ionum) {
        this.ionum = ionum;
    }

    @Basic
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "STATUS")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "CREATE_TIME")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TVelBrakeCamDto that = (TVelBrakeCamDto) o;
        return id == that.id && Objects.equals(cloudId, that.cloudId) && Objects.equals(parkid, that.parkid) && Objects.equals(gate, that.gate) && Objects.equals(producerType, that.producerType) && Objects.equals(version, that.version) && Objects.equals(ipv4, that.ipv4) && Objects.equals(port, that.port) && Objects.equals(userName, that.userName) && Objects.equals(passward, that.passward) && Objects.equals(direction, that.direction) && Objects.equals(ionum, that.ionum) && Objects.equals(remark, that.remark) && Objects.equals(status, that.status) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cloudId, parkid, gate, producerType, version, ipv4, port, userName, passward, direction, ionum, remark, status, createTime);
    }
}
