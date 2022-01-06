package com.example.spring_batch_example.job;

import com.example.spring_batch_example.entity.MemberEntity;
import com.example.spring_batch_example.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
@AllArgsConstructor
public class MemberStatusJobConfig {

//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;

    private final MemberRepository memberRepository;

    @Bean
    public Job memberStatusJob(JobBuilderFactory jobBuilderFactory, Step changeStatusMember) {
        return jobBuilderFactory.get("memberStatusJob")
                .start(changeStatusMember)
                .build();
    }

    @Bean
    public Step changeStatusMember(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("changeStatusMember")
                .<MemberEntity, MemberEntity>chunk(6)
                .reader(memberReader())
                .processor(memberProcessor())
                .writer(memberWriter())
                .build();
    }


    @Bean
    @StepScope
    public ListItemReader<MemberEntity> memberReader() {
        List<MemberEntity> oldMember = memberRepository.findByStatusFalse();
        return new ListItemReader<>(oldMember);
    }

    public ItemProcessor<MemberEntity, MemberEntity> memberProcessor() {
        return MemberEntity::changeStatusTrue;
    }

    public ItemWriter<MemberEntity> memberWriter() {
        return ((List<? extends MemberEntity> memberList) ->
                memberRepository.saveAll(memberList));
    }


}
