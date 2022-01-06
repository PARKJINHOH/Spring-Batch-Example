package com.example.spring_batch_example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class TestJobConfig {

    @Bean
    public JobLauncherTestUtils memberJobLauncher() {
        return new JobLauncherTestUtils(){
            @Override
            public void setJob(@Qualifier("memberStatusJob") Job job) {
                super.setJob(job);
            }
        };
    }

}
