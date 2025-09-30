package souradippatra.Spring_Kafka_Job_Scheduler.exception;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(String message) {
        super(message);
    }
}
