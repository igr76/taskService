package org.example.taskservice.service;


import org.example.taskservice.dto.Role;
import org.example.taskservice.entity.UserEntity;
import org.example.taskservice.exception.ElemNotFound;
import org.example.taskservice.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
  //  PostRepository postRepository;


    private UserRepository userRepository;

    /** Проверка пользователя на авторство */
    public boolean checkAuthor(int id, UserEntity user) {
        return id == user.getId();
    }
    /** Проверка автора сообщения на электронную почту */
    public boolean checkAuthor(int id, String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(ElemNotFound::new);
        return checkAuthor(id, user);
    }
    /** Проверка роли администратора по Authentication */
    public boolean checkAuthorRoleFromAuthentication(Authentication authentication) {
        UserEntity user = userRepository.findByEmail(authentication.getName()).orElseThrow(ElemNotFound::new);
        return isAdmin(user);
    }
    /** Проверка пользователя на электронную почту */
    public boolean isAuthorAuthenticated(String email, Authentication authentication) {
        return authentication.getName().equals(email) && authentication.isAuthenticated();
    }

    public boolean isAuthorAuthenticated(int id, Authentication authentication) {
        UserEntity user = userRepository.findById(id).orElseThrow(ElemNotFound::new);
        return isAuthorAuthenticated(user.getEmail(), authentication);
    }

    /** Проверка пользователя на роль администратора */
    public boolean isAdmin(UserEntity user) {
        return user.getRole().equals(Role.ADMIN);
    }

    public boolean isAdmin(Authentication authentication) {
        return authentication.isAuthenticated() &&
                authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
    /** Проверка законности доступа к методам комментариям */
    public boolean isCommentUpdateAvailable(Authentication authentication, int commentEntityAuthorId,
                                            int commentDTOAuthorId) {
        if (isUpdateAvailable(authentication)) {
            return true;
        }
        if (checkAuthor(commentEntityAuthorId, authentication.getName()) &&
                commentEntityAuthorId == commentDTOAuthorId) {
            return true;
        }
        return false;
    }

    /** Проверка законности доступа к методам сообщений */
    public boolean isAdsUpdateAvailable(Authentication authentication, int AuthorId) {
        if (isUpdateAvailable(authentication)) {
            return true;
        }
        if (checkAuthor(AuthorId, authentication.getName())) {
            return true;
        }
        return false;
    }

    /** Проверка возможности обновления */
    private boolean isUpdateAvailable(Authentication authentication) {
        if (!authentication.isAuthenticated()) {
            return false;
        }
        if (isAdmin(authentication)) {
            return true;
        }
        return false;
    }
}
