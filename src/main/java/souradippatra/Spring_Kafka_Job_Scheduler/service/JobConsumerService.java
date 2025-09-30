package souradippatra.Spring_Kafka_Job_Scheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import souradippatra.Spring_Kafka_Job_Scheduler.config.KafkaConfig;

@Service
@RequiredArgsConstructor
public class JobConsumerService {

    private final KafkaConfig kafkaConfig;

    @KafkaListener(topics = "#{kafkaConfig.getJobTopic()}", groupId = "job_scheduler_group")
    public void consume(String jobData) {
        System.out.println("[Kafka Consumer] Executing Job: " + jobData);
        // Here you can trigger Quartz jobs dynamically if needed
    }
}
