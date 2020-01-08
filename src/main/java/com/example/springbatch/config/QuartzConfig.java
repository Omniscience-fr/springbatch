package com.example.springbatch.config;

import com.example.springbatch.pojo.JobEntity;
import com.example.springbatch.pojo.JobPropertiesEntity;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;


/**
 * @Author fr
 * @Date 2020-01-06 13:43
 */
@Configuration
@EnableScheduling
@EnableConfigurationProperties(JobPropertiesEntity.class)
public class QuartzConfig {


//    private CronTriggerFactoryBean createTrigger(JobDetail jobDetail, String expression) {
//
//        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
//        cronTriggerFactoryBean.setJobDetail(jobDetail);
//        cronTriggerFactoryBean.setCronExpression(expression);
//        return cronTriggerFactoryBean;
//    }
//
//    private JobDetailFactoryBean createJobDetail(Class clazz) {
//        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
//        jobDetailFactoryBean.setJobClass(clazz);
//        return jobDetailFactoryBean;
//    }
//
//    @Bean
//    public JobDetailFactoryBean jobDetail() {
//        return createJobDetail(SchedulerJob.class);
//    }
//
//    @Bean
//    public CronTriggerFactoryBean cronTrigger() {
//        return createTrigger(jobDetail().getObject(), "*/10 * * * * ? *");
//    }
//
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean() {
//        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//        schedulerFactoryBean.setAutoStartup(true);
//        schedulerFactoryBean.setTriggers(cronTrigger().getObject());
//        return schedulerFactoryBean;
//    }


//    /**
//     * 配置JobFctory
//     * @param appletContext
//     * @return
//     */
//    @Bean
//    public JobFactory jobFactory(ApplicationContext appletContext){
//        AutowiringSpringBeanJobFactory autowiringSpringBeanJobFactory = new AutowiringSpringBeanJobFactory();
//        autowiringSpringBeanJobFactory.setApplicationContext(appletContext);
//        return autowiringSpringBeanJobFactory;
//    }

//    /**
//     * SchedulerFactoryBean这个类的真正作用提供了对org.quartz.Scheduler的创建与配置，并且会管理它的生命周期与Spring同步。
//     * org.quartz.Scheduler: 调度器。所有的调度都是由它控制。
//     * @param dataSource 为SchedulerFactory配置数据源
//     * @param jobFactory 为SchedulerFactory配置JobFactory
//     * @return
//     */
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) throws IOException {
//        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//        schedulerFactoryBean.setDataSource(dataSource);
//        schedulerFactoryBean.setQuartzProperties(quartzProperties());
//        return schedulerFactoryBean;
//    }
//
//    /**
//     * 从quartz.properties文件中读取Quartz配置属性
//     * @return
//     * @throws IOException
//     */
//    @Bean
//    public Properties quartzProperties() throws IOException{
//        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
//        propertiesFactoryBean.afterPropertiesSet();
//        return propertiesFactoryBean.getObject();
//    }

//
//    /**
//     * 配置JobFactory,为quartz作业添加自动连接支持
//     */
//    public final class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {
//        private transient AutowireCapableBeanFactory capableBeanFactory;
//
//        @Override
//        public void setApplicationContext(ApplicationContext applicationContext) {
//            capableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
//        }
//
//        @Override
//        protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
//            final Object jobInstance = super.createJobInstance(bundle);
//            capableBeanFactory.autowireBean(jobInstance);
//            return jobInstance;
//        }
//    }




    @Autowired
    private JobPropertiesEntity jobPropertiesEntity;

    @Bean
    public Scheduler scheduler() throws IOException, SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory(quartzProperties());
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        return scheduler;
    }

    /**
     * 加载quartz数据源配置
     * @return
     * @throws IOException
     */
    @Bean
    public Properties quartzProperties() throws IOException {
        Properties prop = new Properties();
       prop.put("quartz.scheduler.instanceName", jobPropertiesEntity.getInstanceName());
        prop.put("org.quartz.scheduler.instanceId", jobPropertiesEntity.getInstanceId());
        prop.put("org.quartz.scheduler.skipUpdateCheck", jobPropertiesEntity.getSkipUpdateCheck());
        prop.put("org.quartz.jobStore.class", jobPropertiesEntity.getStoreClass());
        prop.put("org.quartz.jobStore.driverDelegateClass", jobPropertiesEntity.getDriverDelegateClass());
        prop.put("org.quartz.jobStore.dataSource", jobPropertiesEntity.getDataSource());
        prop.put("org.quartz.jobStore.tablePrefix", jobPropertiesEntity.getTablePrefix());
        prop.put("org.quartz.jobStore.isClustered", jobPropertiesEntity.getIsClustered());
        prop.put("org.quartz.jobStore.clusterCheckinInterval", jobPropertiesEntity.getClusterCheckinInterval());
        prop.put("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread",
                jobPropertiesEntity.getThreadsInheritContextClassLoaderOfInitializingThread());
        prop.put("org.quartz.threadPool.class", jobPropertiesEntity.getThreadClass());
        prop.put("org.quartz.threadPool.threadCount", jobPropertiesEntity.getThreadCount());
        prop.put("org.quartz.dataSource.account.driver", jobPropertiesEntity.getDriver());
        prop.put("org.quartz.dataSource.account.URL", jobPropertiesEntity.getUrl());
        prop.put("org.quartz.dataSource.account.user", jobPropertiesEntity.getUser());
        prop.put("org.quartz.dataSource.account.password",jobPropertiesEntity.getPassword());
        prop.put("org.quartz.dataSource.account.maxConnections", jobPropertiesEntity.getMaxConnections());
        prop.put("org.quartz.dataSource.account.validationQuery", jobPropertiesEntity.getValidationQuery());
        prop.put("org.quartz.dataSource.account.idleConnectionValidationSeconds",
                jobPropertiesEntity.getIdleConnectionValidationSeconds());
        prop.put("org.quartz.dataSource.account.validateOnCheckout", jobPropertiesEntity.getValidateOnCheckout());
//        prop.put("org.quartz.dataSource.account.discardIdleConnectionsSeconds", jobPropertiesEntity.getDiscardIdleConnectionsSeconds());

        return prop;
    }



}

