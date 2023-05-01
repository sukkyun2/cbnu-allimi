package com.news.noti.api.user.query;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

@Getter
@Immutable
@NoArgsConstructor
@Entity
@Table(name = "NT_USER")
public class UserData {
    @Id
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TOKEN")
    private String token;
}
