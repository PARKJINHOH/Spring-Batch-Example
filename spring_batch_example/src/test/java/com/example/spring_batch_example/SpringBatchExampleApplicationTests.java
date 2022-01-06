package com.example.spring_batch_example;

import com.example.spring_batch_example.entity.MemberEntity;
import com.example.spring_batch_example.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBatchTest
public class SpringBatchExampleApplicationTests {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    public void 미납회원_생성() throws Exception {
        // 유저 생성
        generateMember();
        assertEquals(10, memberRepository.findAll().size());
        assertEquals(10, memberRepository.findByStatusFalse().size());

        // 배치 실행
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        // 실행 후 테스트 결과
        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
        assertEquals(0, memberRepository.findByStatusFalse().size());
        assertEquals(10, memberRepository.findByStatusTrue().size());

    }


    protected void generateMember() {
        for (int i = 0; i < 10; i++) {
            String name = "user_" + (i + 1);
            MemberEntity member = new MemberEntity(name);
            this.memberRepository.save(member);
        }
    }


}
