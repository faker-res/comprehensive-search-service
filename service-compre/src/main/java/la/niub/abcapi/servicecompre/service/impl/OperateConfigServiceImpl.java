package la.niub.abcapi.servicecompre.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import la.niub.abcapi.servicecompre.service.IOperateConfigService;
import la.niub.abcapi.servicecompre.dao.reporter.IOperateConfigDao;
import la.niub.abcapi.servicecompre.dao.notice.IOperateConfigFromNoticeDao;
import la.niub.abcapi.servicecompre.model.KeyWordQueryModel;
import la.niub.abcapi.servicecompre.model.CateCategoriesModel;
import la.niub.abcapi.servicecompre.model.FreqTablesDetailsModel;
import la.niub.abcapi.servicecompre.model.request.KeyWordRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OperateConfigServiceImpl implements IOperateConfigService {

    private static Integer SUBJECT_TAG = 30001;

    @Autowired(required = false)
    IOperateConfigDao operateConfigDao;

    @Autowired(required = false)
    IOperateConfigFromNoticeDao operateConfigNoticeDao;

    @Override
    public List<KeyWordQueryModel> queryWithKeyWord(KeyWordRequest request){
        Integer type = request.getType();
        Integer terminal = 1 - request.getTerminal();
        Integer lan = 1 - request.getLan();
        if (type.equals(1)) {
            return operateConfigDao.queryWithKeyWordpresetQuery(String.valueOf(request.getModule()),
                    terminal, lan, request.getLimit());
        }else if (type.equals(2)) {
            return operateConfigDao.queryWithKeyWordhotSearch(String.valueOf(request.getModule()),
                    terminal, lan, request.getLimit());
        }else{
            return operateConfigDao.queryWithKeyWordpromoteWords(String.valueOf(request.getModule()),
                    terminal, lan, request.getLimit());
        }
    }

    @Override
    public List<Object> getChartConfig(KeyWordRequest request){
        Integer type = request.getType();
        Integer terminal = 1 - request.getTerminal();
        Integer lan = 1 - request.getLan();
        if (type.equals(1)) {
            if (request.getModule().equals(SUBJECT_TAG)) {
                return operateConfigNoticeDao.getChartConfigFromSubjectTag(String.valueOf(request.getModule()),
                        terminal, lan, request.getLimit());
            } else {
                return operateConfigDao.getChartConfigFromOpTopic(String.valueOf(request.getModule()),
                        terminal, lan, request.getLimit());
            }
        } else{
            return operateConfigDao.getChartConfigFromSelectedIMG(String.valueOf(request.getModule()),
                    terminal, lan, request.getLimit());
        }
    }

    @Override
    public Object getChartConfigone(String id){
            return operateConfigDao.getChartConfigFromSelectedIMGONE(id);
    }

    @Override
    public Object getConfTables(Integer type, Integer limit) {
        if (type == null) { type = 0; }
        if (limit == null) { limit = 9; }
        switch(type){
            case 1 :
                return getFreqTablesDetails(limit);
            case 2 :
                return getCateCategories(limit);
            case 3 :
                return getOtherCategories(limit);
            default :
                HashMap res = new HashMap();
                res.put("use", getFreqTablesDetails(limit));
                res.put("cate", getCateCategories(limit));
                res.put("other", getOtherCategories(limit));
                return res;
        }
    }

    @Override
    public List<FreqTablesDetailsModel> getTablesByKeyword(String keyword, Integer limit) {
        if (keyword == null){ keyword = ""; };
        if (limit == null){ limit = 1; };
        return operateConfigDao.getTablesByKeyword(keyword, limit);
    }

    private List<Object> getFreqTablesDetails(Integer limit){
        List<String> res = operateConfigDao.getTabIdsFromFreqSearchTables(limit);
        return operateConfigDao.getTablesDetails(res);
    }

    private List<Object> getCateCategories(Integer limit){
        List<CateCategoriesModel> categories = operateConfigDao.getCateCategories(limit);
        List<Object> res = new ArrayList();
        for(CateCategoriesModel c: categories){
            String category_Code = c.getCategory_Code();
            List<Object> tables = operateConfigDao.getTablesFromCategoryCode(category_Code);
            c.setItem(tables);
            c.setCount(tables.size());
            res.add((Object)c);
        }
        return res;
    }

    private List<Object> getOtherCategories(Integer limit){  //TODO 暂时临时代替一下
        List<Object> res = new ArrayList();
        CateCategoriesModel c = new CateCategoriesModel();
        c.setCategory_Name("其他类型".toString());
        c.setCategory_icon("http://abc-market-web-image.oss-cn-hangzhou.aliyuncs.com/others/others.png");
        c.setCount(0);
        c.setItem(new ArrayList<>());
        res.add((Object)c);
        return res;
    }
}
