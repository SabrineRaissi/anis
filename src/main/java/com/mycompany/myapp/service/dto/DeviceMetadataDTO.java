package com.mycompany.myapp.service.dto;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DeviceMetadata entity.
 */
public class DeviceMetadataDTO implements Serializable {

    private Long id;

    private Long userIdDevice;

    private String deviceDetails;

    private String location;

    private ZonedDateTime lastLoggedIn;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserIdDevice() {
        return userIdDevice;
    }

    public void setUserIdDevice(Long userIdDevice) {
        this.userIdDevice = userIdDevice;
    }

    public String getDeviceDetails() {
        return deviceDetails;
    }

    public void setDeviceDetails(String deviceDetails) {
        this.deviceDetails = deviceDetails;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ZonedDateTime getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(ZonedDateTime lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeviceMetadataDTO deviceMetadataDTO = (DeviceMetadataDTO) o;
        if (deviceMetadataDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), deviceMetadataDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DeviceMetadataDTO{" +
            "id=" + getId() +
            ", userIdDevice=" + getUserIdDevice() +
            ", deviceDetails='" + getDeviceDetails() + "'" +
            ", location='" + getLocation() + "'" +
            ", lastLoggedIn='" + getLastLoggedIn() + "'" +
            "}";
    }
}
