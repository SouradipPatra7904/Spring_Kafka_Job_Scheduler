package souradippatra.Spring_Kafka_Job_Scheduler.service;

import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.stereotype.Service;
import souradippatra.Spring_Kafka_Job_Scheduler.entity.JobEntity;

import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final Scheduler scheduler;

    public void scheduleJob(JobEntity jobEntity, String cronExpression) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(QuartzJobExecutor.class)
                .withIdentity("job-" + jobEntity.getId())
                .usingJobData("jobId", jobEntity.getId())
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger-" + jobEntity.getId())
                .startAt(Date.from(jobEntity.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant()))
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }
}
