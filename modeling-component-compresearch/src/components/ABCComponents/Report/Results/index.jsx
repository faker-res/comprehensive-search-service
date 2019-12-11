import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {
    Row,
    Col,
    notification,
    Select
} from 'antd';
import { withRouter } from 'react-router-dom';
import { inject, observer } from 'mobx-react';
import _ from 'lodash';
import { getMails, getSearchMails,getUserEmailList } from 'config/mail';
import MailList from 'components/Results/List';
import { getQueryString } from '../../lib/lib';
import SearchResultWrap from './../SearchResultHeader/';
import ReportSearchResult from './ReportList/';
import DataSearchResult from './DataList/';
import FilterBar from './FilterBar/';
// import MailDetail from 'components/Results/Detail'
import MailSortAction from 'components/Action';
import Loading from './../Loading';
import moment from 'moment';
import empty from './empty.png';
import './index.scss';

const SelectOption = Select.Option;

const SEARCH_TYPE_ALL    = {key: 'all', title: '全部'};
const SEARCH_TYPE_REPORT = {key: 'report', title: '研报'};
const SEARCH_TYPE_DATA   = {key: 'data', title: '数据'};

@withRouter @inject('listStore', 'searchStore') @observer
class MailResults extends Component {
    constructor() {
        super();
        this.state = {
            loading: true,
            list: [],
            totalCount: 0,
            moreResults: true,
            // 当前选中的搜索结果类型
            curSearchType: SEARCH_TYPE_ALL,
            // 数据图二级过滤条件
            filtersData: [],
            // 研报二级过滤条件
            filtersReport: [],
            // 数据图搜索总条数
            totalData: 0,
            // 研报搜索总条数
            totalReport: 0,
            // 研报排序条件
            orderReport: 'all_score',
        };
    }

    static propTypes = {
        isSearched: PropTypes.bool
    }
    reflashPage = () => {
        window.location.reload();
    }

    componentWillReceiveProps(nextProps) {
        let {
            searchStore = {}
        } = nextProps;
        let {
            curSearchType
        } = this.state;

        let searchTime = searchStore.search && searchStore.search.cur_time || '';
        let newOp = '' + searchTime;
        let oriOp = '' + this.oriOp;

        
        if (newOp != oriOp) {
            if (curSearchType.key == SEARCH_TYPE_REPORT.key) {
                this.setState({
                    orderReport: 'all_score'
                });
            }
        }
        this.oriOp = newOp;
    }

    async componentDidMount() {
        /** need reset */
        const { listStore } = this.props;
        const mailList1 = await getUserEmailList();
        if (mailList1 && mailList1.success) {
            if (mailList1 && mailList1.data && mailList1.data.length > 0){
                if (getQueryString(this.props.location.search,'search')){
                    // 需要搜索
                    this.setState({
                        loading: false
                    });
                    return;
                }
                const mailList = await getMails({ end_time: Number(moment().format('X')) * 1000, page_num: listStore.current, page_size: 10, prior: 'score', search_words: {}, involves: listStore.involves });
                if (!mailList || !mailList.success) {
                    this.setState({
                        loading: false
                    });
                    return notification.error({
                        message: '错误',
                        description: mailList ? mailList.message : ''
                    });
                }
                let num = mailList.data.total_count ? mailList.data.total_count : 0;
                listStore.totalCount = num;
                listStore.changeCount(num);
                listStore.setList(mailList.data.items);
                this.setState({
                    loading: false
                });
                if (!mailList.data.items.length) return;
                const id = mailList.data.items[0].id;
            }
          }
       
        /** need reset end */
        // const mailDetail = await getMailDetail({ id })
        // if (!mailDetail || !mailDetail.success) return notification.error({
        //   message: '错误',
        //   description: mailDetail.message
        // })
        // listStore.changeSelected(mailDetail.data)
    }

    // 加载更多
    async onLoadMore(page) {
        const { listStore, searchStore } = this.props;
        if (this.state.curSearchType.key != 'all') return;
        searchStore.filter.page_num = listStore.current + 1;
        const o = _.assign({}, searchStore.filter, searchStore.search);
        o.page_num = listStore.current + 1;
        o.involves = listStore.involves;
        o.prior = listStore.prior ? listStore.prior : 'score';
        let mailList = await getSearchMails({ ...o });
        if (!mailList) return;
        if (page * 10 >= mailList.data.total_count){
            listStore.setLoading(false);
        }
        let num = mailList.data.total_count ? mailList.data.total_count : 0;
        listStore.totalCount = num;
        listStore.changeCount(num);
        listStore.setList(listStore.mailList.concat(mailList.data.items));
        listStore.current = listStore.current + 1;
    }
    
