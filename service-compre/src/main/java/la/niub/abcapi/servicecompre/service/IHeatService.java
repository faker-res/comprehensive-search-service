package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.config.enums.FundPeriodEnum;
import la.niub.abcapi.servicecompre.config.enums.PepbEnum;
import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyHeatBO;
import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyStockHeatBO;

import java.util.List;

/**
 * 热力图
 */
public interface IHeatService {

    /**
     * 全行业热力图
     * @return
     */
    List<PublicCompanyHeatBO> getPublicCompanyHeatByPeriod(FundPeriodEnum period,Integer limit);
    List<PublicCompanyHeatBO> getPublicCompanyHeatByPepb(PepbEnum pepb, Integer limit);

    /**
     * 成分股热力图
     * @param indexUniCode
     * @param period
     * @param limit
     * @return
     */
    List<PublicCompanyStockHeatBO> getPublicCompanyStockHeat(Long indexUniCode, FundPeriodEnum period, Integer limit);

    /**
     * 获取成分股热力数据
     * @param secUniCodesOfALL
     * @param period
     * @param limit
     * @return
     */
    List<PublicCompanyStockHeatBO> handleStockHeat(List<Long> secUniCodesOfALL, FundPeriodEnum period, Integer limit);
}
