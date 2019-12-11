package la.niub.abcapi.servicecompre.config.configuration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisDefaultConfiguration {

    @Value("${spring.redis.default.host}")
    String host;

    @Value("${spring.redis.default.port}")
    Integer port;

    @Value("${spring.redis.default.password}")
    String password;

    @Value("${spring.redis.default.database}")
    Integer database;

    @Value("${spring.redis.default.timeout}")
    Integer timeout;

    public final static String NAME = "defaultRedis";

    @Primary
    @Bean(name = NAME)
    public RedisTemplate redisTemplate(@Qualifier("defaultConnectionFactory") RedisConnectionFactory connectionFactory) {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(connectionFactory);
        template.afterPropertiesSet();

        //禁止序列化
        RedisSerializer stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
//        template.setValueSerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
//        template.setHashValueSerializer(stringSerializer);

        return template;
    }

    @Primary
    @Bean(name="defaultConnectionFactory")
    public RedisConnectionFactory connectionFactory() {
        JedisConnectionFactory jedis = new JedisConnectionFactory();
        jedis.setHostName(host);
        jedis.setPort(port);
        if (StringUtils.isNotEmpty(password)) {
            jedis.setPassword(password);
        }
        if (database != null) {
            jedis.setDatabase(database);
        }
        jedis.setTimeout(timeout);
//        jedis.setPoolConfig(poolCofig(maxIdle, maxTotal, maxWaitMillis, testOnBorrow));
        //初始化连接pool
//        jedis.afterPropertiesSet();
        RedisConnectionFactory factory = jedis;

        return factory;
    }

//    private JedisPoolConfig poolCofig(int maxIdle, int maxTotal, long maxWaitMillis, boolean testOnBorrow) {
//        JedisPoolConfig poolCofig = new JedisPoolConfig();
//        poolCofig.setMaxIdle(maxIdle);
//        poolCofig.setMaxTotal(maxTotal);
//        poolCofig.setMaxWaitMillis(maxWaitMillis);
//        poolCofig.setTestOnBorrow(testOnBorrow);
//        return poolCofig;
//    }
}
