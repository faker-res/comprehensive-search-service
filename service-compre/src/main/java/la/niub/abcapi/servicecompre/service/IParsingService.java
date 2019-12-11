package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.model.nosql.BitmapCluserModel;
import la.niub.abcapi.servicecompre.model.request.ParsingRequest;

import java.util.List;
import java.util.Map;

/**
 * Parsing Service
 *
 * @author amen
 */
public interface IParsingService {

    /**
     * 插队解析
     * @param params
     * @return
     * @throws Throwable
     */
    Object send(ParsingRequest params) throws Throwable;

    /**
     * 获取解析结果
     * @param params
     * @return
     * @throws Throwable
     */
    Object parseState(ParsingRequest params) throws  Throwable;

    /**
     * 获取推送结果
     * @param params
     * @return
     * @throws Throwable
     */
    Map<String, Object> post(ParsingRequest params) throws  Throwable;

    /**
     * 获取最新版本号
     * @param params
     * @return
     */
    Object version(ParsingRequest params);
}
