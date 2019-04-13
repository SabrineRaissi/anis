package com.mycompany.myapp.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A AppStatus.
 */
@Entity
@Table(name = "app_status")
public class AppStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "designation")
    private String designation;

    @Column(name = "code")
    private Integer code;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public AppStatus designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getCode() {
        return code;
    }

    public AppStatus code(Integer code) {
        this.code = code;
        return this;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AppStatus appStatus = (AppStatus) o;
        if (appStatus.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), appStatus.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AppStatus{" +
            "id=" + getId() +
            ", designation='" + getDesignation() + "'" +
            ", code=" + getCode() +
            "}";
    }
}
