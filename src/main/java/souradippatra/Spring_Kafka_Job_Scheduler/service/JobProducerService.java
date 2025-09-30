package souradippatra.Spring_Kafka_Job_Scheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;
import souradippatra.Spring_Kafka_Job_Scheduler.config.KafkaConfig;

@Service
@RequiredArgsConstructor
public class JobProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaConfig kafkaConfig;

    public void sendJob(String jobData) {
        kafkaTemplate.send(kafkaConfig.getJobTopic(), jobData);
        System.out.println("[Kafka Producer] Sent job: " + jobData);
    }
}
