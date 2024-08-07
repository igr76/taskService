package org.example.taskservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.example.taskservice.dto.NewPassword;
import org.example.taskservice.dto.UserDto;
import org.example.taskservice.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping("/users")
@Slf4j
@Tag(name = "Пользователи")
@RestController
public class UserController {


  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Operation(summary = "Установить новый пароль")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK", content =
          @Content(
              array = @ArraySchema(schema = @Schema(implementation = NewPassword.class)))),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
      @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
  })
  @PostMapping(value = "/setPassword")
  public ResponseEntity<NewPassword> setPassword(
      @RequestBody NewPassword newPassword) {
    log.info("setPasswordController");
    NewPassword newPasswordDTO = userService.setPassword(newPassword);
    return ResponseEntity.ok(newPasswordDTO);
  }

  @Operation(summary = "Получить пользователя")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK",
          content = @Content(
              array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
      @ApiResponse(responseCode = "401", description = "Unauthorized",
          content = @Content(schema = @Schema())),
      @ApiResponse(responseCode = "403", description = "Forbidden",
          content = @Content(schema = @Schema())),
      @ApiResponse(responseCode = "404", description = "Not Found",
          content = @Content(schema = @Schema()))
  })
  @GetMapping(value = "/me")
  public ResponseEntity<UserDto> getUser(Authentication authentication) {
    log.info("getUser");
    return ResponseEntity.ok(userService.getUser(authentication));
  }

  @Operation(summary = "Обновить пользователя")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK",
          content = @Content(
              array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
      @ApiResponse(responseCode = "204", description = "No Content",
          content = @Content(schema = @Schema())),
      @ApiResponse(responseCode = "401", description = "Unauthorized",
          content = @Content(schema = @Schema())),
      @ApiResponse(responseCode = "403", description = "Forbidden",
          content = @Content(schema = @Schema())),
      @ApiResponse(responseCode = "404", description = "Not Found",
          content = @Content(schema = @Schema()))
  })
  @PatchMapping(value = "/me")
  public ResponseEntity<UserDto> updateUser(
      @RequestBody
      UserDto userDto, Authentication authentication) {
    log.info("updateUser");
    return ResponseEntity.ok(userService.updateUser(userDto, authentication));
  }


    @Operation(summary = "Получить пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
    })
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserDto> findById(
            @PathVariable(name = "id") @NonNull @Parameter(description = "Больше 0, Например 1") Integer id,
            Authentication authentication) {
        log.info("findById");
        return ResponseEntity.ok(userService.findById(id,authentication));
    }
    @Operation(summary = "отправить сообщение другу")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(
                                    schema = @Schema(ref = "#/components/schemas/AdsDTO"))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PatchMapping("/message/{id}")
    public void messageOfFriend(
            @PathVariable(name = "id") @NonNull @Parameter(description = "Больше 0, Например 1") Integer id,
            @RequestBody @NonNull String message) {
      userService.messageOfFriend(id,message);

    }





}