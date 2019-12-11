package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.config.enums.DifferEnum;
import la.niub.abcapi.servicecompre.model.IndexBasicInfoModel;
import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyDetailBO;
import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyDifferBO;
import la.niub.abcapi.servicecompre.model.bo.publiccompany.PublicCompanyIndexBO;
import la.niub.abcapi.servicecompre.model.request.publiccompany.PublicCompanyFundFlowRequest;
import la.niub.abcapi.servicecompre.model.request.publiccompany.PublicCompanyHeavilyFundRequest;
import la.niub.abcapi.servicecompre.model.response.publiccompany.PublicCompanyFundFlowItemResponse;
import la.niub.abcapi.servicecompre.model.response.publiccompany.PublicCompanyHeavilyFundItemResponse;

import java.util.Date;
import java.util.List;

/**
 * 行业
 */
public interface IPublicCompanyService {

    PublicCompanyDetailBO getDetail(Long indexUniCode);

    List<PublicCompanyIndexBO> getIndex(Long indexUniCode,Date endDate);

    List<PublicCompanyDifferBO> getDiffer(Long indexUniCode, DifferEnum differEnum, Integer limit) throws Exception;

    IndexBasicInfoModel getIndustryByName(String industryName);

    List<PublicCompanyHeavilyFundItemResponse> heavilyFund(PublicCompanyHeavilyFundRequest publicCompanyHeavilyFundRequest);

    List<PublicCompanyFundFlowItemResponse> fundFlow(PublicCompanyFundFlowRequest publicCompanyFundFlowRequest) throws Throwable;
}
