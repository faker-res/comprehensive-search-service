package la.niub.abcapi.servicecompre.dao.impl;

import la.niub.abcapi.servicecompre.dao.IJuchaoTextDao;
import la.niub.abcapi.servicecompre.model.nosql.JuchaoTextModel;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JuchaoTextDaoImpl implements IJuchaoTextDao {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<JuchaoTextModel> getByFileIds(List<ObjectId> fileIds) {
        Criteria criteria = new Criteria().and("fileId").in(fileIds);
        Query query = new Query(criteria).with(new Sort(new Sort.Order(Sort.Direction.ASC, "_id")));
        return mongoTemplate.find(query, JuchaoTextModel.class);
    }
}
