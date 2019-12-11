package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.config.enums.LineTypeEnum;
import la.niub.abcapi.servicecompre.model.SecBasicInfoModel;
import la.niub.abcapi.servicecompre.model.bo.line.KlineCompanyBO;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IStockService {

    /**
     * 根据stock_code获取信息
     * @param stockCode
     * @return
     */
    SecBasicInfoModel buildRecordByStockCode(String stockCode);

    List<Map<String,Object>> getRecordsBySecUniCode(String code,Date startTime,String graphType);

    /**
     * 获取K线历史数据
     * @param startTime
     * @param stockCode
     * @param lineTypeEnum
     * @return
     */
    List<KlineCompanyBO> getKlineHistory(Date startTime, String stockCode, LineTypeEnum lineTypeEnum);

    List<SecBasicInfoModel> getRecordsBySecUniCodes(List<Long> secUniCodes);

    List<SecBasicInfoModel> getRecordsBySecCodes(List<String> secCodes);
}
