# Spring_Kafka_Job_Scheduler

**A Worker Queue & Job Scheduler demonstration project built with Spring Boot, Apache Kafka, and Quartz Scheduler.**

---

## 🚀 Project Vision

This project demonstrates **job orchestration and worker queue patterns** in a Spring Boot application using **Apache Kafka** for messaging and **Quartz** for scheduling.

It combines:

* **Distributed Job Queue** (via Kafka topics & consumer groups)
* **Scheduled Jobs** (via Quartz Scheduler, cron-based triggers)
* **Relational Storage** (via PostgreSQL)
* **REST API** (Spring Boot + Spring Data JPA)

This makes it a compact showcase of **enterprise-grade job scheduling and orchestration patterns**.

---

## ⚙️ Tech Stack

* **Java 21**
* **Spring Boot 3.5.6**
* **Apache Kafka (KRaft mode)**
* **Quartz Scheduler**
* **PostgreSQL**
* **Maven (Jar packaging)**

---

## Running the Application

1. **Configure PostgreSQL in `application.properties`:**

```properties
server.port=9595
spring.datasource.url=jdbc:postgresql://localhost:5432/job_scheduler_db
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

2. **Kafka Setup (KRaft mode):**

   * Ensure Kafka broker is running on `localhost:9092`.
   * Topic is configurable via `application.properties`:

```properties
job.scheduler.kafka.topic=spring_jobs
```

3. **Build & Run:**

```bash
mvn clean package
java -jar target/Spring_Kafka_Job_Scheduler-0.0.1-SNAPSHOT.jar
```

4. **Server runs on:**

```
http://localhost:8080
```

---

## 🎯 Key REST Endpoints

| Endpoint                  | Method | Description                                                                  |
| ------------------------- | ------ | ---------------------------------------------------------------------------- |
| `/api/jobs/enqueue`       | POST   | Enqueue a job (Kafka + Quartz). Optional cron expression for recurring jobs. |
| `/api/jobs`               | GET    | List all jobs with status.                                                   |
| `/api/jobs/{id}`          | GET    | Fetch a job by ID.                                                           |
| `/api/jobs/{id}/complete` | PUT    | Manually mark a job as completed (for demo/testing).                         |

**Example Payload for POST /enqueue:**

```json
{
  "jobName": "SampleJob",
  "jobData": "Process this payload",
  "cronExpression": "0/30 * * * * ?"
}
```

---

## 🎯 Key Highlights

* Demonstrates **Kafka integration** with Spring Boot
* Shows **Quartz scheduling** and cron-based recurring jobs
* Tracks job lifecycle: **PENDING → RUNNING → COMPLETED**
* REST API for **monitoring & managing jobs**
* Enterprise-ready **clean architecture** with modular packages (`config, controller, service, repository, entity, dto`)

---

## 🏗️ Future Enhancements

* Add **job retry mechanism** and dead-letter handling
* Add **metrics & observability** (Micrometer + Prometheus + Grafana)
* Add **web dashboard** for live job monitoring
* Support **multiple Kafka topics and worker pools** for advanced orchestration
