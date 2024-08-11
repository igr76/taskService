package org.example.taskservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.example.taskservice.dto.GreatTaskDto;
import org.example.taskservice.dto.TaskDto;
import org.example.taskservice.dto.UserDto;
import org.example.taskservice.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/task")
@Slf4j
@Tag(name = "Пользователи")
@RestController
public class TaskCotroller {
    private TaskService taskService;

    public TaskCotroller(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Получить задачу по заголовку")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "204", description = "нет содержимого", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "неверная авторизация или аутентификация", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Не найдено", content = @Content(schema = @Schema()))
    })
    @GetMapping(value = "/{heading}")
    public ResponseEntity<TaskDto> getTasks(@PathVariable(name = "heading")
                                            @NotBlank(message = "заголовок не должен быть пустым") String heading/*, Authentication authentication*/) {
        log.info("controller Получить задачу");
        return ResponseEntity.ok(taskService.getTask(heading));
    }
    @Operation(summary = "Получить все задачи")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "204", description = "нет содержимого", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "неверная авторизация или аутентификация", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Не найдено", content = @Content(schema = @Schema()))
    })
    @GetMapping(value = "/all")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        log.info("controller Получить все задачи");
        return ResponseEntity.ok(taskService.getAllTasks());
    }
    @Operation(summary = "Получить все задачи по имени исполнителя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "204", description = "нет содержимого", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "неверная авторизация или аутентификация", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Не найдено", content = @Content(schema = @Schema()))
    })
    @GetMapping(value = "/all/{priority}")
    public ResponseEntity<List<TaskDto>> getTaskOfExecutor(@PathVariable(name = "executor")
                                                           @NotBlank(message = "поле имя исполнителя  не должен быть пустым") String executor) {
        log.info("controller Получить все задачи по имени исполнителя");
        return ResponseEntity.ok(taskService.getTaskOfExecutor(executor));
    }
    @Operation(summary = "Создать задачу")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "204", description = "нет содержимого", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "неверная авторизация или аутентификация", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Не найдено", content = @Content(schema = @Schema()))
    })
    @PostMapping
    public void greatTask(
            @RequestBody
            @NotBlank(message = "задача не должна быть пустой") GreatTaskDto greatTaskDto) {
        log.info("controller создать задачу");
        taskService.greatTask(greatTaskDto);
    }
    @Operation(summary = "Обновить задачу")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "204", description = "нет содержимого", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "неверная авторизация или аутентификация", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Не найдено", content = @Content(schema = @Schema()))
    })
    @PatchMapping()
    public ResponseEntity<TaskDto> updateTask(
            @RequestBody
            @NotBlank(message = "задача не должна быть пустой") TaskDto taskDto, Authentication authentication) {
        log.info("controller Обновить задачу");
        return ResponseEntity.ok(taskService.updateTask(taskDto,authentication));
    }
    @Operation(summary = "Обновить статус исполнения задачи")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "204", description = "нет содержимого", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "неверная авторизация или аутентификация", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Не найдено", content = @Content(schema = @Schema()))
    })
    @PatchMapping("/upPriority")
    public ResponseEntity<TaskDto> updatePriorityTask(
            @RequestBody
            @NotBlank(message = "задача не должна быть пустой") TaskDto taskDto) {
        log.info("controller Обновить статус исполнения задачи");
        return ResponseEntity.ok(taskService.updateStatusTask(taskDto));
    }
    @Operation(summary = "Удалить задачу")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "204", description = "нет содержимого", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "неверная авторизация или аутентификация", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Не найдено", content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{heading}")
    public void deleteTask(@PathVariable(name = "heading")
                           @NotBlank(message = "заголовок не должен быть пустым") String heading, Authentication authentication) {
        log.info("controller Удалить задачу");
        taskService.deleteTask(heading,authentication);
    }
}
