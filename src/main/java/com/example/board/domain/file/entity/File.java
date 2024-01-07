package com.example.board.domain.file.entity;

import com.example.board.standard.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
public class File extends BaseEntity {

    @Enumerated(value = EnumType.STRING)
    private FileDomain domain;

    private String uuid;

    private String name;

    private Long size;

    private String ext;

    public String toUri(String origin) {
        return origin
                + java.io.File.separator + this.domain.getDomain()
                + java.io.File.separator + this.uuid + "." + this.ext;
    }

    public String toPath(String root) {
        return root
                + java.io.File.separator + this.domain.getDomain()
                + java.io.File.separator + this.uuid + "." + this.ext;
    }
}
