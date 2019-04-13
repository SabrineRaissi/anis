package com.mycompany.myapp.service.dto;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the PasswordResetToken entity.
 */
public class PasswordResetTokenDTO implements Serializable {

    private Long id;

    private String token;

    private ZonedDateTime expiryDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ZonedDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(ZonedDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PasswordResetTokenDTO passwordResetTokenDTO = (PasswordResetTokenDTO) o;
        if (passwordResetTokenDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), passwordResetTokenDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PasswordResetTokenDTO{" +
            "id=" + getId() +
            ", token='" + getToken() + "'" +
            ", expiryDate='" + getExpiryDate() + "'" +
            "}";
    }
}
