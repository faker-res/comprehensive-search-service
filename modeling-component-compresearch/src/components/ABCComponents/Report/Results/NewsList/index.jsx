import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withRouter } from 'react-router-dom';
import { inject, observer } from 'mobx-react';
import ask from '../../../../../lib/ask';
import NewsItem from './../NewsItem/';
import BottomLoading from './../BottomLoading/';
import EmptyView from './../EmptyView/';
import queryString from 'query-string';
import compact from 'lodash/compact';
import { newsOrigin } from '../../../../../lib/constants'
import './index.scss';

import { mock_data } from './test';

@withRouter
@inject('searchStore')
@observer
class NewsList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            tags:this.props.tags,
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
        this.onScroll = this.onScroll.bind(this);
    }

    async componentDidUpdate(prevProps, prevState) {
        // const oQueryParams = queryString.parse(prevProps.location.search);
        const nQueryParams = queryString.parse(this.props.location.search);
        // if (nQueryParams.keyword !== oQueryParams.keyword
        //     || nQueryParams.filters !== oQueryParams.filters
        //     || nQueryParams.order !== oQueryParams.order
        //     || nQueryParams.industry !== oQueryParams.industry
        //     || nQueryParams.report !== oQueryParams.report
        //     || nQueryParams.company !== oQueryParams.company) {
        if (this.props.location !== prevProps.location){
            await this.setState({
                curPage: 1,
            })
            if (typeof prevProps.isActive !== 'undefined') {
                if (prevProps.isActive) {
                    await this.loadData(true, nQueryParams);
                }
            } else {
                await this.loadData(true, nQueryParams);
            }
        }
    }

    componentDidMount() {
        this.bindScrollLoad();
        const nQueryParams = queryString.parse(this.props.location.search);
        this.loadData(true, nQueryParams);
        console.log('tags',this.state.tags);
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

    getOrder(order = 'score') {
        switch (order) {
            case 'time_asc': return 2;break;
            case 'time_desc': return 1;break;
            default: return 0;
        }
    }

    loadData(reload, {keyword, filters, order, industry, report, company, selected, start_time, end_time}) {
        let {
            isActive,
            requireFilters,
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
            loadInternalData,
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
        ask('NewsInfo', { params: {
            channel_id: 0,
            sub_channel_id: -1,
            limit: 20,
            offset: 20 * (curPage - 1),
            page: 'news_search_home',
            keyword: keyword,
            selected,
            prior: order,
            // limit: 20,
            // page: 'comprehensive_news_search' ,
            // input_from: 'direct',
            // offset: 20 * curPage,
            // channel: '',
            // start_time,
            // end_time
        }})
        .then((resp) => {
            let {data = {}} = resp;
            let {resultList = []} = this.state;
            let result = Object.assign([], (reload ? [] : resultList).concat(data.item || []));
            this.loading = false;
            this.setState({
                loading: false,
                resultList: result,
                curPage:  curPage + 1,
                loadMore: (data.item || []).length >= pageSize
            });
            console.log('研报数据',this.state.resultList);
            requireFilters(data.option, data.selected)
            onLoadSuccess && onLoadSuccess(resp, reload);
        }).catch(() => {
            this.loading = false;
            this.setState({loading: false, loadMore: false});

            onLoadFail && onLoadFail(reload);
        });
    }

    handleRef(e) {
        let {
            handleRef
        } = this.props;

        this.refResultList = e;

        handleRef && handleRef(e);
    }

    onScroll(event = {}) {
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
            // 每20px打点一次
            this._scrollTop = target.scrollTop;
        }
        // 计算是否需要触底加载
        // 距底300px 开启自动加载
        if (diff > 0
            && scrollHeight <= clientHeight + scrollTop + scrollLoadDistance && loadMore) {
            const nQueryParams = queryString.parse(this.props.location.search);

            this.loadData(false, nQueryParams);
        }
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
            params,
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
                        return <NewsItem key={index} data={item} highlight={nQueryParams} onClick={(data = {}) => {
                            window.open(`${newsOrigin}/search/detail/${data.id}`);
                        }}/>;
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

NewsList.defaultProps = {
    language: 'zh-CN',
    pageSize: 20,
    className: '',
    handleRef: () => {},
    filters: [],
    onLoadSuccess: () => {},
    onLoadFail: () => {},
    order: '',
    scrollLoad: true,
    scrollLoadDistance: 300,
    scrollLoadBox: window.document,
    requireFilters: (option, selected) => {}
};

NewsList.propTypes = {
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
    //筛选条件获取
    requireFilters: PropTypes.func
};

export default NewsList;