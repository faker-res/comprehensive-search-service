package la.niub.abcapi.servicecompre.model.nosql;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "backend_charts")
public class BackendChartsModel extends ChartsModel{
}

