import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {Row, Col} from 'antd';
import { withRouter } from 'react-router-dom';
import { inject, observer } from 'mobx-react';
import ask from '../../../../../../lib/ask';
import ResultTable from '../DataTable';
import TableCard from '../TableCard';
import BottomLoading from './../BottomLoading/';
import queryString from 'query-string';
import './index.scss';
import EmptyView from '../EmptyView';

@withRouter
@inject('searchStore')
@observer
class TableList extends Component {
    constructor(props) {
        super(props);

        this.state = {
            // 当前Table搜索列表
            resultList: [],
            // 每页条数
            pageSize: props.pageSize || 16,
            // 当前页码
            curPage: 0,
            // 是否在拉取数据
            loading: true,
            // 是否可以加载更多
            loadMore: true,
            // 附加的参数
            params: {},
            //table数据
            data: {},
            //后台返回的关键字
            keywordServer:''
        };

        // this.handleRef = this.handleRef.bind(this);
        this.loadData = this.loadData.bind(this);
        this.onScroll = this.onScroll.bind(this);
    }

    componentDidUpdate(prevProps, prevState) {
        // 更新搜索条件
        // let {
        //     filters = [],
        //     searchStore = {},
        //     location = {}
        // } = nextProps;
        // filters = filters.map((item) => {
        //     return item.key + '' + item.value;
        // });
        //
        // let searchTime = location.search || "";
        // let newOp = '' + searchTime;
        // let oriOp = '' + this.oriOp;
        // if (newOp != oriOp) {
        if (prevProps.location !== this.props.location) {
            this.loadData(true);
        }
        // this.oriOp = newOp;
    }

    componentDidMount() {
        // let {
        //     searchStore = {}
        // let searchTime = searchStore.search && searchStore.search.cur_time || '';
        // this.oriOp = '' + searchTime;
        // } = this.props;
        this.bindScrollLoad();
        this.loadData(true);
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

    loadData(reload) {
        if (reload || !this.endTime) {
            this.endTime = new Date().getTime();
        }
        if (this.loading) { return; }
        this.loading = true;
        let {keyword, order, selected, start_time, end_time} = queryString.parse(this.props.location.search)
        let {
            searchStore,
            onLoadSuccess,
            onLoadFail,
            requireFilters,
        } = this.props;
        let {
            curPage,
            pageSize
        } = this.state;
        // let search_word = getQueryString(window.location.search, 'keyword');
        this.setState({loading: true, [reload ? 'resultList' : '_']: []});
        if (window.search_log){
            window.search_log.request_id = window.guid();
        }
        ask('TableModuleResult', { params: {
            keyword: keyword,
            limit: pageSize,
            page: 'comprehensive_table_search' ,
            prior: order,
            input_from: 'direct',
            offset: pageSize * curPage,
            selected,
            start_time,
            end_time
            }}).then((resp) => {
            let {data = {}} = resp;
            // console.log('数据图表',data);
            let {resultList = []} = this.state;
            let result = Object.assign([], (reload ? [] : resultList).concat(data.items || []));
            this.loading = false;
            this.setState({
                keywordServer:data.keyword || keyword,
                data: data,
                loading: false,
                resultList: result,
                curPage: reload ? 1 : curPage + 1,
                loadMore: (data.items || []).length >= pageSize
            });
            requireFilters(data.option, data.selected)
            onLoadSuccess && onLoadSuccess(resp);
        }).catch(() => {
            this.loading = false;
            this.setState({loading: false, loadMore: false});
            onLoadFail && onLoadFail();
        });
    }

    // handleRef(e) {
    //     let {
    //         handleRef
    //     } = this.props;
    //
    //     this.refResultList = e;
    //
    //     handleRef && handleRef(e);
    // }

    onScroll(event = {}) {
        const { target: container = {} } = event;
        const { scrollingElement: target = {} } = container;
        // 计算滚动距离差
        let diff = target.scrollTop - (this._scrollTop || 0);
        let {scrollLoadBox, scrollLoadDistance, order} = this.props;
        let {scrollHeight, clientHeight, scrollTop, } = target;
        let {loadMore} = this.state;

        if (Math.abs(diff) >= 20) {
            // 每20px打点一次
            this._scrollTop = target.scrollTop;
        }
        // 计算是否需要触底加载
        if (diff > 0
            && scrollHeight <= clientHeight + scrollTop + scrollLoadDistance && loadMore) {

            // 距底300px 开启自动加载
            this.loadData(false, {
                order: order
            });
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
        let {
            resultList,
            params,
            data,
            loading,
            loadMore
        } = this.state;
        let {
            className
        } = this.props;

        return (
            <div ref={this.handleRef} className={`abc-data-search-results ${className}`}>
                <div className={'result-wrap'}>
                    <Row gutter={20}>
                        {/*<ResultTable data={data}/>*/}
                        {
                            resultList.map((item, index) => {
                                return (
                                    <Col span={12} key={index} style={{marginBottom: 20}}>
                                        <TableCard data={item} keyword={this.state.keywordServer}/>
                                    </Col>
                                );
                            })
                        }
                    </Row>
                </div>

                {!(!loading && !resultList.length) ?
                    (<BottomLoading status={loading ? 'loading' : loadMore ? 'load_more' : 'loaded'}
                        onClick={(status) => {
                            if (status == 'load_more') {
                                this.loadData(false);
                            }
                        }}/>) : <EmptyView />}
            </div>
        );
    }
}

TableList.defaultProps = {
    language: 'zh-CN',
    pageSize: 16,
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

TableList.propTypes = {
    // 语言环境
    language: PropTypes.string,
    // 附加类名
    className: PropTypes.string,
    // ref回调
    handleRef: PropTypes.func,
    // 分页条数
    pageSize: PropTypes.number,
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

export default TableList;