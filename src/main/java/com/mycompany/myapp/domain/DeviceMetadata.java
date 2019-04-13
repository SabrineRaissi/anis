package com.mycompany.myapp.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DeviceMetadata.
 */
@Entity
@Table(name = "device_metadata")
public class DeviceMetadata implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id_device")
    private Long userIdDevice;

    @Column(name = "device_details")
    private String deviceDetails;

    @Column(name = "location")
    private String location;

    @Column(name = "last_logged_in")
    private ZonedDateTime lastLoggedIn;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserIdDevice() {
        return userIdDevice;
    }

    public DeviceMetadata userIdDevice(Long userIdDevice) {
        this.userIdDevice = userIdDevice;
        return this;
    }

    public void setUserIdDevice(Long userIdDevice) {
        this.userIdDevice = userIdDevice;
    }

    public String getDeviceDetails() {
        return deviceDetails;
    }

    public DeviceMetadata deviceDetails(String deviceDetails) {
        this.deviceDetails = deviceDetails;
        return this;
    }

    public void setDeviceDetails(String deviceDetails) {
        this.deviceDetails = deviceDetails;
    }

    public String getLocation() {
        return location;
    }

    public DeviceMetadata location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ZonedDateTime getLastLoggedIn() {
        return lastLoggedIn;
    }

    public DeviceMetadata lastLoggedIn(ZonedDateTime lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
        return this;
    }

    public void setLastLoggedIn(ZonedDateTime lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
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
        DeviceMetadata deviceMetadata = (DeviceMetadata) o;
        if (deviceMetadata.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), deviceMetadata.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DeviceMetadata{" +
            "id=" + getId() +
            ", userIdDevice=" + getUserIdDevice() +
            ", deviceDetails='" + getDeviceDetails() + "'" +
            ", location='" + getLocation() + "'" +
            ", lastLoggedIn='" + getLastLoggedIn() + "'" +
            "}";
    }
}
