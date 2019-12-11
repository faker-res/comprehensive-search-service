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
@MapperScan(basePackages = DataSourceMarketPriceConfiguration.PACKAGE, sqlSessionFactoryRef = "marketpriceSqlSessionFactory")
public class DataSourceMarketPriceConfiguration {
    // 精确到 market 目录，以便跟其他数据源隔离
    static final String PACKAGE = "la.niub.abcapi.servicecompre.dao.marketprice";
    static final String MAPPER_LOCATION = "classpath:mapper/marketprice/*.xml";

    @Value("${spring.datasource.marketprice.url}")
    private String url;

    @Value("${spring.datasource.marketprice.username}")
    private String user;

    @Value("${spring.datasource.marketprice.password}")
    private String password;

    @Value("${spring.datasource.marketprice.driverClassName}")
    private String driverClass;

    @Value("${spring.datasource.marketprice.timeoutSeconds}")
    private Integer timeout;

    @Bean(name = "marketpriceDataSource")
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

    @Bean(name = "marketpriceTransactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "marketpriceSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("marketpriceDataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
