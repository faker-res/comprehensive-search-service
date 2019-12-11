import React from 'react';
import { Route } from 'react-router-dom';
import Loadable from 'react-loadable';
import Loading from '../Loading';

//导入子组件-上传研报
const StockPool = Loadable({
    loader: () => import("../../StockPoolGroup"),
    loading: Loading
});
const ProductModel = Loadable({
    loader: () => import("../../ProductModelGroup"),
    loading: Loading
});
const SimulationGroup = Loadable({
    loader: () => import("../../SimulationGroup"),
    loading: Loading
});
const SimulationDetails = Loadable({
    loader: () => import("../../SimulationDetails"),
    loading: Loading
});

const MyStockPage = Loadable({
    loader: () => import("../../MyStockPage"),
    loading: Loading
});

const BargainReceipt = Loadable({
    loader: () => import("../../BargainReceipt"),
    loading: Loading
});

const Index = ({ match }) => (
    <div className="module-report">
        <Route exact path={`${match.url}/`} component={MyStockPage} />
        <Route exact path={`${match.url}/mystock`} component={MyStockPage} />
        <Route path={`${match.url}/simulation-group`} component={SimulationGroup} />
        <Route path={`${match.url}/stock-pool`} component={StockPool} />
        <Route path={`${match.url}/product-model`} component={ProductModel} />
        <Route path={`${match.url}/bargain-receipt`} component={BargainReceipt} />
        <Route exact path={`${match.url}/SimulationDetails`} component={SimulationDetails} />
    </div>
);

export default Index;