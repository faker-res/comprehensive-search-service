package la.niub.abcapi.servicecompre.config.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = DataSourceReporterConfiguration.PACKAGE, sqlSessionFactoryRef = "reporterSqlSessionFactory")
public class DataSourceReporterConfiguration {

    // 精确到 reporter 目录，以便跟其他数据源隔离
    static final String PACKAGE = "la.niub.abcapi.servicecompre.dao.reporter";
    static final String MAPPER_LOCATION = "classpath:mapper/reporter/*.xml";

    @Value("${spring.datasource.reporter.url}")
    private String url;

    @Value("${spring.datasource.reporter.username}")
    private String user;

    @Value("${spring.datasource.reporter.password}")
    private String password;

    @Value("${spring.datasource.reporter.driverClassName}")
    private String driverClass;

    @Value("${spring.datasource.reporter.timeoutSeconds}")
    private Integer timeout;

    @Bean(name = "reporterDataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setLoginTimeout(timeout);
        dataSource.setQueryTimeout(timeout);
        return dataSource;
    }

    @Bean(name = "reporterTransactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "reporterSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("reporterDataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION));
        SqlSessionFactory result=sessionFactory.getObject();
        result.getConfiguration().setCallSettersOnNulls(true);
        return result;
    }
}