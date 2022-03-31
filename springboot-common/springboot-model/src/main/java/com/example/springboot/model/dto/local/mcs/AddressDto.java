package com.example.springboot.model.dto.local.mcs;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address", schema = "dg-mcs", catalog = "")
public class AddressDto {
    private int id;
    private String content;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDto that = (AddressDto) o;
        return id == that.id && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
    }


    public AddressDto(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public AddressDto() {
    }
}
