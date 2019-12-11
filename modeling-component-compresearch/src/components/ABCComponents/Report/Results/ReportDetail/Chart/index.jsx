import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {
    Row, Col
} from 'antd';
import ask from '../../../../../../lib/ask';
import { withRouter } from 'react-router-dom';
import classnames from 'classnames';
import ChartCard from './../../ChartCard/';
import FullScreenChart from './../../FullScreenChart/';
import BottomLoading from './../../BottomLoading/';

import { utils } from '../../../../../../lib/until';
import './index.scss';
import EmptyView from '../../EmptyView';

@withRouter
class ReportChart extends Component {
    constructor(props) {
        super(props);

        this.state = {
            // 图表列表
            resultList: [],
            // 是否显示全屏图表
            isShowFullScreen: false,
            // 当前全屏的图表
            curFullScreenChart: {},
            // 是否在加载
            loading: true,
            // 每页条数
            pageSize: props.pageSize || 21,
            // 当前页码
            curPage: 1,
            // 是否可以加载更多
            loadMore: true,
        };

        this.loadData = this.loadData.bind(this);
        this.onScroll = this.onScroll.bind(this);
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.isShow && !this.loaded) {
            this.loaded = true;

            let {
                match
            } = nextProps;
    
            let {
                params = {}
            } = match;

            this.loadData(params.fileId);
        }
    }

    componentDidMount() {
        let {
            isShow,
            match
        } = this.props;

        let {
            params = {}
        } = match;

        this.bindScrollLoad();

        if (isShow && !this.loaded) {
            this.loaded = true;

            this.loadData(params.fileId);
        }
    }

    loadData(report_id) {
        let {
            fileInfo
        } = this.props;
        let {
            curPage,
            pageSize,
        } = this.state;

        if (this.loading) { return; }
        this.loading = true;

        this.setState({loading: true});
        ask({
            baseURL: '',
            url: '/search-api/report/charts',
            method: 'get',
            params: {
                report_id: report_id || fileInfo.id,
                page_num: curPage,
                page_size: pageSize
            },
        }).then((resp) => {
            let {data = []} = resp;
            let {resultList = []} = this.state;
            let result = Object.assign([], resultList.concat(data || []));
            this.loading = false;

            this.setState({
                loading: false,
                resultList: result,
                curPage: curPage + 1,
                loadMore: (data || []).length >= pageSize
            });
        }).catch(() => {
            this.loading = false;
            this.setState({loading: false, loadMore: false});
        });
    }

    onScroll(event = {}) {
        const { target = {} } = event;
        // 计算滚动距离差
        let diff = target.scrollTop - (this._scrollTop || 0);
        let {scrollLoadBox, scrollLoadDistance, isShow} = this.props;
        if (!isShow) {
            return;
        }
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
            this.loadData(false);
        }
    }

    bindScrollLoad() {
        let {
            scrollLoad,
        } = this.props;

        if (!scrollLoad) {
            return;
        }
        this.scrollLoadBox.addEventListener('scroll', this.onScroll);
    }

    unBindScrollLoad() {
        let {
            scrollLoad,
        } = this.props;
        
        if (!scrollLoad) {
            return;
        }
        // 移除滚动监听事件
        this.scrollLoadBox.removeEventListener('scroll', this.onScroll);
    }

    componentWillUnmount() {
        this.unBindScrollLoad();
    }

    render() {
        let {
            className,
            fileInfo,
            match
        } = this.props;
        let {
            resultList,
            isShowFullScreen,
            curFullScreenChart,
            loading,
            loadMore
        } = this.state;
        let {
            params
        } = match;

        return (
            <div className={`abc-report-detail-chart ${className}`} ref={e => this.scrollLoadBox = e}>
                <div className={'base-info-box'}>
                    <Row>
                        {
                            resultList.length && resultList.map((item, index) => {
                                let confidence = utils.getObjType(item.data) == 'object' ? 1 : 0;
                                return (
                                    <Col span={8} key={index}>
                                        <ChartCard
                                            type={'chart'}
                                            hideList={['from','type','author']}
                                            // 卡片配置
                                            config={{
                                                image_title: item.title || '',
                                                title: fileInfo.title || item.title || '',
                                                chart_type: 'CHART',
                                                hideSource: true,
                                                image_id: item.id,
                                                id: params.fileId,
                                                file_id: params.fileId,
                                                image_url: item.url || '',
                                                confidence: confidence,
                                                chart_data: confidence > 0 ? JSON.stringify(item.data || {}) : '{}'
                                            }}
                                            size={{
                                                width: 350,
                                                height: 300
                                            }}
                                            // 图表被点击
                                            onClickChart={(chart) => {
                                                if (confidence < 1) {
                                                    return;
                                                }
                                                this.setState({
                                                    isShowFullScreen: true,
                                                    curFullScreenChart: chart.config
                                                });
                                            }} />
                                    </Col>
                                );
                            })
                        }

                        {!(!loading && !resultList.length) ?
                        (<BottomLoading status={loading ? 'loading' : loadMore ? 'load_more' : 'loaded'}
                            onClick={(status) => {
                                if (status == 'load_more') {
                                    this.loadData();
                                }
                            }}/>) : <EmptyView />}
                    </Row>
                </div>

                <FullScreenChart isShow={isShowFullScreen}
                    config={curFullScreenChart}
                    onClose={() => {
                        this.setState({
                            isShowFullScreen: false
                        });
                    }}/>
            </div>
        );
    }
}

ReportChart.defaultProps = {
    language: 'zh-CN',
    className: '',
    isShow: false,
    fileInfo: {},
    scrollLoad: true,
    scrollLoadDistance: 300,
    scrollLoadBox: window.document,
};

ReportChart.propTypes = {
    // 语言环境
    language: PropTypes.string,
    // 附加类名
    className: PropTypes.string,
    // 是否可见
    isShow: PropTypes.bool,
    // 文件基本信息
    fileInfo: PropTypes.object,
    // 是否启用滚动加载
    scrollLoad: PropTypes.bool,
    // 滚动加载距离
    scrollLoadDistance: PropTypes.number,
    // 滚动加载监听容器
    scrollLoadBox: PropTypes.object,
};

export default ReportChart;