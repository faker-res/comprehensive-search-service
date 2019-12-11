package la.niub.abcapi.servicecompre.config.configuration;

import la.niub.abcapi.servicecompre.config.CommonConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class NosqlConfiguration {

    @Bean
    public RedisCacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager= new RedisCacheManager(redisTemplate);
        cacheManager.setUsePrefix(true);
        cacheManager.setDefaultExpiration(CommonConfig.CACHE_EXPIRATION);
//        Map<String,Long> expiresMap=new HashMap<>();
//        expiresMap.put("Product",5L);
//        cacheManager.setExpires(expiresMap);
        return cacheManager;
    }

}
