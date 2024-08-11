package org.example.taskservice.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.taskservice.dto.Role;

import java.util.Collection;

/**     * Сущность пользователя     */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {
    /** id пользователя     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    /** Имя пользователя     */
    @Column(name = "name")
    String Name;

    /**     * почта пользователя     */
    @Column(name = "email")
    String email;

    /**     * пароль пользователя     */
    @Column(name = "password")
    String password;



    /**     * роль пользователя     */
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    Role role;
}
