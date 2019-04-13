package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the StatusSession entity.
 */
public class StatusSessionDTO implements Serializable {

    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StatusSessionDTO statusSessionDTO = (StatusSessionDTO) o;
        if (statusSessionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), statusSessionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StatusSessionDTO{" +
            "id=" + getId() +
            "}";
    }
}
