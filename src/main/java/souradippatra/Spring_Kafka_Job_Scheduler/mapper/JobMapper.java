package souradippatra.Spring_Kafka_Job_Scheduler.mapper;

import org.springframework.stereotype.Component;
import souradippatra.Spring_Kafka_Job_Scheduler.dto.JobResponseDTO;
import souradippatra.Spring_Kafka_Job_Scheduler.entity.JobEntity;

@Component
public class JobMapper {

    public JobResponseDTO toDTO(JobEntity entity) {
        return new JobResponseDTO(
                entity.getId(),
                entity.getJobName(),
                entity.getStatus()
        );
    }
}
