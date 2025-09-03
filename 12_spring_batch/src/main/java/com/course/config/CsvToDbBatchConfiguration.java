package com.course.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.course.entity.PersonEntity;
import com.course.model.Person;
import com.course.repository.PersonRepository;

@Configuration
public class CsvToDbBatchConfiguration {

	@Autowired
	private PersonRepository personRepository;
	
	// Reader
	
	@Bean
	ItemReader<Person> personReader() {
	    // 實作類別：平面文件
	    FlatFileItemReader<Person> itemReader = new FlatFileItemReader<Person>();
	    // 讀取資源
	    itemReader.setResource(new ClassPathResource("sample-data.csv"));
	    
	    // 設定編碼格式為UTF8，避免中文亂碼
	    itemReader.setEncoding("UTF-8");
	    
	    // 設定讀取器的名稱
	    itemReader.setName("csv-reader");

	    // 資料的第一行是標頭，不能讀入
	    itemReader.setLinesToSkip(1);
	    
	    // 設定 LineMapper，定義資料行當中的如何映射
	    itemReader.setLineMapper(getLineMapper());

	    return itemReader;
	}
	
	private LineMapper<Person> getLineMapper() {
	    // 針對輸入的每行資料進行處理

	    DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();

	    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
	    lineTokenizer.setDelimiter(",");
	    lineTokenizer.setNames("name", "gender", "city");

	    // 將欄位映射到指定的物件類型
	    BeanWrapperFieldSetMapper<Person> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
	    fieldSetMapper.setTargetType(Person.class);
	    
	    // 將 LineTokenizer 和 FieldSetMapper 組合進入 LineMapper
	    lineMapper.setLineTokenizer(lineTokenizer);
	    lineMapper.setFieldSetMapper(fieldSetMapper);
	    return lineMapper;
	}
	
	// Processor
	ItemProcessor<Person, PersonEntity> personProcessor(){
		ItemProcessor<Person, PersonEntity> processor = new ItemProcessor<>() {

			@Override
			public PersonEntity process(Person item) throws Exception {
		        // 業務邏輯處理
		        String gender = item.getGender();
		        if (!("男".equals(gender) || "女".equals(gender))) {
		            return null;
		        }
		        PersonEntity entity = new PersonEntity();
		        entity.setName(item.getName());
		        entity.setGender(item.getGender());
		        entity.setCity(item.getCity());
		        return entity;
			}
		};
		return processor;
	}
	
	// Writer
	@Bean
	ItemWriter<PersonEntity> personWriter() {

	    RepositoryItemWriter<PersonEntity> itemWriter = new RepositoryItemWriter<>();
	    itemWriter.setRepository(personRepository);
	    itemWriter.setMethodName("save");

	    return itemWriter;
	}
	
	// Step
	@Bean
	Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
	    return new StepBuilder("step-1", jobRepository)
	            .<Person, PersonEntity>chunk(2, transactionManager)
	            .reader(personReader())
	            .processor(personProcessor())
	            .writer(personWriter())
	            .build();
	}
	
	@Bean
	Step cleanupStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
	    return new StepBuilder("cleanupStep", jobRepository)
	        .tasklet((contribution, chunkContext) -> {
	            // 刪除舊檔案、初始化目錄
	            System.out.println("清理完成");
	            personRepository.deleteAllInBatch();
	            return RepeatStatus.FINISHED;
	        }, transactionManager)
	        .build();
	}
	
	// Job
//	@Bean
//	Job personJob(JobRepository jobRepository, Step step1) {
//	    return new JobBuilder("personJob", jobRepository)
//	            .start(step1)
//	            .build();
//	}
	
	@Bean
	Job personJob(JobRepository jobRepository, Step step1, Step cleanupStep) {
	    return new JobBuilder("personJob", jobRepository)
	            .start(cleanupStep)
	            .next(step1)
	            .build();
	}
	
}
