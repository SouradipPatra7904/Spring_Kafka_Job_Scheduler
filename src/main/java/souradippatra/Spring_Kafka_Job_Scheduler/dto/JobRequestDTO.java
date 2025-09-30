package souradippatra.Spring_Kafka_Job_Scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRequestDTO {
    private String jobName;
    private String jobData;
    private String cronExpression; // Optional: for scheduling recurring jobs
}
