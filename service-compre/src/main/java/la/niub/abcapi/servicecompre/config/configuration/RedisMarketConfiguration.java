package la.niub.abcapi.servicecompre.config.configuration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisMarketConfiguration {

    @Value("${spring.redis.market.host}")
    String host;

    @Value("${spring.redis.market.port}")
    Integer port;

    @Value("${spring.redis.market.password}")
    String password;

    @Value("${spring.redis.market.database}")
    Integer database;

    @Value("${spring.redis.market.timeout}")
    Integer timeout;

    public final static String NAME = "marketRedis";

    @Bean(name = NAME)
    public RedisTemplate redisTemplate(@Qualifier("marketConnectionFactory") RedisConnectionFactory connectionFactory) {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(connectionFactory);
        template.afterPropertiesSet();

        //禁止序列化
        RedisSerializer stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(stringSerializer);

        return template;
    }

    @Bean(name="marketConnectionFactory")
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
        // 初始化连接pool
//        jedis.afterPropertiesSet();
        RedisConnectionFactory factory = jedis;

        return factory;
    }
}
