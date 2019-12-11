package la.niub.abcapi.servicecompre.dao;

import la.niub.abcapi.servicecompre.model.nosql.JuchaoTextModel;
import org.bson.types.ObjectId;

import java.util.List;

public interface IJuchaoTextDao {
    List<JuchaoTextModel> getByFileIds(List<ObjectId> fileIds);
}
