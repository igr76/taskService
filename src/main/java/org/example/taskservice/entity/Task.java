package org.example.taskservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.taskservice.dto.Status;

import java.util.List;

/**     * Сущность задач     */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tasks")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    /** id задачи     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;
    /** описание задачи    */
    String task;
    /** автор задачи    */
    @OneToOne
    UserEntity author;
    /** исполнитель задачи   */
    @OneToOne
    UserEntity executor;
    /** комментарии к задаче    */
    @ElementCollection
    @CollectionTable(name = "task_comment", joinColumns = @JoinColumn(name = "task_id"))
    List<String> comment;
    /** статут задачи     */
    Status status;
}
