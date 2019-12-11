package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.model.OrgBasicInfoModel;
import la.niub.abcapi.servicecompre.model.bo.fund.FundCompanyManagerBO;
import la.niub.abcapi.servicecompre.model.request.*;
import la.niub.abcapi.servicecompre.model.response.FundCompanyKpiResponse;
import la.niub.abcapi.servicecompre.model.response.FundCompanyLawsuitResponse;
import la.niub.abcapi.servicecompre.model.response.FundCompanyNewsResponse;
import la.niub.abcapi.servicecompre.model.response.message.MessageResponse;

import java.util.List;
import java.util.Map;

public interface IFundCompanyService {

    List<MessageResponse> getFundCompanyWechatList(Long com_uni_code) throws Exception;

    OrgBasicInfoModel getCompanyByOrgName(String orgName);

    FundCompanyKpiResponse kpi(FundCompanyKpiRequest fundCompanyKpiRequest) throws Throwable;

    FundCompanyManagerBO manager(FundCompanyManagerRequest fundCompanyManagerRequest) throws Throwable;

    FundCompanyLawsuitResponse lawsuit(FundCompanyLawsuitRequest fundCompanyLawsuitRequest) throws Throwable;

    FundCompanyNewsResponse news(FundCompanyNewsRequest fundCompanyNewsRequest) throws Throwable;

    List<Map<String, String>> getProductCategory(Long com_uni_code) throws Exception;

    Map<String, Object> getProductInfo(FundCompanyProductInfoRequest request) throws Exception;

    List<Map<String, String>> getAchievementsCategory(Long com_uni_code) throws Exception;

    Map<String, Object> getAchievementsInfo(FundCompanyAchievementsInfoRequest request) throws Exception;

    Map<String, Object> getAchievementsChart(FundCompanyAchievementsChartRequest request) throws Exception;
}
