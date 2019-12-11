import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import { inject, observer } from 'mobx-react';
import { getQueryString } from '../../../lib/lib';
import ComTabs from './ComTabs';
import { Col, Divider, Icon, List } from 'antd';
import { getDay } from '../../../utils/utils';
import '../index.scss';
// import empty from '../empty.png'
// import HomeReportItem from 'components/HomeReportItem';
import ReportItem from './ReportItem';
import empty from '../../../assets/image/new-report/icon_empty.png';
import ImportantSearch from './ImportantSearch';
import indu from '../../../assets/image/new-report/icon_indu.svg';
import category from '../../../assets/image/new-report/icon_type.svg';
import HotList from './HotList';
import SideCard from './SideCard';
@withRouter
@inject('reportStore', 'searchStore')
// @inject('searchStore') 
@observer
class TabOfReportIndex extends Component {
    constructor() {
        super();
        this.state = {
        };
    }
    clickCallBack = () => {

    }
    handleSelectRating = (value) => {
        // this.handleMaps('selectedRatingMap', 'rating', 'selected_rating', value);
    }
    onLoadMore = (e,listName) => {
        if (listName === 'myStockReportList'){
            // 自选股查看更多
            window.open('/index/report?stock_filter=true');
        } else {
            let obj = {'select':'report','value':e.target.dataset.type};
            let search = encodeURIComponent(JSON.stringify([obj]));
            window.open('/index/report?search=' + search);
        }
    }
    getLoadMoreInfo = (listName,type) => {
        const { reportState } = this.props.reportStore;
        let nameList = [];
        if (listName.indexOf('.') > -1) {
            nameList = listName.split('.');
        }
        return (
            <div className={`center_middle`} style={{ height: '50px' }}>
                {
                    nameList.length > 0 && (
                        <div className={(reportState[nameList[0]] && reportState[nameList[0]][nameList[1]] && reportState[nameList[0]][nameList[1]].length > 0) ? '' : 'display_none'} data-type={type} onClick={(e) => {this.onLoadMore(e,listName);}} style={{ cursor: 'pointer', color: '#828A92' }}>查看更多</div>
                    )
                }
                {
                    nameList.length == 0 && (
                        <div className={reportState[listName] && reportState[listName].length > 0 ? '' : 'display_none'} data-type={listName} onClick={(e) => {this.onLoadMore(e,listName);}} style={{ cursor: 'pointer', color: '#828A92' }}>查看更多</div>
                    )
                }
            </div>
        );
    }
    clickStockName = (stockName, stockCode) => {
        const { searchData, stockSearchData } = this.props.reportStore;
        searchData.keyword_filter = stockName;
        // searchData.stockname = stockName;
        // searchData.stock_filter = stockCode;
        stockSearchData.keyword = stockName;
        this.changeStateData(['searchData', 'stockSearchData'], [searchData, stockSearchData]);
        this.props.history.push('/report-reading/list');
    }
    handleMaps = (mapName, propName, type, value) => {
        // 不知道干嘛的
        const newSearchData = this.props.reportStore.searchData;
        newSearchData.page = 1;
        newSearchData.offset = 0;
        const map = this.props.reportStore[mapName];
        if (value === '') {
            if (propName === 'file_page') {
                delete newSearchData.min_file_pages;
                delete newSearchData.max_file_pages;
            } else {
                delete newSearchData[propName];
            }
            this.changeStateData(['searchData', mapName], [newSearchData, new Map()]);
        } else if (!map.get(value)) {
            if (propName !== 'file_page') {
                if (map.size === 0) {
                    newSearchData[propName] = value;
                } else {
                    newSearchData[propName] = `${newSearchData[propName]}%7C${value}`;
                }
                map.set(value, value);
            } else {
                if (value === '10') {
                    newSearchData.max_file_pages = 10;
                    delete newSearchData.min_file_pages;
                } else if (value === '10-20') {
                    newSearchData.min_file_pages = 10;
                    newSearchData.max_file_pages = 20;
                } else if (value === '20') {
                    newSearchData.min_file_pages = 20;
                    delete newSearchData.max_file_pages;
                }
                map.clear();
                map.set(value, value);
            }
            this.changeStateData(['searchData', mapName], [newSearchData, map]);
        }
        this.props.history.push('/report-reading/seereport');
    }
    showList = (listName,type) => {
        const { reportState, loadingMySotckReport } = this.props.reportStore;
        const { searchData } = reportState;
        let nameList = [];
        if (listName.indexOf('.') > -1) {
            nameList = listName.split('.');
        }
        if ((listName === 'myStockReportList') && loadingMySotckReport == false && reportState[listName].length === 0) {
            // 自选股
            return (
                // <div className={`col_center_middle subscribe_empty`}>
                //     <img src={empty} alt={empty} />
                //     <div>您还没有订阅任何内容</div>
                //     <div>添加订阅后，您可以收到与之相关的研报信息</div>
                //     <a href="https://www.analyst.ai/account/subscription">
                //         <div className={`center_middle empty_sub`}>
                //             <Icon type="plus" />
                //             <div style={{ marginLeft: '3px', cursor: 'pointer' }}>订阅</div>
                //         </div>
                //     </a>
                // </div>
                <div className={`col_center_middle subscribe_empty`}>
                    <img src={empty} alt={empty} />
                    <div>暂无内容</div>
                </div>
            );
        } else {
            if (nameList.length > 0 && reportState[nameList[0]][nameList[1]].length === 0){
                return (
                    <div className={`col_center_middle subscribe_empty`}>
                        <img src={empty} alt={empty} />
                        <div>暂无内容</div>
                    </div>
                );
            }
            return (
                <List
                    style={{ backgroundColor: 'white', padding: '0 24px', minHeight: '250px' }}
                    loadMore={this.getLoadMoreInfo(listName,type)}
                    dataSource={nameList.length > 0 ? reportState[nameList[0]][nameList[1]] : reportState[listName]}
                    renderItem={item => (
                        <List.Item key={item.id} style={{ paddingLeft: '0', paddingRight: '0', width: '100%' }}>
                            <div className={'middle'} style={{ width: '100%' }}>
                                <ReportItem
                                    item={item}
                                    searchData={searchData}
                                    clickCallBack={this.clickCallBack}
                                    handleSelectRating={this.handleSelectRating}
                                    clickStockNameCallBack={this.clickStockName}
                                    isShowProperty={!!((listName == 'myStockReportList' || listName == 'authorReportList'))}
                                />
                            </div>
                        </List.Item>
                    )}
                />
            );
        }
    }
    changeSearchObj2Text(search = '') {
        // 把搜索条件格式化为json字符串
        let searchQuery = search.trim().split(/\s+/).filter(v => v);
    
        return JSON.stringify(searchQuery.map(item => {
          let [key, value] = item.replace(':', '_#_#_').split('_#_#_');
          return {
            select: key,
            value: value
          };
        }));
      }
    clickSearchType = (t,type) => {
        let obj = {'select':'','value':t.name};
        if (type === 'industry'){
            // 主要行业
            obj.select = 'industry';
        } else if (type === 'category') {
            // 报告类型
            obj.select = 'report';
        } else if (type === 'company') {
            // 报告类型
            obj.select = 'company';
        }
        let search = encodeURIComponent(JSON.stringify([obj]));
        window.open('/index/report?search=' + search);
    }
    componentWillReceiveProps(nextProps) {
        let {
            searchStore = {}
        } = nextProps;

        let searchTime = searchStore.search && searchStore.search.cur_time || '';
        let newOp = '' + searchTime;
        let oriOp = '' + this.oriOp;


        if (newOp != oriOp) {
            if (!window.location.search) {
                return;
            }
            this.props.history.push('/index/report?search=' + encodeURIComponent(getQueryString(window.location.search, 'search')));
        }
        this.oriOp = newOp;
    }
    componentDidMount() {
        this.props.reportStore.fetchMyStockReportList(
            'fetchMyStockReportList',
            {}
        );
        this.props.reportStore.fetchHomeBasicData(
            'fetchHomeBasicData',
            {}
        );
        this.props.reportStore.fetchImportantTypeReport(
            'fetchImportantTypeReport',
            {}
        );
        this.props.reportStore.fetchTodayReadingReport(
            'fetchTodayReadingReport',
            {}
        );
        this.props.reportStore.fetchMarketTypeReport(
            'fetchMarketTypeReport',
            {}
        );
        this.props.reportStore.fetchMoneyTypeReport(
            'fetchMoneyTypeReport',
            {}
        );
    }
    render() {
        const importantTypeList = [];
        const { mianIndustryList, mianCategoryList, mostReadStockList, hotIndustryList, hotStockList } = this.props.reportStore.reportState;
        importantTypeList.push({ title: '主要行业', icon: indu, typeList: mianIndustryList, type: 'industry' });
        importantTypeList.push({ title: '报告类型', icon: category, typeList: mianCategoryList, type: 'category' });
        let lastWeek = '';
        let yesterday = getDay(-1, '/');
        let lastWeekDay = getDay(-7, '/');
        lastWeek = `${lastWeekDay.substr(5)}-${yesterday.substr(5)}`;
        return (
            <div className="mail-report-wrapper">
                <div className="mail-report-wrapper-l">
                    <div style={{ marginBottom: '10px', backgroundColor: 'white' }}>
                        <ImportantSearch
                            importantTypeList={importantTypeList}
                            clickSearchType={this.clickSearchType}
                            isLoading={this.props.reportStore.loadingList}
                        />
                    </div>
                    <div className={'report_list'}>
                        <ComTabs
                        title="自选股"
                        tabNameList={['']}
                        tabContentList={[this.showList('myStockReportList')]}
                        isLoading={this.props.reportStore.loadingMySotckReport}
                        />
                    </div>
                    <div className={'report_list'}>
                        <ComTabs
                            title=""
                            tabNameList={['行业深度', '公司深度', '行业调研', '公司调研']}
                            tabContentList={[this.showList('importantTypeReport.industryDeepReportList','行业深度'), this.showList('importantTypeReport.companyDeepReportList','公司深度'), this.showList('importantTypeReport.industryResearchReportList','行业调研'), this.showList('importantTypeReport.companyResearchReportList','公司调研')]}
                            selectedIndex="0"
                            isLoading={this.props.reportStore.loadingImportantTypeReport}
                        />
                    </div>
                    <div className={'report_list type_report_list'}>

                        <ComTabs
                            title=""
                            tabNameList={['晨会纪要', '早间资讯']}
                            tabContentList={[this.showList('todayReadingReport.todaySummaryReportList','晨会纪要'), this.showList('todayReadingReport.todayMessageReportList','早间资讯')]}
                            selectedIndex="0"
                            isLoading={this.props.reportStore.loadingTodayReadingReport}
                        />
                    </div>
                    <div className={'report_list type_report_list'}>
                        <ComTabs
                            title=""
                            tabNameList={['港股研究', '美股研究', '台股研究']}
                            tabContentList={[this.showList('marketTypeReport.HKReportList','港股研究'), this.showList('marketTypeReport.USReportList','美股研究'), this.showList('marketTypeReport.TWReportList','台股研究')]}
                            selectedIndex="0"
                            isLoading={this.props.reportStore.loadingMarketTypeReport}
                        />
                    </div>
                    <div className={'report_list type_report_list'}>
                        <ComTabs
                            title=""
                            tabNameList={['基金研究', '期货研究', '债券研究', '期权研究']}
                            tabContentList={[this.showList('moneyTypeReport.fundReportList','基金研究'), this.showList('moneyTypeReport.futuresReportList','期货研究'), this.showList('moneyTypeReport.bondReportList','债券研究'), this.showList('moneyTypeReport.optionReportList','期权研究')]}
                            selectedIndex="0"
                            isLoading={this.props.reportStore.loadingMoneyTypeReport}
                        />
                    </div>
                </div>
                <div className="mail-report-wrapper-r">
                    {/* <div className={'tree_container'}>
                        <SideCard
                        title='我常看的公司'
                        tabNameList={['']}
                        tabContentList={[mostReadStockList]}
                        selectedIndex="0"
                        clickMostReadCallback={this.clickMostRead}
                        isLoading={this.props.loadingList}
                        />
                    </div> */}
                    <div className={'tree_container'}>
                        <ComTabs
                        title="我的热门行业"
                        tabNameList={['']}
                        tabContentList={[<HotList
                            extraInfo={lastWeek}
                            hotList={hotIndustryList}
                            clickHandleCallback={this.clickSearchType}
                            type="industry"
                        />]}
                        isLoading={this.props.reportStore.loadingList}
                        />
                    </div>
                    <div className={'tree_container'}>
                        <ComTabs
                        title="我的热门公司"
                        tabNameList={['']}
                        tabContentList={[<HotList
                            extraInfo={lastWeek}
                            hotList={hotStockList}
                            clickHandleCallback={this.clickSearchType}
                            type="company"
                        />]}
                        isLoading={this.props.reportStore.loadingList}
                        />
                    </div>
                </div>
            </div>
        );
    }
}

export default TabOfReportIndex;
