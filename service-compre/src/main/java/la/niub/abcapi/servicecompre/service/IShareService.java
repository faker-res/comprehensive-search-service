package la.niub.abcapi.servicecompre.service;

public interface IShareService {

    /**
     * 生成分享链接
     * @param type
     * @param id
     * @return
     */
    String buildShareUrl(String type, Integer id);
}
