package la.niub.abcapi.servicecompre.service;

import la.niub.abcapi.servicecompre.model.Navigation_bar;

import java.util.List;
import java.util.Map;

public interface INavigationService {
    //查询模块的导航栏数据
    public List<Navigation_bar> queryMenuList(Map map);
}
