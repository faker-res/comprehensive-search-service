package la.niub.abcapi.servicecompre.dao;

import la.niub.abcapi.servicecompre.model.nosql.FinanceJuchaoItemModel;

import java.util.List;

public interface IFinanceJuchaoItemDao {
    List<FinanceJuchaoItemModel> getBySrcIds(List<String> srcIds);
}
