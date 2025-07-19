package com.lab.backend.dtos.admin;

import com.lab.backend.model.enums.ProfileType;

public class ProfileTypeUpdateDto {
    private ProfileType profileType;

    public ProfileTypeUpdateDto() {}

    public ProfileTypeUpdateDto(ProfileType profileType) {
        this.profileType = profileType;
    }

    public ProfileType getProfileType() {
        return profileType;
    }

    public void setProfileType(ProfileType profileType) {
        this.profileType = profileType;
    }
}
