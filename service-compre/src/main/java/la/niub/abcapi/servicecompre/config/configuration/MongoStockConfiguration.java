package la.niub.abcapi.servicecompre.config.configuration;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class MongoStockConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.data.mongodb.stock.uri}")
    private String uri;

    @Value("${spring.data.mongodb.stock.timeout}")
    private Integer timeout;

    public final static String NAME = "stockMongo";

    @Bean(name = NAME)
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

    private MongoDbFactory mongoDbFactory() throws Exception {
        MongoClientOptions.Builder options = MongoClientOptions.builder()
                .applicationName(applicationName)
                .connectTimeout(timeout/2)
                .socketTimeout(timeout);
        MongoClientURI mongoClientURI = new MongoClientURI(uri,options);
        return new SimpleMongoDbFactory(mongoClientURI);
    }
}
