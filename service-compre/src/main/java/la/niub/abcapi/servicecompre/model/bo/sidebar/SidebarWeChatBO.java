package la.niub.abcapi.servicecompre.model.bo.sidebar;

import java.util.List;

public class SidebarWeChatBO extends SidebarBO {

    private List<SidebarWeChatHotArticleBO> hotArtice;

    private List<SidebarWeChatHotPublicBO> hotPublic;

    public List<SidebarWeChatHotArticleBO> getHotArtice() {
        return hotArtice;
    }

    public void setHotArtice(List<SidebarWeChatHotArticleBO> hotArtice) {
        this.hotArtice = hotArtice;
    }

    public List<SidebarWeChatHotPublicBO> getHotPublic() {
        return hotPublic;
    }

    public void setHotPublic(List<SidebarWeChatHotPublicBO> hotPublic) {
        this.hotPublic = hotPublic;
    }
}