    onChangeSearchTab() {
        // 切换tab
        const { searchStore } = this.props;
        // 暂存
        let oldValue = searchStore.searchValueFromUrl;
        // 加一个空格 为了避免不渲染问题
        searchStore.changeSearchValueFromUrl(' ');
        // 重置搜索框上的内容 避免被用户清掉切换tab
        searchStore.changeSearchValueFromUrl(oldValue);
        let {curSearchType = {}} = this.state;
        const { listStore } = this.props;
        listStore.setKey(curSearchType.key);
        if (this.state.curSearchType.key == 'all'){

            listStore.setLoading(true);
        } else {

            listStore.setLoading(false);
        }
    }

    render() {
        const {
            loading,
            curSearchType,
            filtersData,
            filtersReport,
            totalData,
            totalReport,
            orderReport,
        } = this.state;
        const { listStore, searchStore } = this.props;
        const emptyDefaultContent = (
            <div className="noResult">
                {/* <div className="mail-sort-action"></div> */}
                <div className="noResult" style={{ marginTop: '12rem' }}>
                    <img src={empty} className="icon noMail" />
                    <div className="re-input">邮件正在同步，请稍后。可点击<span onClick={this.reflashPage}>刷新</span>查看最新同步进度</div>
                </div>
            </div>
        );
        const emptySearchContent = (
            <div className="noResult">
                {/* <div className="mail-sort-action"></div> */}
                <div className="noResult" style={{ marginTop: '12rem' }}>
                    <img src={empty} className="icon noMail" />
                    <div className="re-input">暂无搜索结果，请重新输入</div>
                </div>
            </div>
        );
        const resultContent = (
            <Row gutter={8}>
                <Col span={24} style={{ paddingRight: '0px' }}>
                    {searchStore.isSearched && <p className="result-content">相关搜索<span className="result-number"> {listStore.totalCount} </span> 条</p>}
                    {/* <MailSortAction /> */}
                    <MailList dataset={listStore.mailList && listStore.mailList.slice()} current={listStore.current} onLoadMore={() => this.onLoadMore(listStore.current)} showLoadMore={true} total={listStore.totalCount} loading={listStore.loading}/>
                    <div className="mail-divider" />
                </Col>
                {/* <Col span={11} style= {{ paddingLeft : '0px' }}>
          <MailDetail dataset={listStore.selectedItem} />
        </Col> */}
            </Row>
        );
        // Loading 修正 <Icon type="loading" style={{ fontSize: 24 }} spin />

        // 邮件右侧信息
        const EmailSearchTabbarRightContent = (props) => {
            return props.isShow ? (
                <div className={'tabbar-right-content'}>
                    <p className={'tabbar-right-content-msg'}>
                        共找到相关结果<span>{props.total || 0}</span>个
                    </p>
                </div>
            ) : null;
        };

        // 研报右侧信息
        const ReportSearchTabbarRightContent = (props) => {
            return props.isShow ? (
                <div className={'tabbar-right-content'}>
                    <p className={'tabbar-right-content-msg'}>
                        共找到相关结果<span>{props.total || 0}</span>个
                    </p>
                    <Select defaultValue={props.defaultValue || 'all_score'}
                        size="small"
                        style={{ width: 85,outline: 'none',border: 'none' }}
                        onChange={(value) => {
                            props.onChange && props.onChange(value);
                        }}>

                        <SelectOption value={'all_score'} className="sort-select-option-item">综合排序</SelectOption>
                        <SelectOption value={'time_asc'} className="sort-select-option-item">日期升序</SelectOption>
                        <SelectOption value={'time_desc'} className="sort-select-option-item">日期降序</SelectOption>
                    </Select>
                </div>
            ) : null;
        };

        // 数据右侧信息
        const DataSearchTabbarRightContent = (props) => {
            return props.isShow ? (
                <div className={'tabbar-right-content'}>
                    <p className={'tabbar-right-content-msg'}>
                        共找到相关结果<span>{props.total || 0}</span>个
                    </p>
                </div>
            ) : null;
        };

        return (
            <div className="mail-list-results">
                <div className="content-loading" style={{ display: loading ? 'block' : 'none' }}>
                    <Loading />
                </div>
                <div style={{ display: !loading ? 'block' : 'none', overflow: 'hidden' }}>
                    {/* {listStore.mailList.length ?<MailSortAction />:<div className="kong"><div className="mail-sort-action"></div></div>} */}
                    
                    <SearchResultWrap
                        activeKey={curSearchType.key}
                        defaultActiveKey={SEARCH_TYPE_DATA.key}
                        tabs={[{
                            key: SEARCH_TYPE_ALL.key,
                            title: SEARCH_TYPE_ALL.title,
                            content: (
                                <React.Fragment>
                                    {/* 筛选条件 */}
                                    <MailSortAction />

                                    {/* 全部搜索结果 */}
                                    {
                                        listStore.mailList && listStore.mailList.length ? resultContent : (
                                            listStore.search_words ? emptySearchContent : emptyDefaultContent
                                        )
                                    }
                                </React.Fragment>
                            )
                        }, {
                            key: SEARCH_TYPE_REPORT.key,
                            title: SEARCH_TYPE_REPORT.title,
                            content: (
                                <React.Fragment>
                                    {/* 筛选条件 */}
                                    <FilterBar filters={[]}
                                        onChange={(filter, allFilters) => {
                                            this.setState({
                                                filtersReport: allFilters
                                            });
                                        }}/>

                                    {/* 研报搜索结果 */}
                                    <ReportSearchResult filters={filtersReport}
                                        order={orderReport}
                                        isShow={curSearchType.key == SEARCH_TYPE_REPORT.key}
                                        curSearchType={curSearchType}
                                        onLoadFail={(reload)=> {
                                            let state = {
                                                totalReport: 0,
                                            };
                                            this.setState(state);
                                        }}
                                        onLoadSuccess={(resp, reload) => {
                                            let {data = {}} = resp;
                                            let state = {
                                                totalReport: data.total_count || 0,
                                            };
                                            this.setState(state);
                                        }}/>
                                </React.Fragment>
                            )
                        }, {
                            key: SEARCH_TYPE_DATA.key,
                            title: SEARCH_TYPE_DATA.title,
                            content: (
                                <React.Fragment>
                                    {/* 筛选条件 */}
                                    <FilterBar filters={[]}
                                        onChange={(filter, allFilters) => {
                                            this.setState({
                                                filtersData: allFilters
                                            });
                                        }}/>

                                    {/* 数据搜索结果 */}
                                    <DataSearchResult filters={filtersData}
                                        isShow={curSearchType.key == SEARCH_TYPE_DATA.key}
                                        curSearchType={curSearchType}
                                        onLoadFail={(reload)=> {
                                            let state = {
                                                totalData: 0,
                                            };
                                            this.setState(state);
                                        }}
                                        onLoadSuccess={(resp, reload) => {
                                            let {data = {}} = resp;
                                            let state = {
                                                totalData: data.total_count || 0,
                                            };
                                            this.setState(state);
                                        }}/>
                                </React.Fragment>
                            )
                        }]}
                        tabBarExtraContent={
                            <React.Fragment>
                                <EmailSearchTabbarRightContent
                                    isShow={false && curSearchType.key == SEARCH_TYPE_ALL.key}
                                    total={listStore.totalCount}/>

                                <ReportSearchTabbarRightContent
                                    isShow={curSearchType.key == SEARCH_TYPE_REPORT.key}
                                    total={totalReport}
                                    defaultValue={orderReport}
                                    onChange={(value) => {
                                        this.setState({
                                            orderReport: value
                                        });
                                    }} />

                                <DataSearchTabbarRightContent
                                    isShow={curSearchType.key == SEARCH_TYPE_DATA.key}
                                    total={totalData} />
                            </React.Fragment>
                        }
                        onChangeTab={(key, [tab]) => {
                            this.setState({
                                curSearchType: {
                                    key: tab.key,
                                    title: tab.title
                                }
                            }, this.onChangeSearchTab);
                            
                        }}/>
                </div>
            </div>
        );
    }
}

export default MailResults;