###### SpringBatch
1. ItemReader：SpringBatch读取操作
2. ItemProcessor：SpringBatch执行过程
3. ItemWriter:SpringBatch写入操作
4. jobBuilderFactory：Job创建工厂
5. stepBuilderFactory：步骤创建工厂

6. 踩坑SpringBatch
    1. 在SpringBatch读取操作的类中，无法使用Autowired注解注入Service业务层，dao数据库操作层
    2. 在SpringBatch执行过程的类中，无法使用Autowired注解注入Service业务层，dao数据库操作层
    3. 在SpringBatch写入操作的类中，无法使用Autowired注解注入Service业务层，dao数据库操作层
    4. 解决办法：
        1. 创建一个新类，实现ApplicationContextAware接口
        2. 重写ApplicationContextAware接口中的方法，获取ApplicationContext
        3. 通过类名，ApplicationContext获取在SpringIoC中托管的类，达到自动注入的效果
        

7. jobLauncher:Job执行器：
    通过jobLauncher.run(job,new JobParametersBuilder().toJobParameters())方法开始执行job
    
8. SpringBatch整合Quartz定时框架 踩坑
    1. 配置SchedulerFactory时，需要在pom中引入C3P0的jar包，因为SchedulerFactory的实现类StdSchedulerFactory
需要手动设置配置文件，也就是需要先从配置文件中读取配置，然后赋值进properties对象里，并将properties对象以传参的形式
传到StdSchedulerFactory中；配置中需要设置数据源，而StdSchedulerFactory默认实现的是C3P0连接池
   2. Scheduler实际调度的是执行类，所以从数据库中读取配置时，需要将执行类的全限定名称存进去，取到之后，再用Class.forname
方法，利用反射找到该类进行调度
   3. 执行类需要直接或间接实现Job接口