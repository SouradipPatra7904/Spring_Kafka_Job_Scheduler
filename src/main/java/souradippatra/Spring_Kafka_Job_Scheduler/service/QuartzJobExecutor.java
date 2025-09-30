package souradippatra.Spring_Kafka_Job_Scheduler.service;

import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import souradippatra.Spring_Kafka_Job_Scheduler.repository.JobRepository;

public class QuartzJobExecutor implements Job {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Long jobId = context.getJobDetail().getJobDataMap().getLong("jobId");
        System.out.println("[Quartz Job] Executing Job ID: " + jobId);

        jobRepository.findById(jobId).ifPresent(job -> {
            job.setStatus("COMPLETED");
            job.setUpdatedAt(LocalDateTime.now());
            jobRepository.save(job);
        });
}

}
