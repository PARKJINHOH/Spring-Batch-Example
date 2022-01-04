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
public class SimpleJobBatch {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleJob() {
        Job simpleJob = jobBuilderFactory.get("Batch Name : Simple Job")
                .start(simpleStep1())
                .next(simpleStep2())
                .build();
        return simpleJob;
    }

    @Bean
    public Step simpleStep1() {
        TaskletStep simpleStep1 = stepBuilderFactory.get("Step Name : Simple Step1")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(">>>>>>>> This is Simple Step1");
                    return RepeatStatus.FINISHED;
                }).build();

         return simpleStep1;
    }

    @Bean
    public Step simpleStep2() {
        TaskletStep simpleStep1 = stepBuilderFactory.get("Step Name : Simple Step2")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(">>>>>>>> This is Simple Step2");
                    return RepeatStatus.FINISHED;
                }).build();

        return simpleStep1;
    }

}
