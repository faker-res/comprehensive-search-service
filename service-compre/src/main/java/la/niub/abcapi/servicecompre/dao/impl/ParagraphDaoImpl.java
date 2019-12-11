package la.niub.abcapi.servicecompre.dao.impl;

import la.niub.abcapi.servicecompre.dao.IParagraphDao;
import la.niub.abcapi.servicecompre.model.nosql.BackendChartsModel;
import la.niub.abcapi.servicecompre.model.nosql.HbChartsModel;
import la.niub.abcapi.servicecompre.model.nosql.HbTextModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParagraphDaoImpl implements IParagraphDao {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<HbTextModel> getParagraphs(List<Integer> ids) {
        Query query = new Query(Criteria.where("_id").in(ids));
        List<HbTextModel> model = mongoTemplate.find(query,HbTextModel.class);
        return model;
    }

    @Override
    public List<HbChartsModel> getRecordsFromHB_Charts(Integer report_id) {
        List<Integer> bad_status = new ArrayList();
        bad_status.add(4);
        bad_status.add(3);
        bad_status.add(-1);

        Criteria criteria = new Criteria();
        criteria.and("fileId").is(report_id);
        criteria.and("status").nin(bad_status);
        Query query = new Query(criteria);
        query.with(new Sort(new Sort.Order(Sort.Direction.ASC, "_id")));

        List<HbChartsModel> model = mongoTemplate.find(query, HbChartsModel.class);
        return model;
    }

    @Override
    public List<BackendChartsModel> getRecordsFromBackend_Charts(Integer report_id) {
        List<Integer> bad_status = new ArrayList();
        bad_status.add(4);
        bad_status.add(3);
        bad_status.add(-1);

        Criteria criteria = new Criteria();
        criteria.and("fileId").is(report_id);
        criteria.and("status").nin(bad_status);
        Query query = new Query(criteria);
        query.with(new Sort(new Sort.Order(Sort.Direction.ASC, "_id")));

        List<BackendChartsModel> model = mongoTemplate.find(query, BackendChartsModel.class);
        return model;
    }
}
