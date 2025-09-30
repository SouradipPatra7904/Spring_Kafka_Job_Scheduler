package souradippatra.Spring_Kafka_Job_Scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import souradippatra.Spring_Kafka_Job_Scheduler.entity.JobEntity;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {
}
