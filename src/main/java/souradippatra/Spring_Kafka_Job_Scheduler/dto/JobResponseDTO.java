package souradippatra.Spring_Kafka_Job_Scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobResponseDTO {
    private Long jobId;
    private String jobName;
    private String status;
}
