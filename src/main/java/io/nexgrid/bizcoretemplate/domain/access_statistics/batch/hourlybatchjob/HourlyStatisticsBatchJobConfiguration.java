package io.nexgrid.bizcoretemplate.domain.access_statistics.batch.hourlybatchjob;

import io.nexgrid.bizcoretemplate.domain.access.repository.AccessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class HourlyStatisticsBatchJobConfiguration {

    private final PlatformTransactionManager transactionManager;
    private final JobRepository jobRepository;
    private final AccessRepository accessRepository;


    @Bean
    public Job hourlyStatisticsBatchJob(Step hourlyStatisticsStep) {
        return new JobBuilder("hourlyStatisticsBatchJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(hourlyStatisticsStep)
//                .listener()
                .build();
    }

    @Bean
    public Step hourlyStatisticsStep(ItemReader<List<String>> hourlyStatisticsItemReader,
                                     ItemWriter<Object> hourlyStatisticsItemWriter) {
        return new StepBuilder("hourlyStatisticsStep", jobRepository)
                .chunk(1, transactionManager)
                .reader(hourlyStatisticsItemReader)
                .writer(hourlyStatisticsItemWriter)
                .build();
    }

    @Bean
    public ItemReader<List<String>> hourlyStatisticsItemReader() {
        return new HourlyStatisticsBatchItemReader(accessRepository);
    }

    @Bean
    public ItemWriter<Object> hourlyStatisticsItemWriter() {
        return new ItemWriter() {
            @Override
            public void write(Chunk chunk) throws Exception {
            }
        };
    }

}
