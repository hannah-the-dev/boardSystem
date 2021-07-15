package com.hannahj.springBoard.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("ROLE_ADMIN", "관리자"),
    USER("ROLE_USER", "회원"),
    GUEST("ROLE_GUEST", "게스트");
    
    private final String key;
    private final String membership;

}
