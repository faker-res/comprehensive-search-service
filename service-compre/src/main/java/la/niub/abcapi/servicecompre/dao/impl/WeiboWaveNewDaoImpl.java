package la.niub.abcapi.servicecompre.dao.impl;

import la.niub.abcapi.servicecompre.config.configuration.MongoStockConfiguration;
import la.niub.abcapi.servicecompre.dao.IWeiboWaveNewDao;
import la.niub.abcapi.servicecompre.model.nosql.WeiboWaveNewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class WeiboWaveNewDaoImpl implements IWeiboWaveNewDao {

    private static final Logger logger = LogManager.getLogger(WeiboWaveNewDaoImpl.class);

    @Autowired
    @Qualifier(MongoStockConfiguration.NAME)
    MongoTemplate mongoTemplate;

    @Override
    public List<WeiboWaveNewModel> getByKey(String keyword, Integer limit) {
        try {
            Criteria criteria = new Criteria()
                    .and("keys").is(keyword);
            Query query = new Query(criteria)
                    .with(new Sort(new Sort.Order(Sort.Direction.DESC, "date")))
                    .limit(limit);
            logger.info("WeiboWaveNewDaoImpl.getByKey {}",query.toString());
            return mongoTemplate.find(query, WeiboWaveNewModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取微博指数失败",e.getMessage());
        }
        return null;
    }

    @Override
    public List<WeiboWaveNewModel> getByKeyAndDate(String keyword, Date startDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Criteria criteria = new Criteria()
                .and("keys").is(keyword).and("date").gte(sdf.format(startDate));
        Query query = new Query(criteria)
                .with(new Sort(new Sort.Order(Sort.Direction.DESC, "date")));
        logger.info("WeiboWaveNewDaoImpl.getByKey {}",query.toString());
        return mongoTemplate.find(query, WeiboWaveNewModel.class);
    }
}
