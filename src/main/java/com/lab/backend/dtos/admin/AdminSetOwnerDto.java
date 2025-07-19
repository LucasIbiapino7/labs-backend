package com.lab.backend.dtos.admin;

import jakarta.validation.constraints.NotNull;

public class AdminSetOwnerDto {
    @NotNull
    private Long ownerId;

    public AdminSetOwnerDto() {
    }

    public Long getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
