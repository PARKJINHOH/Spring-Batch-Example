package com.example.spring_batch_example.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TestJobBatch {


    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job testJob() {
        Job simpleJob = jobBuilderFactory.get("Batch Name : Test Job")
                .start(testStep1())
                .next(testStep2())
                .build();
        return simpleJob;
    }

    @Bean
    public Step testStep1() {
        TaskletStep simpleStep1 = stepBuilderFactory.get("Step Name : Test Step1")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(">>>>>>>> This is Test Step1");
                    return RepeatStatus.FINISHED;
                }).build();

         return simpleStep1;
    }

    @Bean
    public Step testStep2() {
        TaskletStep simpleStep1 = stepBuilderFactory.get("Step Name : Test Step2")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(">>>>>>>> This is Test Step2");
                    return RepeatStatus.FINISHED;
                }).build();

        return simpleStep1;
    }

}
