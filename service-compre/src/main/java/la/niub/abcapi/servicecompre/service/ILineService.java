package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.config.enums.LineTypeEnum;
import la.niub.abcapi.servicecompre.model.bo.line.KlineCompanyBO;
import la.niub.abcapi.servicecompre.model.bo.line.PublicCompanyTimeBO;
import la.niub.abcapi.servicecompre.model.bo.line.RealTimeBO;
import la.niub.abcapi.servicecompre.model.bo.line.TimelineCompanyBO;

import java.util.Date;
import java.util.List;

/**
 * 获取曲线
 */
public interface ILineService {

    /**
     * 获取分时线
     * @param startTime
     * @param stockCode
     * @return
     */
    List<TimelineCompanyBO> getTimeline(Date startTime, String stockCode);

    /**
     * 获取K线
     * @param startTime
     * @param stockCode
     * @param lineTypeEnum
     * @return
     */
    List<KlineCompanyBO> getKline(Date startTime, String stockCode, LineTypeEnum lineTypeEnum);

    /**
     * 获取实时数据
     * @param stockCode
     * @return
     */
    RealTimeBO getRealTime(String stockCode);

    /**
     * 获取行业实时数据
     * @param indexUniCode
     * @return
     */
    PublicCompanyTimeBO getPublicCompanyRealTime(Long indexUniCode);
}
