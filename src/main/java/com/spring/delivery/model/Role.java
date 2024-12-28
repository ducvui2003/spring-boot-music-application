/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 5:22 PM - 10/08/2024
 * User: lam-nguyen
 **/
package com.spring.delivery.model;

import com.spring.delivery.util.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Role extends BaseModel implements Serializable {
    @Enumerated(EnumType.STRING)
    RoleEnum name;

    @OneToMany(mappedBy = "role")
    Set<User> users;
}
