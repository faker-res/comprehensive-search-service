package la.niub.abcapi.servicecompre.dao.impl;

import la.niub.abcapi.servicecompre.dao.IFinanceJuchaoItemDao;
import la.niub.abcapi.servicecompre.model.nosql.FinanceJuchaoItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FinanceJuchaoItemDaoImpl implements IFinanceJuchaoItemDao {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<FinanceJuchaoItemModel> getBySrcIds(List<String> srcIds) {
        Criteria criteria = new Criteria().and("src_id").in(srcIds);
        Query query = new Query(criteria).with(new Sort(new Sort.Order(Sort.Direction.ASC, "_id")));
        return mongoTemplate.find(query, FinanceJuchaoItemModel.class);
    }
}
