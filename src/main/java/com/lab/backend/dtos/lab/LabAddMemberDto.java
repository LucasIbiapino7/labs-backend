package com.lab.backend.dtos.lab;

import com.lab.backend.model.enums.LabRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LabAddMemberDto {
    @NotNull(message = "campo requerido!")
    private Long userId;
    private LabRole labRole;

    public LabAddMemberDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LabRole getLabRole() {
        return labRole;
    }

    public void setLabRole(LabRole labRole) {
        this.labRole = labRole;
    }
}
