package com.attractor.library.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_authorities")
public class UserAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "authority_id", nullable = false)
    private Long authorityId;


    public UserAuthority(Long userId, Long authorityId) {
        this.userId = userId;
        this.authorityId = authorityId;
    }

}
