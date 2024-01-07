package com.example.board.domain.file.entity;

import lombok.Getter;

@Getter
public enum FileDomain {

    MEMBER("member"),
    REVIEW("review");

    private String domain;

    FileDomain(String domain) {
        this.domain = domain;
    }
}
