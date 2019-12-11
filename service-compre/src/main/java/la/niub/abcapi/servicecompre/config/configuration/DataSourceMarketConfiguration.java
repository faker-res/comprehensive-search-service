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
@MapperScan(basePackages = DataSourceMarketConfiguration.PACKAGE, sqlSessionFactoryRef = "marketSqlSessionFactory")
public class DataSourceMarketConfiguration {

    // 精确到 market 目录，以便跟其他数据源隔离
    static final String PACKAGE = "la.niub.abcapi.servicecompre.dao.market";
    static final String MAPPER_LOCATION = "classpath:mapper/market/*.xml";

    @Value("${spring.datasource.market.url}")
    private String url;

    @Value("${spring.datasource.market.username}")
    private String user;

    @Value("${spring.datasource.market.password}")
    private String password;

    @Value("${spring.datasource.market.driverClassName}")
    private String driverClass;

    @Value("${spring.datasource.market.timeoutSeconds}")
    private Integer timeout;

    @Bean(name = "marketDataSource")
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

    @Bean(name = "marketTransactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "marketSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("marketDataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}