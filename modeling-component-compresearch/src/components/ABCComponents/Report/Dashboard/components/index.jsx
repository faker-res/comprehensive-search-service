import React, { Component } from 'react'
import { withRouter } from 'react-router-dom'
import { inject, observer } from 'mobx-react'
import PropTypes from 'prop-types';
import { getQueryString } from '../../../../../lib/lib'
import ComTabs from './ComTabs';
import { List } from 'antd'
import { getDay } from '../../../../../lib/utils';
import ReportItem from './ReportItem';
import empty from '../../../../../assets/image/new-report/icon_empty.png';
import ImportantSearch from './ImportantSearch';
import indu from '../../../../../assets/image/new-report/icon_indu.svg';
import category from '../../../../../assets/image/new-report/icon_type.svg';
import HotList from './HotList';

import '../index.scss';

@withRouter
@inject('reportStore', 'searchStore')
@observer
class TabOfReportIndex extends Component {
    constructor() {
        super()

        this.onLoadMore = this.onLoadMore.bind(this);
        this.clickSearchType = this.clickSearchType.bind(this);
        this.showList = this.showList.bind(this);
        this.state = {
        }
    }
    clickCallBack = () => {

    }
    handleSelectRating = (value) => {
        // this.handleMaps('selectedRatingMap', 'rating', 'selected_rating', value);
    }
    onLoadMore(e, listName) {
        let { tab = "" } = this.props;
        if (listName === 'myStockReportList') {
            // 自选股查看更多
            // window.open('/index/report?stock_filter=true');
            window.open(`/${tab}/report-results?filters=stock`);
        } else {
            let obj = { "select": 'report', "value": e.target.dataset.type }
            let search = encodeURIComponent(JSON.stringify([obj]));
            // window.open('/index/report?search=' + search);
            window.open(`/${tab}/report-results?report=${e.target.dataset.type}`)
        }
    }
    getLoadMoreInfo = (listName, type) => {
        const { reportState } = this.props.reportStore;
        let nameList = [];
        if (listName.indexOf('.') > -1) {
            nameList = listName.split('.');
        }
        return (
            <div className={`center_middle`} style={{ height: '50px' }}>
                {
                    nameList.length > 0 && (
                        <div className={(reportState[nameList[0]] && reportState[nameList[0]][nameList[1]] && reportState[nameList[0]][nameList[1]].length > 0) ? '' : 'display_none'} data-type={type} onClick={(e) => { this.onLoadMore(e, listName) }} style={{ cursor: 'pointer', color: '#828A92' }}>查看更多</div>
                    )
                }
                {
                    nameList.length == 0 && (
                        <div className={reportState[listName] && reportState[listName].length > 0 ? '' : 'display_none'} data-type={listName} onClick={(e) => { this.onLoadMore(e, listName) }} style={{ cursor: 'pointer', color: '#828A92' }}>查看更多</div>
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
        this.props.history.push("/report-reading/list");
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
        this.props.history.push("/report-reading/seereport");
    }

    // 显示每一个tab里面的内容
    showList(listName, type) {
        const { reportState, loadingMySotckReport } = this.props.reportStore;
        const { searchData } = reportState;
        const { tab } = this.props;
        let nameList = [];
        if (listName.indexOf('.') > -1) {
            nameList = listName.split('.');
        }

        if ((listName === 'myStockReportList') && loadingMySotckReport == false && reportState[listName].length === 0) {
            // 自选股
            return (
                <div className={`col_center_middle subscribe_empty`}>
                    <img src={empty} alt={empty} />
                    <div>暂无内容</div>
                </div>
            );
        } else {
            if (nameList.length > 0 && reportState[nameList[0]][nameList[1]].length === 0) {
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
                    loadMore={this.getLoadMoreInfo(listName, type)}
                    dataSource={nameList.length > 0 ? reportState[nameList[0]][nameList[1]] : reportState[listName]}
                    renderItem={item => (
                        <List.Item key={item.id} style={{ paddingLeft: '0', paddingRight: '0', width: '100%' }}>
                            <div className={'middle'} style={{ width: '100%' }}>
                                <ReportItem
                                    tab={tab}
                                    item={item}
                                    searchData={searchData}
                                    clickCallBack={this.clickCallBack}
                                    handleSelectRating={this.handleSelectRating}
                                    clickStockNameCallBack={this.clickStockName}
                                    isShowProperty={(listName == 'myStockReportList' || listName == 'authorReportList') ? true : false}
                                />
                            </div>
                        </List.Item>
                    )}
                />
            );
        }
    }
    changeSearchObj2Text(search = "") {
        // 把搜索条件格式化为json字符串
        let searchQuery = search.trim().split(/\s+/).filter(v => v);

        return JSON.stringify(searchQuery.map(item => {
            let [key, value] = item.replace(":", "_#_#_").split('_#_#_');
            return {
                select: key,
                value: value
            }
        }));
    }
    clickSearchType(t, type) {
        let { tab = "" } = this.props;
        let obj = { "select": '', "value": t.name }
        if (type === 'industry') {
            // 主要行业
            obj.select = 'industry'
        } else if (type === 'category') {
            // 报告类型
            obj.select = 'report'
        } else if (type === 'company') {
            // 报告类型
            obj.select = 'company'
            window.open(`/report/stock?${obj.select}=${encodeURIComponent(t.name)}`)
            return;
        }
        let search = encodeURIComponent(JSON.stringify([obj]));
        // window.open('/index/report?search=' + search);
        // 研报类型：/report/secret/report-results?report={}，多个类型用“|”隔开
        // 行业：/report/secret/report-results?industry={}，多个类型用“|”隔开
        // 公司：/report/secret/report-results?company={}，多个类型用“|”隔开

        window.open(`/${tab}/report-results?${obj.select}=${encodeURIComponent(t.name)}`)
    }
    componentWillReceiveProps(nextProps) {
        let {
            searchStore = {}
        } = nextProps;

        let searchTime = searchStore.search && searchStore.search.cur_time || "";
        let newOp = "" + searchTime;
        let oriOp = "" + this.oriOp;


        if (newOp != oriOp) {
            if (!window.location.search) {
                return;
            }
            this.props.history.push('/index/report?search=' + encodeURIComponent(getQueryString(window.location.search, 'search')))
        }
        this.oriOp = newOp;
    }
    componentDidMount() {
        let {
            // 面板Tab配置
            panelTabs = [],
            tab
        } = this.props;
        let [
            first = [],
            second = [],
            third = [],
            fourth = []
        ] = panelTabs;

        let flag = tab.indexOf('viewpoint') != -1;
        // 获取自选股
        this.props.reportStore.fetchMyStockReportList(
            'fetchMyStockReportList',
            {},
            flag
        );
        // 获取顶上 报告类型和主要行业
        this.props.reportStore.fetchHomeBasicData(
            'fetchHomeBasicData',
            {},
            flag
        );

        if (first.length) {
            // 获取公司、行业、宏观、通用数据
            this.props.reportStore.fetchImportantTypeReport(
                'fetchImportantTypeReport',
                first,
                flag
            );
        }

        if (second.length) {
            // 获取晨会纪要、早间资讯
            this.props.reportStore.fetchTodayReadingReport(
                'fetchTodayReadingReport',
                second,
                flag
            );
        }
        if (third.length) {
            // 沪深股研究、港股研究、美股研究
            this.props.reportStore.fetchMarketTypeReport(
                'fetchMarketTypeReport',
                third,
                flag
            );
        }
        if (fourth.length) {
            // 基金研究、债券研究、期货研究、期权研究
            this.props.reportStore.fetchMoneyTypeReport(
                'fetchMoneyTypeReport',
                fourth,
                flag
            );
        }
    }


    tabOnChange = (t) => {
        let {
            // 面板Tab配置
            tab
        } = this.props
        const { reportState } = this.props.reportStore;
        let flag = tab.indexOf('viewpoint') != -1;
        console.log(t)
        switch (t) {
            case '公司研究':
                if (reportState.importantTypeReport.industryDeepReportList.length <= 0) {
                    this.props.reportStore.fetchImportantTypeReport('', {}, flag)
                }
                break
            case '行业研究':
                if (reportState.importantTypeReport.companyDeepReportList.length <= 0) {
                    this.props.reportStore.getCompanyDeepReportList('', {}, flag)
                }
                break
            case '宏观研究':
                if (reportState.importantTypeReport.industryResearchReportList.length <= 0) {
                    this.props.reportStore.getIndustryResearchReportList('', {}, flag)
                }
                break
            case '通用报告':
                if (reportState.importantTypeReport.companyResearchReportList.length <= 0) {
                    this.props.reportStore.companyResearchReportList('', {}, flag)
                }
                break
            case '港股研究':
                if (reportState.marketTypeReport.USReportList.length <= 0) {
                    this.props.reportStore.getHKReportList('', {}, flag)
                }
                break
            case '美股研究':
                if (reportState.marketTypeReport.TWReportList.length <= 0) {
                    this.props.reportStore.getTWReportList('', {}, flag)
                }
                break
            case '债券研究':
                if (reportState.moneyTypeReport.futuresReportList.length <= 0) {
                    this.props.reportStore.getFuturesReportList('', {}, flag)
                }
                break
            // 期货研究
            case '期货研究':
                if (reportState.moneyTypeReport.bondReportList.length <= 0) {
                    this.props.reportStore.getBondReportList('', {}, flag)
                }
                break
            case '期权研究':
                if (reportState.moneyTypeReport.optionReportList.length <= 0) {
                    this.props.reportStore.getOptionReportList('', {}, flag)
                }
                break
        }
    }


    render() {
        let {
            // 面板Tab配置
            panelTabs = []
        } = this.props;
        let [
            first = [],
            second = [],
            third = [],
            fourth = []
        ] = panelTabs;
        const importantTypeList = [];
        const { mianIndustryList = [], mianCategoryList = [], mostReadStockList, hotIndustryList, hotStockList } = this.props.reportStore.reportState;
        // console.log('hotIndustryList', typeof hotIndustryList.toJS !== 'undefined' && hotIndustryList.toJS() || hotIndustryList)
        importantTypeList.push({ title: '主要行业', icon: indu, typeList: mianIndustryList, type: 'industry' });
        importantTypeList.push({ title: '报告类型', icon: category, typeList: mianCategoryList, type: 'category' });
        let lastWeek = '';
        let yesterday = getDay(-1, '/');
        let lastWeekDay = getDay(-7, '/');
        lastWeek = `${lastWeekDay.substr(5)}-${yesterday.substr(5)}`;
        return (
            <div className="container mail-container">
                <div className='result-left-wrp'>
                    <div className="mail-report-wrapper">
                        <div className="mail-report-wrapper-l">
                            <div style={{ marginBottom: '10px', backgroundColor: 'white' }}>
                                {
                                    !this.props.reportStore.loadingList
                                        && (!mianIndustryList || !mianIndustryList.length)
                                        && (!mianCategoryList || !mianCategoryList.length) ? (
                                            <div className={`col_center_middle subscribe_empty important_type_container`}>
                                                <img src={empty} alt={empty} />
                                                <div>暂无内容</div>
                                            </div>
                                        ) : (
                                            <ImportantSearch
                                                importantTypeList={importantTypeList}
                                                clickSearchType={this.clickSearchType}
                                                isLoading={this.props.reportStore.loadingList}
                                            />
                                        )
                                }
                            </div>
                            {/* 自选股 */}
                            <div className={'report_list important_type_container'}>
                                <ComTabs
                                    title="自选股"
                                    tabNameList={['']}
                                    tabContentList={[this.showList('myStockReportList')]}
                                    isLoading={this.props.reportStore.loadingMySotckReport}
                                />
                            </div>
                            {/* 研究和通用报告 */}
                            <div className={'report_list important_type_container'}>
                                <ComTabs
                                    title=""
                                    tabNameList={first.map(v => v.category)}
                                    tabContentList={[this.showList('importantTypeReport.industryDeepReportList', (first[0] || {}).category),
                                    this.showList('importantTypeReport.companyDeepReportList', (first[1] || {}).category),
                                    this.showList('importantTypeReport.industryResearchReportList', (first[2] || {}).category),
                                    this.showList('importantTypeReport.companyResearchReportList', (first[3] || {}).category)]}
                                    selectedIndex="0"
                                    isLoading={this.props.reportStore.loadingImportantTypeReport}
                                    change={this.tabOnChange}
                                />
                            </div>

                            {!!second.length && <div className={'report_list type_report_list important_type_container'}>
                                <ComTabs
                                    title=""
                                    tabNameList={second.map(v => v.category)}
                                    tabContentList={[this.showList('todayReadingReport.todaySummaryReportList', (second[0] || {}).category),
                                    this.showList('todayReadingReport.todayMessageReportList', (second[1] || {}).category)]}
                                    selectedIndex="0"
                                    isLoading={this.props.reportStore.loadingTodayReadingReport}
                                />
                            </div>}
                            {/* 港股 沪深 每股 */}
                            <div className={'report_list type_report_list important_type_container'}>
                                <ComTabs
                                    title=""
                                    tabNameList={third.map(v => v.category)}
                                    tabContentList={[this.showList('marketTypeReport.HKReportList', (third[0] || {}).category),
                                    this.showList('marketTypeReport.USReportList', (third[1] || {}).category),
                                    this.showList('marketTypeReport.TWReportList', (third[2] || {}).category)]}
                                    selectedIndex="0"
                                    isLoading={this.props.reportStore.loadingMarketTypeReport}
                                    change={this.tabOnChange}
                                />
                            </div>
                            {/* 期权、债券、期货、基金研究 */}
                            <div className={'report_list type_report_list important_type_container'}>
                                <ComTabs
                                    title=""
                                    tabNameList={fourth.map(v => v.category)}
                                    tabContentList={[this.showList('moneyTypeReport.fundReportList', (fourth[0] || {}).category),
                                    this.showList('moneyTypeReport.futuresReportList', (fourth[1] || {}).category),
                                    this.showList('moneyTypeReport.bondReportList', (fourth[2] || {}).category),
                                    this.showList('moneyTypeReport.optionReportList', (fourth[4] || {}).category)]}
                                    selectedIndex="0"
                                    isLoading={this.props.reportStore.loadingMoneyTypeReport}
                                    change={this.tabOnChange}
                                />
                            </div>
                        </div>
                        <div className="mail-report-wrapper-r">
                            <div className={'tree_container important_type_container'}>
                                <ComTabs
                                    title="我的热门行业"
                                    tabNameList={['']}
                                    tabContentList={[<HotList
                                        extraInfo={lastWeek}
                                        hotList={hotIndustryList}
                                        clickHandleCallback={this.clickSearchType}
                                        type='industry'
                                    />]}
                                    isLoading={this.props.reportStore.loadingList}
                                />
                            </div>
                            <div className={'tree_container important_type_container'}>
                                <ComTabs
                                    title="我的热门公司"
                                    tabNameList={['']}
                                    tabContentList={[<HotList
                                        extraInfo={lastWeek}
                                        hotList={hotStockList}
                                        clickHandleCallback={this.clickSearchType}
                                        type='company'
                                    />]}
                                    isLoading={this.props.reportStore.loadingList}
                                />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}
TabOfReportIndex.propTypes = {
    // 面板所处Tab
    tab: PropTypes.string,
};

TabOfReportIndex.defaultProps = {
    tab: "report"
};

export default TabOfReportIndex;
