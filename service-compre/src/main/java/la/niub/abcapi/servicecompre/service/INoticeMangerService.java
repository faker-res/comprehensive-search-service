package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.component.exception.ServiceException;
import la.niub.abcapi.servicecompre.model.bo.NoticeBO;
import la.niub.abcapi.servicecompre.model.request.client.NoticeRequest;

/**
 * 公告
 */
public interface INoticeMangerService {

    NoticeBO buildNoticeReadingSearchResult(String uid, NoticeRequest noticeRequest,String module,Boolean isCount) throws ServiceException;
}
