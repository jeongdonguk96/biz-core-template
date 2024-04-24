package io.nexgrid.bizcoretemplate.domain.access_statistics.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Slf4j
public class JobListener implements JobExecutionListener {

    long startTime;
    long endTime;
    long timeDiff;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("========== {} 동작 START ==========", jobExecution.getJobInstance().getJobName());
        startTime = System.currentTimeMillis();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        endTime = System.currentTimeMillis();
        log.info("========== {} 동작 END ==========", jobExecution.getJobInstance().getJobName());

        timeDiff = (endTime - startTime);
        log.info("========== {} 소요 시간 = {}ms ==========", jobExecution.getJobInstance().getJobName(), timeDiff);
    }
}
