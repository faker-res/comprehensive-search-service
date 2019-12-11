package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.component.exception.CustomException;
import la.niub.abcapi.servicecompre.model.request.BatchExportRequest;

/**
 * 通用服务
 */
public interface ICommonService {

    /**
     * 批量导出
     * @param request
     * @return
     */
    Object buildBatchExport(BatchExportRequest request) throws CustomException;
}
