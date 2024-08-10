package org.example.taskservice.dto;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class RefreshJwtRequest {

    public String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
