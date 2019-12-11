package la.niub.abcapi.servicecompre.service.impl;

import la.niub.abcapi.servicecompre.dao.reporter.IAllSearchTablesDao;
import la.niub.abcapi.servicecompre.dao.reporter.IAllTableGroupsDao;
import la.niub.abcapi.servicecompre.dao.reporter.IFreqSearchTablesDao;
import la.niub.abcapi.servicecompre.model.AllSearchTablesModel;
import la.niub.abcapi.servicecompre.model.AllTableGroupsModel;
import la.niub.abcapi.servicecompre.model.FreqSearchTablesModel;
import la.niub.abcapi.servicecompre.model.bo.ComprehensiveChartUseBO;
import la.niub.abcapi.servicecompre.model.bo.TableConfigBO;
import la.niub.abcapi.servicecompre.service.IKeywordConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeywordConfigServiceImpl implements IKeywordConfigService {

    @Autowired
    IFreqSearchTablesDao freqSearchTablesDao;

    @Autowired
    IAllSearchTablesDao allSearchTablesDao;

    @Autowired
    IAllTableGroupsDao allTableGroupsDao;

    @Override
    public ComprehensiveChartUseBO getAllTableConfig(Integer type) {
        ComprehensiveChartUseBO comprehensiveChartUseBO = new ComprehensiveChartUseBO();
        if (type == 0){
            comprehensiveChartUseBO.setUse(buildTableConfig(1,9));
            comprehensiveChartUseBO.setCate(buildTableConfig(2,9));
            comprehensiveChartUseBO.setOther(buildTableConfig(3,9));
        }else if (type == 1){
            comprehensiveChartUseBO.setUse(buildTableConfig(1,9));
        }else if (type == 2){
            comprehensiveChartUseBO.setCate(buildTableConfig(2,9));
        }else if (type == 3){
            comprehensiveChartUseBO.setOther(buildTableConfig(3,9));
        }
        return comprehensiveChartUseBO;
    }

    private List<TableConfigBO> buildTableConfig(Integer type,Integer limit){
        List<TableConfigBO> list = new ArrayList<>();
        switch (type){
            case 1:
                List<FreqSearchTablesModel> freqSearchTablesModelList = freqSearchTablesDao.selectLimit(limit);
                if (ObjectUtils.isEmpty(freqSearchTablesModelList)){
                    return list;
                }
                List<String> tabIds = new ArrayList<>();
                for (FreqSearchTablesModel item : freqSearchTablesModelList){
                    tabIds.add(item.getTab_ID());
                }

                List<AllSearchTablesModel> allSearchTablesModelList = allSearchTablesDao.selectByTabIds(tabIds);
                if (ObjectUtils.isEmpty(allSearchTablesModelList)){
                    return list;
                }
                Map<String,AllSearchTablesModel> allSearchTablesModelMap = new HashMap<>();
                for (AllSearchTablesModel item : allSearchTablesModelList){
                    allSearchTablesModelMap.put(item.getTab_ID(),item);
                }

                for (String tabId : tabIds){
                    if (allSearchTablesModelMap.containsKey(tabId)){
                        AllSearchTablesModel model= allSearchTablesModelMap.get(tabId);
                        TableConfigBO tableConfigBO = new TableConfigBO();

                        tableConfigBO.setTab_id(model.getTab_ID());
                        tableConfigBO.setIcon(model.getTab_ICON_Freq());
                        tableConfigBO.setName(model.getTab_Name());
                        tableConfigBO.setShortName(model.getTab_ShortName());
                        tableConfigBO.setEngName(model.getTab_EngName());

                        list.add(tableConfigBO);
                    }
                }
                return list;
            case 2:
                List<AllTableGroupsModel> allTableGroupsModelList = allTableGroupsDao.selectDisplay();
                if (ObjectUtils.isEmpty(allTableGroupsModelList)){
                    return list;
                }
                List<String> cateCodes = new ArrayList<>();
                for (AllTableGroupsModel item : allTableGroupsModelList){
                    cateCodes.add(item.getCategory_Code());
                }

                List<AllSearchTablesModel> allSearchTablesModels = allSearchTablesDao.selectByCateCodes(cateCodes);
                if (ObjectUtils.isEmpty(allSearchTablesModels)){
                    return list;
                }
                Map<String,List<AllSearchTablesModel>> modelMap = new HashMap<>();

                for (AllSearchTablesModel item : allSearchTablesModels){
                    String cateCode = item.getCategory_Code();
                    List<AllSearchTablesModel> modelList = modelMap.containsKey(cateCode)
                            ? modelMap.get(cateCode) : new ArrayList<>();
                    modelList.add(item);
                    modelMap.put(cateCode,modelList);
                }
                for (AllTableGroupsModel item : allTableGroupsModelList){
                    TableConfigBO tableConfigBO = new TableConfigBO();
                    tableConfigBO.setCategory_Name(item.getCategory_Name());
                    tableConfigBO.setCategory_Code(item.getCategory_Code());
                    tableConfigBO.setCategory_icon(item.getGrp_ICON());

                    String cateCode = item.getCategory_Code();
                    if (modelMap.containsKey(cateCode)){
                        tableConfigBO.setItem(modelMap.get(cateCode));
                        tableConfigBO.setCount(modelMap.get(cateCode).size());
                    }else{
                        tableConfigBO.setItem(new ArrayList<>());
                        tableConfigBO.setCount(0);
                    }

                    list.add(tableConfigBO);
                }
                return list;
            case 3:
                List<AllTableGroupsModel> cateList = allTableGroupsDao.selectNotDisplay();
                List<String> codes = new ArrayList<>();
                for (AllTableGroupsModel item : cateList){
                    codes.add(item.getCategory_Code());
                }

                List<AllSearchTablesModel> ret = allSearchTablesDao.selectOtherTables(codes);

                TableConfigBO tableConfigBO = new TableConfigBO();
                tableConfigBO.setCategory_Name("其他类型");
                tableConfigBO.setCategory_icon("http://abc-market-web-image.oss-cn-hangzhou.aliyuncs.com/others/others.png");
                tableConfigBO.setCount(ObjectUtils.isEmpty(ret) ? 0 : ret.size());
                tableConfigBO.setItem(ObjectUtils.isEmpty(ret) ? new ArrayList<>() : ret);

                list.add(tableConfigBO);
                return list;
            default:
                return null;
        }
    }
}
