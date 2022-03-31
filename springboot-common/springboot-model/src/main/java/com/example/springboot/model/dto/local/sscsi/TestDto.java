package com.example.springboot.model.dto.local.sscsi;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "test", schema = "sscsi-local", catalog = "")
public class TestDto {
    private int id;

    @Size(min = 1,max = 5,message = "内容范围1~5位")
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


    public TestDto(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public TestDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestDto testDto = (TestDto) o;
        return id == testDto.id && Objects.equals(content, testDto.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
    }
}
