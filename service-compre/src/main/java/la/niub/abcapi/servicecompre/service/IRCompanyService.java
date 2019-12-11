package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.model.bo.ReportRequest;
import la.niub.abcapi.servicecompre.model.bo.SearchReportBO;
import la.niub.abcapi.servicecompre.model.response.rentity.RCompanyBrief;
import la.niub.abcapi.servicecompre.model.response.rentity.RCompanyIndexMark;
import la.niub.abcapi.servicecompre.model.response.rentity.RCompanyIndustry;
import la.niub.abcapi.servicecompre.model.response.rentity.RCompanyMarketTotal;

import java.util.List;
import java.util.Map;

public interface IRCompanyService {
    //获取公司简介
    public Map brief(Map map);
    //获取公司董事与高管
    public Map directors(Map map);
    //获取公司行业
    public Map industry(Map map);
    //获取公司指数
    public Map indexmark(Map map);
    //所属概念
    public Map notion(Map map);
    //所属同概念个股
    public Map notionDetail(Map map);
    //获取股东十大股东明细
    public Map partner_detail(Map map);
    //获取股东十大流通股东
    public Map partner_circulate(Map map);
    //获取公司交易行情统计数据
    public Map markettotal(Map map);
    //获取公司业绩预告
    public Map foreshow(Map map);
    //获取公司行业研报
    public SearchReportBO industryReport(ReportRequest request);
    //获取公司财务摘要
    public Map summary(Map map);
    //获取公司财务资产负债
    public Map liabilities(Map map);
}
