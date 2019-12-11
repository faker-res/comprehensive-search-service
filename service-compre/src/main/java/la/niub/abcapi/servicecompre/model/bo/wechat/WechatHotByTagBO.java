package la.niub.abcapi.servicecompre.model.bo.wechat;

import la.niub.abcapi.servicecompre.model.WechatPublicModel;

public class WechatHotByTagBO {

    private Integer similarity;

    private WechatPublicModel model;

    public Integer getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Integer similarity) {
        this.similarity = similarity;
    }

    public WechatPublicModel getModel() {
        return model;
    }

    public void setModel(WechatPublicModel model) {
        this.model = model;
    }
}
