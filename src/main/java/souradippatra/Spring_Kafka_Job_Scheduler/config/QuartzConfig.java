package souradippatra.Spring_Kafka_Job_Scheduler.config;

import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
public class QuartzConfig {

    // Bean for injecting Spring dependencies into Quartz jobs
    @Bean
    public JobFactory jobFactory() {
        SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
        return jobFactory;
    }

    // Scheduler factory using the JobFactory
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setJobFactory(jobFactory);
        return schedulerFactory;
    }

    @Bean
    public Scheduler scheduler(@Autowired SchedulerFactoryBean factory) throws Exception {
        return factory.getScheduler();
    }
}
