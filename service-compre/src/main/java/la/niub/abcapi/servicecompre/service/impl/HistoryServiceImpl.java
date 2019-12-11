package la.niub.abcapi.servicecompre.service.impl;

import la.niub.abcapi.servicecompre.dao.reporter.IQueryHistoryDao;
import la.niub.abcapi.servicecompre.model.QueryHistoryModel;
import la.niub.abcapi.servicecompre.model.request.HistoryRequest;
import la.niub.abcapi.servicecompre.service.IHistroyService;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
/**
 * 搜索历史
 *
 * @author amen
 */
public class HistoryServiceImpl implements IHistroyService {

    private static final Logger logger = LogManager.getLogger(HistoryServiceImpl.class);

    @Autowired
    IQueryHistoryDao iQueryHistoryDao;

    /**
     * 添加搜索历史
     * @param userId
     * @param input_from
     * @param keyword
     * @param page
     * @return
     */
    @Override
    public Boolean recordSearchHistory(String userId, String keyword, String input_from, String page) {
        if( userId == null || userId.isEmpty() || input_from == null || input_from.isEmpty() || keyword == null
            || keyword.isEmpty() || page == null || page.isEmpty()){
            return false;
        }
        String pages[] = new String[] { "comprehensive_search", "comprehensive_news_search",
                                        "comprehensive_notice_search", "comprehensive_report_search",
                                        "comprehensive_chart_search", "comprehensive_table_search",
                                        "comprehensive_edb_search", "edb_result"};
        for (String s : pages) {
            if (s.equals(page)){
                int flag = iQueryHistoryDao.insertLog(new QueryHistoryModel(userId, keyword, input_from, page));
                return  flag > 0 ;
            }
        }
        return false;
    }


    /**
     * 删除历史记录
     * @param userId
     * @param ids
     * @return
     */
    @Override
    public Boolean delRecords(String userId, String ids) {
        List<Long> idList = new ArrayList<>();
        if(ids != null && !ids.isEmpty()) {
            List<String> idStrList = Arrays.asList(ids.split(","));
            for (String id : idStrList) {
                idList.add(Long.valueOf(id));
            }
        }
        int flag = iQueryHistoryDao.deleteLog(userId,idList);
        return  flag > 0 ;
    }

    /**
     * 获取历史记录
     * @param request
     * @return
     */
    @Override
    public List<QueryHistoryModel> getRecords(HistoryRequest request) {
        List<String> page = new ArrayList<>();
        switch (request.getType()) {
            case 1 : // 综合
                page.add("comprehensive_search");
                break;
            case 2 : // 资讯
                page.add("comprehensive_news_search");
                break;
            case 3 : // 公告
                page.add("comprehensive_notice_search");
                break;
            case 4 : // 研报
                page.add("comprehensive_report_search");
                break;
            case 5 : // 数据图
                page.add("comprehensive_chart_search");
                break;
            case 6 : // 数据表
                page.add("comprehensive_table_search");
                break;
            case 7 : // 数据
                page.add("comprehensive_edb_search");
                page.add("edb_result");
                break;
        }
        String keyword = (request.getKeyword() != null && request.getKeyword().isEmpty()) ? null : request.getKeyword();

        // 去重过滤一分钟内同类型重复keyword
        List<QueryHistoryModel> historyModelList = iQueryHistoryDao.selectByPages(request.getUserId(),page,request.getLimit(),
                request.getLastId(),keyword);
        Map<String,String> timeMap = new HashMap<>();
        List<Long> repeatList = new ArrayList<>();

        for (int i = 0; i < historyModelList.size(); i++) {
            QueryHistoryModel item = historyModelList.get(i);
            String timeKey = item.getKeyword()+item.getPage();
            String timeVal = FastDateFormat.getInstance("yyyy-MM-dd HH:mm").format(item.getLastmodify());
//            System.out.println(timeVal);
            if( timeMap.containsKey(timeKey) && timeMap.get(timeKey).equals(timeVal) ){
                item.setStatus(1);
                repeatList.add(item.getId());
            }else{
                timeMap.put(timeKey, timeVal);
            }
        }

        if(repeatList.size() > 0 ){
            iQueryHistoryDao.deleteLog(request.getUserId(),repeatList);
        }
        return historyModelList;
    }

}
