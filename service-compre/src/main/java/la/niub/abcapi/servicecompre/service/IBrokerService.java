package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.model.RepostTypeModel;
import la.niub.abcapi.servicecompre.model.bo.LatestReportBO;
import la.niub.abcapi.servicecompre.model.bo.ReportCountBO;
import la.niub.abcapi.servicecompre.model.bo.ResearchIndustryBasicInfoBO;
import la.niub.abcapi.servicecompre.model.bo.StarAnalystInfoBO;
import la.niub.abcapi.servicecompre.model.bo.card.CardAnalystReportStocksBO;
import la.niub.abcapi.servicecompre.model.request.BrokerHeatMapRequest;
import la.niub.abcapi.servicecompre.model.request.BrokerNewsRequest;
import la.niub.abcapi.servicecompre.model.request.BrokerOverviewRequest;
import la.niub.abcapi.servicecompre.model.response.BrokerHeatMapItemResponse;
import la.niub.abcapi.servicecompre.model.response.BrokerNewsResponse;
import la.niub.abcapi.servicecompre.model.response.BrokerOverviewResponse;
import org.springframework.cache.annotation.Cacheable;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IBrokerService {
    BrokerOverviewResponse overview(BrokerOverviewRequest brokerOverviewRequest) throws Throwable;

    List<BrokerHeatMapItemResponse> heatMap(BrokerHeatMapRequest brokerHeatMapRequest) throws Throwable;

    BrokerNewsResponse news(BrokerNewsRequest brokerNewsRequest) throws Throwable;

    Map<String,Object> getActivities(Date startDate, Date endDate, Integer org_uni_code) throws Exception;

    List<ResearchIndustryBasicInfoBO> researchIndustryList(String broker_name) throws Exception;

    List<StarAnalystInfoBO> starAnalystList(Long org_uni_code, String indu_name) throws Exception;

    List<StarAnalystInfoBO> starAnalystList2(Long org_uni_code, String indu_name) throws Exception;

    List<CardAnalystReportStocksBO> analystLatestResearchStocks(String peo_uni_code) throws Exception;

    List<RepostTypeModel> reportType() throws Exception;

    List<ReportCountBO> reportCount(Long org_uni_code) throws Exception;

    List<LatestReportBO> latestReport(Long org_uni_code, String type_id) throws Exception;

    List<StarAnalystInfoBO> otherStarAnalystList(Long org_uni_code, String type_id) throws Exception;

    Map<String,Object> getAnalystFlow(BigInteger org_uni_code) throws Exception;

    List<Map<String, Object>> getInduStock(String indu_name, String type, String broker_name, Integer limit) throws Exception;
}
