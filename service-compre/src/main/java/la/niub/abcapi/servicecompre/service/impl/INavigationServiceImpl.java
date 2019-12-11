package la.niub.abcapi.servicecompre.service.impl;

import la.niub.abcapi.servicecompre.dao.reporter.INavigation_barDao;
import la.niub.abcapi.servicecompre.model.Navigation_bar;
import la.niub.abcapi.servicecompre.service.INavigationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class INavigationServiceImpl implements INavigationService {

    private static Logger logger = LogManager.getLogger(INavigationServiceImpl.class);

    @Autowired
    private INavigation_barDao iNavigation_barDao;
    /****
     * 查询模块的导航栏数据
     * @param map
     * @return
     */
    public List<Navigation_bar> queryMenuList(Map map)
    {
        // 最后的结果
        List<Navigation_bar> menuList = new ArrayList<Navigation_bar>();
        try{
            // 原始的数据
            List<Navigation_bar> rootMenu = iNavigation_barDao.find(map);
            // 先找到所有的一级菜单
            for (int i = 0; i < rootMenu.size(); i++) {
                // 一级菜单没有parentId
                if (rootMenu.get(i).getParent_id().equals("0")) {
                    menuList.add(rootMenu.get(i));
                }
            }
            // 为一级菜单设置子菜单，getChild是递归调用的
            for (Navigation_bar menu : menuList) {
                menu.setChildren(getChilds(menu.getId(), rootMenu));
            }
        }catch (Exception e){
            logger.error(e.toString());
        }finally {
            return menuList;
        }
    }

    /**
     * 递归查找子菜单
     *
     * @param id
     *            当前菜单id
     * @param rootMenu
     *            要查找的列表
     * @return
     */
    public List<Navigation_bar> getChilds(String id, List<Navigation_bar> rootMenu) {
        // 子菜单
        List<Navigation_bar> childList = new ArrayList<>();
        for (Navigation_bar menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (menu.getParent_id()!=null) {
                if (menu.getParent_id().equals(id)) {
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (Navigation_bar menu : childList) {// 没有url子菜单还有子菜单
            // 递归
            menu.setChildren(getChilds(menu.getId(), rootMenu));
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
