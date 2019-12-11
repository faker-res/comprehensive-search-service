package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.model.request.ReportRequest;

import java.util.Map;

/**
 * 解析处理结果
 */
public interface IParseResultService {

    /**
     * 是否按组查询
     * @param request
     * @return
     */
    Boolean isGroupSearch(ReportRequest request);

    /**
     * 从oss获取文件
     * @param urls
     * @return
     */
    Map<String, String> getFilesFormOssByUrl(Map<String, String> urls);

    /**
     * 请求url地址返回文本结果
     * @param url
     * @return
     */
    String curl(String url);
}
