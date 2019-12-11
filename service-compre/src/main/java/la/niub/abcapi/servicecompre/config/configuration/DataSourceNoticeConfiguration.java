package la.niub.abcapi.servicecompre.config.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = DataSourceNoticeConfiguration.PACKAGE, sqlSessionFactoryRef = "noticeSqlSessionFactory")
public class DataSourceNoticeConfiguration {

    // 精确到 notice 目录，以便跟其他数据源隔离
    static final String PACKAGE = "la.niub.abcapi.servicecompre.dao.notice";
    static final String MAPPER_LOCATION = "classpath:mapper/notice/*.xml";

    @Value("${spring.datasource.notice.url}")
    private String url;

    @Value("${spring.datasource.notice.username}")
    private String user;

    @Value("${spring.datasource.notice.password}")
    private String password;

    @Value("${spring.datasource.notice.driverClassName}")
    private String driverClass;

    @Value("${spring.datasource.notice.timeoutSeconds}")
    private Integer timeout;

    @Bean(name = "noticeDataSource")
    @Primary
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

    @Bean(name = "noticeTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "noticeSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("noticeDataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}