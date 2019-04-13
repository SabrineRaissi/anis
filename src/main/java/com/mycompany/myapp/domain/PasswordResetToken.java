package com.mycompany.myapp.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A PasswordResetToken.
 */
@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "expiry_date")
    private ZonedDateTime expiryDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public PasswordResetToken token(String token) {
        this.token = token;
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ZonedDateTime getExpiryDate() {
        return expiryDate;
    }

    public PasswordResetToken expiryDate(ZonedDateTime expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public void setExpiryDate(ZonedDateTime expiryDate) {
        this.expiryDate = expiryDate;
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
        PasswordResetToken passwordResetToken = (PasswordResetToken) o;
        if (passwordResetToken.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), passwordResetToken.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PasswordResetToken{" +
            "id=" + getId() +
            ", token='" + getToken() + "'" +
            ", expiryDate='" + getExpiryDate() + "'" +
            "}";
    }
}
