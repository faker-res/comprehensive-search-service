import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withRouter } from 'react-router-dom';
import { inject, observer } from 'mobx-react';
import ask from '../../../../../lib/ask';
import ReportItem from './../ReportItem/';
import BottomLoading from './../BottomLoading/';
import EmptyView from './../EmptyView/';
import queryString from 'query-string';
import compact from 'lodash/compact';
import './index.scss';
@withRouter
@inject('searchStore')
@observer
class ReportSearchResults extends Component {
    constructor(props) {
        super(props);

        this.state = {
            total_count:0,//搜索数据总条数
            total_count1:0,//外部研报总数
            total_count2:0,//主站外部研报总数
            //是否加载主站外部研报
            loadAnalyst:false,
            // 当前研报搜索列表
            resultList: [],
            // 每页条数
            pageSize: props.pageSize || 20,
            // 当前页码
            curPage: 1,
            // 是否在拉取数据
            loading: true,
            // 是否可以加载更多
            loadMore: true,
            // 附加的参数
            params: {
                // 需要高亮的
                keyword: props.keyword || ''
            }
        };

        this.handleRef = this.handleRef.bind(this);
        this.loadData = this.loadData.bind(this);
        this.onScroll = this.onScroll.bind(this);
    }

    componentDidUpdate(prevProps, prevState) {
        // const oQueryParams = queryString.parse(prevProps.location.search);
        const nQueryParams = queryString.parse(this.props.location.search);
        // if (nQueryParams.keyword !== oQueryParams.keyword
        //     || nQueryParams.filters !== oQueryParams.filters
        //     || nQueryParams.order !== oQueryParams.order
        //     || nQueryParams.industry !== oQueryParams.industry
        //     || nQueryParams.report !== oQueryParams.report
        //     || nQueryParams.company !== oQueryParams.company) {
        if (this.props.location !== prevProps.location){
            if (typeof prevProps.isActive !== 'undefined') {
                if (prevProps.isActive) {
                    this.loadData(false, nQueryParams);
                }
            } else {
                this.loadData(true, nQueryParams);
            }
        }
    }

    componentDidMount() {
        this.bindScrollLoad();
        const nQueryParams = queryString.parse(this.props.location.search);
        this.loadData(true, nQueryParams);
    }

    // 获取筛选数据
    getReportFilterList = ({keyword, selected, params}) => {
        ask('getReportFilterData', {
            data: {
                keyword: keyword,
                selected: selected,
                ...params
            }
        }).then(res => {
            if (res.code === 200 && res.data) {
                this.props.requireFilters(res.data.option, res.data.selected)
            }
        }).catch(error => {
            console.log(error);
        });
    }

