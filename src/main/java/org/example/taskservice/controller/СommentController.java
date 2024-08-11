package org.example.taskservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.taskservice.dto.UserDto;
import org.example.taskservice.dto.СommentDto;
import org.example.taskservice.service.СommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Контроллер комментариев  */
@RequestMapping("/comment")
@Slf4j
@RequiredArgsConstructor
@RestController
public class СommentController {
    private final СommentService commentService;

    @Operation(summary = "Получить все комментарии к задаче")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "204", description = "нет содержимого", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "неверная авторизация или аутентификация", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Не найдено", content = @Content(schema = @Schema()))
    })
    @GetMapping(value = "/all/{id}")
    public ResponseEntity<List<СommentDto>> getAllСommentsOfTask(@PathVariable(name = "id")
                                                         @NotBlank(message = "id задачи") int id) {
        log.info("controller Получить все комментарии");
        return ResponseEntity.ok(commentService.getAllСommentsOfTask(id));
    }
    @Operation(summary = "Создать комментарий")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "204", description = "нет содержимого", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "неверная авторизация или аутентификация", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Не найдено", content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<СommentDto> greatСomment(
            @RequestBody
            @NotBlank(message = "задача не должна быть пустой") СommentDto commentDto/*, Authentication authentication*/) {
        log.info("controller создать комментарий");
        return ResponseEntity.ok(commentService.greatСomment(commentDto));
    }
    @Operation(summary = "Удалить комментарий")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "204", description = "нет содержимого", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "неверная авторизация или аутентификация", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Не найдено", content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable(name = "id")
                               @NotBlank(message = "id задачи") int id/*, Authentication authentication*/) {
        log.info("controller Удалить комментарий");
        commentService.deleteСomment(id);
    }
}
