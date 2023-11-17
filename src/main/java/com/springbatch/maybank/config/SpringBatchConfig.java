package com.springbatch.maybank.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.springbatch.maybank.entity.TransactionData;

@Component
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public ItemReader<TransactionData> reader() {
        FlatFileItemReader<TransactionData> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("dataSource.txt"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<TransactionData>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
            	setDelimiter("|");
            	setStrict(false);
                setNames(new String[] {"accountNumber", "trxAmount", "description", "trxDate", "trxTime", "customerId"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<TransactionData>() {{
                setTargetType(TransactionData.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ItemProcessor<TransactionData, TransactionData> processor() {
        // Add any processing logic here if needed
        return transaction -> transaction;
    }

    @Bean
    public ItemWriter<TransactionData> writer() {
        JpaItemWriter<TransactionData> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    @Bean
    public Step step(ItemReader<TransactionData> reader, ItemProcessor<TransactionData, TransactionData> processor, ItemWriter<TransactionData> writer) {
        return stepBuilderFactory.get("step")
                .<TransactionData, TransactionData>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job importJob(Step step) {
        return jobBuilderFactory.get("importJob")
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()
                .build();
    }

}
