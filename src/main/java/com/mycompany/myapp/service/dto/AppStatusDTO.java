package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AppStatus entity.
 */
public class AppStatusDTO implements Serializable {

    private Long id;

    private String designation;

    private Integer code;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AppStatusDTO appStatusDTO = (AppStatusDTO) o;
        if (appStatusDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), appStatusDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AppStatusDTO{" +
            "id=" + getId() +
            ", designation='" + getDesignation() + "'" +
            ", code=" + getCode() +
            "}";
    }
}
