package la.niub.abcapi.servicecompre.dao.impl;

import la.niub.abcapi.servicecompre.dao.INoticeParsedDao;
import la.niub.abcapi.servicecompre.model.nosql.HbChartsModel;
import la.niub.abcapi.servicecompre.model.nosql.HbTablesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoticeParsedDaoImpl implements INoticeParsedDao {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<HbChartsModel> findChartsByFileId(Integer fileId) {
        Criteria criteria = new Criteria()
                .and("fileId").is(fileId)
                .and("status").nin(new Integer[]{4,3,-1});
        Query query = new Query(criteria)
                .with(new Sort(new Sort.Order(Sort.Direction.ASC, "_id")));
        return mongoTemplate.find(query, HbChartsModel.class);
    }

    @Override
    public List<HbTablesModel> findTablesByFileId(Integer fileId) {
        Criteria criteria = new Criteria()
                .and("fileId").is(fileId);
        Query query = new Query(criteria)
                .with(new Sort(new Sort.Order(Sort.Direction.ASC, "pageIndex"),
                        new Sort.Order(Sort.Direction.ASC, "x")));
        return mongoTemplate.find(query, HbTablesModel.class);
    }
}
