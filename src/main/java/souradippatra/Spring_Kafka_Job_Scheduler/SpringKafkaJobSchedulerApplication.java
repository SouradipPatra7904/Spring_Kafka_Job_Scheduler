package souradippatra.Spring_Kafka_Job_Scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration.class
})
public class SpringKafkaJobSchedulerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaJobSchedulerApplication.class, args);
    }
}

