package com.example.springbatch.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * quartz配置读取entity
 *
 * @author panhc
 * @create 2017-04-20-15:20
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "org.quartz",ignoreUnknownFields = false)
@PropertySource(value = "classpath:/application-quartz.properties")
public class JobPropertiesEntity {

    private String instanceName;
    private String instanceId ;
    private Boolean skipUpdateCheck;
    private String storeClass ;
    private String driverDelegateClass ;
    private String tablePrefix ;
    private Boolean isClustered;
    private String clusterCheckinInterval;
    private String dataSource;
    private String driver ;
    private String url ;
    private String user;
    private String password ;
    private String threadClass ;
    private String threadCount;
    private Integer threadPriority ;
    private String threadsInheritContextClassLoaderOfInitializingThread ;
    private Integer maxConnections;
    private String validationQuery;
    private Integer idleConnectionValidationSeconds;
    private Boolean validateOnCheckout;
    private Integer discardIdleConnectionsSeconds;
}
