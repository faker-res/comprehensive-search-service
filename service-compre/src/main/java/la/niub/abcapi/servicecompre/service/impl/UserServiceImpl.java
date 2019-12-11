package la.niub.abcapi.servicecompre.service.impl;

import la.niub.abcapi.servicecompre.component.util.StringUtil;
import la.niub.abcapi.servicecompre.dao.reporter.IReportDao;
import la.niub.abcapi.servicecompre.dao.reporter.IUserSubscribeDao;
import la.niub.abcapi.servicecompre.dao.reporter.IUserSubscribeInfoDao;
import la.niub.abcapi.servicecompre.model.ReportShareUserModel;
import la.niub.abcapi.servicecompre.model.UserSubscribeInfoModel;
import la.niub.abcapi.servicecompre.model.UserSubscribeModel;
import la.niub.abcapi.servicecompre.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IReportDao reportDao;

    @Autowired
    IUserSubscribeDao userSubscribeDao;

    @Autowired
    IUserSubscribeInfoDao userSubscribeInfoDao;

    @Override
    public Map<String,String> getUserStock(String uid) {
        //@todo 对接订阅
        //根据用户ID获取分组
        List<UserSubscribeModel> userSubscribe = userSubscribeDao.getRecords(uid,0);

        //根据分组ID获取股票信息
        List<Integer> groupId = new ArrayList<>();
        if (userSubscribe != null && !userSubscribe.isEmpty()){
            for (UserSubscribeModel item : userSubscribe){
                groupId.add(item.getId());
            }
        }

        Map<String,String> stockInfo = new HashMap<>();
        if (!groupId.isEmpty()){
            List<UserSubscribeInfoModel> result = userSubscribeInfoDao.getSubscribeInfo(groupId,0);
            if (result != null && !result.isEmpty()){
                for (UserSubscribeInfoModel item : result){
                    if (!StringUtil.isEmpty(item.getTarget_info())){
                        stockInfo.put(item.getTarget_info(),item.getTarget());
                    }
                }
            }
        }
        return stockInfo;
    }

    @Override
    public Map<Integer, String> getUsersByReportIds(List<Integer> reportIds) {
        Map<Integer, String> maps = new HashMap<>();
        if (reportIds.isEmpty()){
            return maps;
        }
        Integer mod = 52940;
        for (Integer reportId : reportIds){
            maps.put(reportId,String.valueOf(reportId%mod));
        }

        List<ReportShareUserModel> reportShareUserModelList = reportDao.getShareUser(new ArrayList<>(maps.values()));
        if (reportShareUserModelList != null && !reportShareUserModelList.isEmpty()){
            Map<Integer,String> reportShareUserModelMap = new HashMap<>();
            for (ReportShareUserModel item : reportShareUserModelList){
                reportShareUserModelMap.put(item.getId(),item.getEmail());
            }
            for (Map.Entry<Integer, String> entry : maps.entrySet()){
                String email = reportShareUserModelMap.get(Integer.valueOf(entry.getValue()));
                if (email != null){
                    Integer pos = email.indexOf("@");
                    if (pos > 3){
                        StringBuffer buffer = new StringBuffer(email);
                        email = buffer.replace(2,2+(pos-3),"*").toString();
                    }else if (pos > 0){
                        StringBuffer buffer = new StringBuffer(email);
                        email = buffer.replace(pos-1,(pos-1)+1,"*").toString();
                    }
                    maps.put(entry.getKey(),email);
                }else{
                    maps.put(entry.getKey(),"fy*@tjsemi.com");
                }
            }
        }
        return maps;
    }
}
