package souradippatra.Spring_Kafka_Job_Scheduler.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import souradippatra.Spring_Kafka_Job_Scheduler.dto.JobRequestDTO;
import souradippatra.Spring_Kafka_Job_Scheduler.dto.JobResponseDTO;
import souradippatra.Spring_Kafka_Job_Scheduler.entity.JobEntity;
import souradippatra.Spring_Kafka_Job_Scheduler.exception.JobNotFoundException;
import souradippatra.Spring_Kafka_Job_Scheduler.mapper.JobMapper;
import souradippatra.Spring_Kafka_Job_Scheduler.repository.JobRepository;
import souradippatra.Spring_Kafka_Job_Scheduler.service.JobProducerService;
import souradippatra.Spring_Kafka_Job_Scheduler.service.SchedulerService;

import org.quartz.SchedulerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final JobProducerService producerService;
    private final SchedulerService schedulerService;

    @PostMapping("/enqueue")
    public ResponseEntity<JobResponseDTO> enqueueJob(@RequestBody JobRequestDTO dto) throws SchedulerException {
        JobEntity jobEntity = new JobEntity();
        jobEntity.setJobName(dto.getJobName());
        jobEntity.setJobData(dto.getJobData());
        jobEntity.setStatus("PENDING");
        jobEntity.setCreatedAt(LocalDateTime.now());
        jobEntity.setUpdatedAt(LocalDateTime.now());

        jobRepository.save(jobEntity);

        // Send to Kafka
        producerService.sendJob(dto.getJobData());

        // Schedule if cron provided
        if(dto.getCronExpression() != null && !dto.getCronExpression().isEmpty()) {
            schedulerService.scheduleJob(jobEntity, dto.getCronExpression());
        }

        return new ResponseEntity<>(jobMapper.toDTO(jobEntity), HttpStatus.CREATED);
    }

    @GetMapping
    public List<JobResponseDTO> getAllJobs() {
        return jobRepository.findAll().stream()
                .map(jobMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public JobResponseDTO getJobById(@PathVariable Long id) {
        JobEntity job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job not found with ID: " + id));
        return jobMapper.toDTO(job);
    }

    @PutMapping("/{id}/complete")
    public JobResponseDTO completeJob(@PathVariable Long id) {
        JobEntity job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job not found with ID: " + id));
        job.setStatus("COMPLETED");
        job.setUpdatedAt(LocalDateTime.now());
        jobRepository.save(job);
        return jobMapper.toDTO(job);
    }

}
