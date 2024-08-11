package org.example.taskservice.dto;




import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;

/**
 * DTO пользователя
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    /** Имя пользователя     */
    String name;

    /**     * почта пользователя     */
    String email;

    /**     * пароль пользователя     */
    String password;



}
