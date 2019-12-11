package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.model.FundManagerInfoModel;
import la.niub.abcapi.servicecompre.model.FundManagerStockModel;
import la.niub.abcapi.servicecompre.model.request.FundManagerFundChartRequest;
import la.niub.abcapi.servicecompre.model.request.FundManagerFundInfoRequest;
import la.niub.abcapi.servicecompre.model.response.*;

import java.util.List;
import java.util.Map;

public interface IFundManagerService {

    FundManagerInfoModel selectFundManagerInfoByPeoUniCode(Long peo_uni_code) throws Exception;

    List<HotPeoListResponse> selectFundManagerStarList() throws Exception;

    FundManagerIndexResponse selectFundManagerIndexByPeoUniCode(Long peo_uni_code) throws Exception;

    FundManagerPositionDistributionResponse selectFundManagerPositionDistributionByPeoUniCode(Long peo_uni_code) throws Exception;

    List<FundManagerStockModel> selectFundManagerStockBySecUniCode(Long sec_uni_code) throws Exception;

    List<FundManagerFundInfoResponse> getFundInfo(FundManagerFundInfoRequest request) throws Exception;

    Map<String, Object> getFundChart(FundManagerFundChartRequest request) throws Exception;

    FundManagerCompetitiveStrengthAnalysisResponse getCompetitiveStrengthAnalysis(Long peo_uni_code) throws Exception;
}
