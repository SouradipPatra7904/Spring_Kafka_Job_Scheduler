package souradippatra.Spring_Kafka_Job_Scheduler.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobName;

    @Column(columnDefinition = "TEXT")
    private String jobData;

    private String status; // PENDING, RUNNING, COMPLETED, FAILED

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