    /**
     * 获取请求form对象
     */
    getSearchParamsObj() {
        let PostForm = function() {};
        let headers = {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'};

        PostForm.prototype.append = function(key, val) {
            this[key] = val;
        };
        PostForm.prototype.get = function(key) {
            return this[key];
        };
        PostForm.prototype.has = function(key) {
            return this.hasOwnProperty(key);
        };

        return {
            form: new PostForm(),
            headers,
        };
    }

    getOrder(order = 'all_score') {
        switch (order) {
            case 'time_asc': return 'timeAsc';break;
            case 'time_desc': return 'timeDesc';break;
            default: return 'compositive';
        }
    }

    // selected
    selectedParse = (selected) => {
        const params = {}
        if(selected) {
            let arr1 = selected.split(';')
            arr1.forEach(item => {
                const arr2 = item.split(',')
                if(params[arr2[0]]) {
                    params[arr2[0]].push(arr2[1])
                } else {
                    params[arr2[0]] = []
                    params[arr2[0]].push(arr2[1])
                }
            })
        }
        return params
    }
    
    loadData(reload, {keyword, filters, order, industry, report, company, selected, start_time, end_time}) {
        let {
            isActive
        } = this.props;

        if (typeof isActive !== 'undefined' && !isActive) {
            return;
        }
        if (reload || !this.endTime) {
            this.endTime = new Date().getTime();
        }
        if (this.loading) { return; }
        this.loading = true;

        let {
            onLoadSuccess,
            onLoadFail,
        } = this.props;
        let {
            curPage,
            pageSize
        } = this.state;
        
        let search_word = {};
        if (keyword) search_word.keyword = [keyword];
        if (industry) search_word.industry = industry.split('|');
        if (report) search_word.report = report.split('|');
        if (company) search_word.company = company.split('|');
        if (window.search_log){
            window.search_log.request_id = window.guid();
        }
        this.setState({loading: true, [reload ? 'resultList' : '_']: []});
        // 获取研报筛选面板数据
        // 获取解析后的selected数据
        let params = {}
        if (selected) {
            params = this.selectedParse(selected)
        }
        this.getReportFilterList({keyword, selected, params})
        ask('getReportData', {
            data:{
                pageIndex: reload ? 1 : curPage,
                pageSize: pageSize,
                keyword: keyword,
                selected: selected,
                sort: this.getOrder(order), //排序
                ...params
                // keyword_filter:search_word.keyword,
                // page_num: reload ? 0 : curPage,
                // page_size: pageSize,
                // search_word: search_word,
                // involves: compact(filters && filters.split('|') || []),
                // prior: this.getOrder(order), //排序
                // search_log: window.search_log 
            }
        }).then((resp) => {
            let {data = {}} = resp;
            let {resultList = []} = this.state;
            this.setState({ total_count1:data.numFound })
            // 返回总条数
            onLoadSuccess && onLoadSuccess(resp, reload, data.numFound);
            // 合并结果数据
            let result = Object.assign([], (reload ? [] : resultList).concat(data['data'] || []));
            this.loading = false;
            
            this.setState({
                loading: false,
                resultList: result,
                curPage: reload ? 1 : curPage + 1,
                loadMore: (data.data || []).length >= pageSize, // 是否加载更多判断
                // loadAnalyst:(data.data || []).length < pageSize
            });
            // console.log('开始加载更多model研报',(data.item || []).length >= pageSize);   
            // if((data.item || []).length < pageSize){
            // this.loadReport(reload,{keyword, filters, order, industry, report, company, selected, start_time, end_time})
            // }
        }).catch((error) => {
            console.log('error',error);
            this.loading = false;
            this.setState({loading: false, loadMore: false});
            onLoadFail && onLoadFail(reload);
        });
    }

    handleRef(e) {
        let { handleRef } = this.props;
        this.refResultList = e;
        handleRef && handleRef(e);
    }

    onScroll(event = {}) {
        // console.log(event)
        let {
            scrollLoadBox, scrollLoadDistance, order,
            filters = [],
        } = this.props;
        let filtersArr = [];
        for (let i in filters) {
            if (filters[i].value){
                filtersArr.push(filters[i].key);
            }
        }
        const { target: container = {} } = event;
        const { scrollingElement: target = {} } = container;
        // 计算滚动距离差
        let diff = target.scrollTop - (this._scrollTop || 0);
        let {scrollHeight, clientHeight, scrollTop, } = target;
        let {loadMore} = this.state;
        
        if (Math.abs(diff) >= 20) {
            // console.log(diff)
            // 每20px打点一次
            this._scrollTop = target.scrollTop;
        }
        // 计算是否需要触底加载
        // 距底300px 开启自动加载
        if (diff > 0
            && scrollHeight <= clientHeight + scrollTop + scrollLoadDistance && loadMore )  {
            const nQueryParams = queryString.parse(this.props.location.search);
            this.loadData(false, nQueryParams);
        }
        // else if(diff > 0 && scrollHeight <= clientHeight + scrollTop + scrollLoadDistance && this.state.loadAnalyst){
        //     const nQueryParams = queryString.parse(this.props.location.search);
        //     this.loadData(false, nQueryParams);
        // }
    }

    bindScrollLoad() {
        let {
            scrollLoad,
            scrollLoadBox
        } = this.props;

        if (!scrollLoad) {
            return;
        }
        scrollLoadBox.addEventListener('scroll', this.onScroll);
    }

    unBindScrollLoad() {
        let {
            scrollLoad,
            scrollLoadBox
        } = this.props;
        
        if (!scrollLoad) {
            return;
        }
        // 移除滚动监听事件
        scrollLoadBox.removeEventListener('scroll', this.onScroll);
    }

    componentWillUnmount() {
        this.unBindScrollLoad();
    }

    render() {
        const {
            resultList,
            loading,
            loadMore
        } = this.state;
        const {
            className,
            isActive
        } = this.props;
        const nQueryParams = queryString.parse(this.props.location.search);
        return (
            <div ref={this.handleRef} className={`abc-report-search-results ${className}`}>
                {
                    resultList.map((item, index) => {
                        return <ReportItem key={index} data={item} highlight={nQueryParams.keyword}  />;
                    })
                }

                {!(!loading && !resultList.length) ? <BottomLoading status={loading ? 'loading' : loadMore ? 'load_more' : 'loaded'}
                    onClick={(status) => {
                        if (status == 'load_more') {
                            this.loadData(false, nQueryParams);
                        }
                    }}/> : <EmptyView />}
            </div>
        );
    }
}

ReportSearchResults.defaultProps = {
    language: 'zh-CN',
    pageSize: 20,
    className: '',
    handleRef: () => {},
    filters: [],
    onLoadSuccess: () => {},
    onLoadFail: () => {},
    requireFilters:()=>{},
    order: '',
    scrollLoad: true,
    scrollLoadDistance: 300,
    scrollLoadBox: window.document,
};

ReportSearchResults.propTypes = {
    // 语言环境
    language: PropTypes.string,
    // 分页条数
    pageSize: PropTypes.number,
    // 附加类名
    className: PropTypes.string,
    // ref回调
    handleRef: PropTypes.func,
    // 二级过滤条件
    filters: PropTypes.array,
    // 加载成功
    onLoadSuccess: PropTypes.func,
    // 加载失败
    onLoadFail: PropTypes.func,
    // 排序参数
    order: PropTypes.string,
    // 是否启用滚动加载
    scrollLoad: PropTypes.bool,
    // 滚动加载距离
    scrollLoadDistance: PropTypes.number,
    // 滚动加载监听容器
    scrollLoadBox: PropTypes.object,
    //回调筛选数据
    requireFilters:PropTypes.func,

};

export default ReportSearchResults;