/**
 * 图表卡片
 * @author lzhang
 * @description 图表卡片组件
 */
import React from 'react';
import {withRouter} from 'react-router-dom';
import PropTypes from 'prop-types';
import moment from 'moment';

// components
import ChartBox from './../ChartItem/';
import Template from '../../Template';

import { utils } from '../../../../../lib/until';

import './index.scss';

// 公告
const NOTICE_REPORT = 'notice-report';
// 研报
const STOCK_REPORT  = 'stock-report';
// 过滤文本
const ChartTableTitleFilterMap = {
    '联Ta系ble我_A们d dress': 1,
    '投资评级定义': 1,
    '半年报点评': 1,
    '投资评级说明': 1,
    '股票投资评级': 1
};

class ChartCard extends React.Component {
    static propTypes = {
        language: PropTypes.string,
        // 是否开启延迟加载
        lazyLoad: PropTypes.bool,
        // 父级滚动容器滑动距离(开启lazyLoad后有效)
        offsetTop: PropTypes.number,
        // 图表被点击
        onClickChart: PropTypes.func,
        autoCollapsed: PropTypes.bool
    }

    static defaultProps = {
        language: 'zh',
        lazyLoad: false,
        offsetTop: 0,
        onClickChart: () => {},
        autoCollapsed: false
    }

    constructor(props) {
        super(props);

        this.handleResize = this.handleResize.bind(this);
        let {
            config,
            // 指定size后不再自动计算尺寸
            size,
            scale = 1 / 1,
            type = 'chart',
            keyword = '',
            lazyLoad
        } = props;

        let {
            box_size = {}
        } = this.generateChartConfig({config, scale, type, size});

        this.state = {
            // 卡片类型(table、chart)
            card_type: type,
            // 图表卡片配置
            config: config,
            // 图表的缩放比例
            scale: scale,
            // 图表容器尺寸
            box_size: box_size,
            // 当前是否正在收藏
            favoriting: false,
            // 卡片标题
            card_title: type === 'table' ? config.table_title : config.image_title,
            // 卡片是否被收藏
            is_favorite: type === 'table' ? config.is_favorite : config.is_favorite,
            // 高亮关键词
            keyword: keyword,
            // 来源信息
            source: type === 'table' ? config.title : config.title,
            // 公司信息
            company: type === 'table' ? config.company : config.company,
            // 行业信息
            industry: type === 'table' ? config.type : config.type,
            // 卡片是否可见
            visible: !lazyLoad
        };
    }

    getBoxSize(scale = 1.0) {
        let client_width = document.body.clientWidth;

        return {
            width: client_width,
            height: client_width / scale
        };
    }

    getContentSize(box_size) {
        // 获取主题内容的尺寸
        return {
            width: box_size.width - 32,
            height: box_size.height - 32 - (48 + 42)
        };
    }

    generateChartConfig({config = {}, scale = 1.0, type = 'chart', size}) {
        let box_size = size || this.getBoxSize(scale);
        let result = Object.assign({}, config);
        
        return {
            config: result,
            box_size: {
                width: box_size.width,
                height: box_size.height
            }
        };
    }

    handleResize(e) {
        let {
            box_size = {}
        } = this.generateChartConfig({
            config: this.state.config,
            scale: this.state.scale,
            type: this.state.chart_type,
            size: this.props.size
        });
        
        this.setState({
            box_size: box_size
        });
    }

    bindResizeEvent() {
        window.addEventListener('resize', this.handleResize);
    }

    unbindResizeEvent() {
        window.removeEventListener('resize', this.handleResize);
    }

    isCardVisible(offsetTop) {
        // 视图窗口高度
        let client_height = document.body.clientHeight;

        if (this.card && !(this.card.offsetTop + this.state.box_size.height < offsetTop
            || this.card.offsetTop > offsetTop + client_height)) {

            // 卡片不可见(卡片未进入视口或者卡片滑出视口)
            if (!this.state.visible) {
                this.setState({
                    visible: true
                });
            }
        }
    }

    checkLink(config = {}) {
        // 图表跳转 stock-report/google (source_url[优先] thumbnailLink)
        // 表格跳转 hb开头的是研报 jc开头的是公告

        // 文件ID
        let result = {
            pathname: '',
            search: '',
            file_id: '',
            file_title: encodeURIComponent(utils.removeDomOfText(config.title) || ''),
            file_type: '',
            file_time: config.time || 0
        };

        if (config.table_columnCount > 0) {
            // 表格类型
            if (config.table_ori === '3' || config.table_ori == '4') {
                // 3: 数据库生产 4: 购买的Excel => 不支持跳转
                return result;
            } else if (config.table_ori === '2') {
                // 2: 网页表格 => 根据table_source跳转
                result.pathname = config.table_source || '';
            } else if (config.table_ori === '1') {
                // 1: 解析表格 => 根据table_source确定类型 file_id = src_id
                result.file_id   = config.src_id || '';
                result.file_type = /^hb_/.test(config.table_source) ? STOCK_REPORT : NOTICE_REPORT;
                result.pathname  = `/report-detail/article/${result.file_id}`;
                result.search    = `?id=${result.file_id}&type=${result.file_type}&title=${result.file_title}&time=${result.file_time}`;
            } else {
                return result;
            }
        } else if (!!(config.chart_data || '').trim() && config.confidence >= 0.5) {
            console.log(config.chart_data);
            // 图表类型(研报)
            result.file_type = STOCK_REPORT;
            result.file_id   = config.file_id || '';
            result.pathname  = `/report-detail/article/${result.file_id}`;
            result.search    = `?id=${result.file_id}&type=${result.file_type}&title=${result.file_title}&time=${result.file_time}`;
        } else {
            if ((config.image_id || '').slice(0, 2) === 'gg' && config.source_url) {
                // 图片类型
                // 来自google
                result.pathname = config.source_url || config.thumbnailLink;
            } else {
                // 图表类型（默认）
                result.file_type = STOCK_REPORT;
                result.file_id   = config.file_id || '';
                result.pathname  = `/report-detail/article/${result.file_id}`;
                result.search    = `?id=${result.file_id}&type=${result.file_type}&title=${result.file_title}&time=${result.file_time}`;
            }
        }

        return result;
    }
    linkToFileViewer({config = {}}) {
        
        let {pathname, search, file_id, file_title, file_type, file_time} = this.checkLink(config);

        if (!(pathname || '').trim()) {
            return;
        }

        window.open(pathname + search);
    }

    componentDidMount() {
        // 绑定监听事件
        this.bindResizeEvent();

        if (this.props.lazyLoad) {
            // 判断当前卡片是否可见
            this.isCardVisible(this.props.offsetTop);
        }
    }

    componentWillUnmount() {
        // 移除监听事件
        this.unbindResizeEvent();
    }

    componentWillReceiveProps(nextProps, nextState) {
        if (nextProps.lazyLoad && nextProps.offsetTop !== this.props.offsetTop) {

            // 监听父级容器的offsetTop属性，计算当前卡片是否处于可视区域
            this.isCardVisible(nextProps.offsetTop);
        } else {
            let {
                box_size = {}
            } = this.generateChartConfig({
                config: nextProps.config,
                scale: this.state.scale,
                type: nextProps.type,
                size: nextProps.size
            });
            
            let cfg = nextProps.config || {};

            this.setState({
                card_type: nextProps.type,
                config: cfg,
                box_size: box_size,
                // 卡片标题
                card_title: nextProps.type === 'table' ? cfg.table_title : cfg.image_title,
                // 卡片是否被收藏
                is_favorite: nextProps.type === 'table' ? cfg.is_favorite : cfg.is_favorite,
                // 来源信息
                source: nextProps.type === 'table' ? cfg.title : cfg.title,
                // 公司信息
                company: nextProps.type === 'table' ? cfg.company : cfg.company,
                // 行业信息
                industry: nextProps.type === 'table' ? cfg.type : cfg.type,
            });
        }
    }

    render() {
        let {
            // 卡片标题
            card_title: cardTitle = '',
            // 搜索关键词
            keyword = '',
            // 是否可见
            visible = false,
            // 卡片类型
            card_type: chartType = '',
            // 卡片尺寸
            box_size: boxSize = {},
            // 卡片配置
            config = {},
        } = this.state;

        let // 公司
            company = '',
            // 行业
            industry = '',
            // 类别
            type = '',
            // 作者
            author = '',
            // 来源
            source = '';
        let {pathname, file_title} = this.checkLink(config) || {};

        if (chartType === 'chart') {
            company  = config.company || '';
            industry = config.industry || '';
            type     = config.type   || '';
            author   = config.author || '';
            source   = config.source_name || '';
        } else {
            company  = config.company || '';
            industry = config.industry || '';
            type     = config.type   || '';
            author   = config.author || '';
            source   = config.source_name || '';
        }
        
        let {
            requestId,
            // 是否需要延迟加载
            lazyLoad = false,
            // 表格是否需要自动收起
            autoCollapsed = false,
            size: chartSize
        } = this.props;

        if (chartType === 'table') {
            // 针对Table类型的卡片展示效果做处理
            // （table_data中小于3行不显示，然后行和列的相乘再相加得到数值小于表格数 就是异常的）
            let flag = false;
            let cfg  = config;

            // 过滤掉小于3行的表，@xielei的需求
            flag = cfg.table_rowCount < 3;

            if (cfg.table_title !== null && cfg.table_title !== '') {
                // 过滤掉某些title的表，title字段是html的，所以先获取里面的文本.@xielei的需求
                let tabletxt = utils.removeDomOfText(cfg.table_title);
                if (tabletxt != null && ChartTableTitleFilterMap[tabletxt] === 1) {
                    flag = true;
                }
            }
            if (flag) {
                return null;
            }
        }

            // 图表的尺寸
        let size = this.getContentSize(boxSize),
            // 来源链接是否可点击
            hasOri = !!pathname,
            // 卡片样式
            styleCard = {
                width: chartSize.width + 'px'
            },
            // 卡片内容样式
            styleCntDetail = {},
            // 是否处于APP
            isAnalystAPP = false;
        let hideList = this.props.hideList || [];
        return (
            <div className={`abc-c-chart-card ${chartType}`} style={styleCard}
                ref={el => this.card = el}>

                {/* 卡片头部　*/}
                <div className="card-header" style={{height: chartSize.height}}>
                    {/* 卡片内容　*/}
                    <div className={'cnt-detail'} style={styleCntDetail}>
                        <Template isShow={chartType === 'chart'}>
                            <ChartBox config={Object.assign({}, config)}
                                size={chartSize}
                                manualRender={lazyLoad}
                                render={visible}
                                onClick={this.props.onClickChart}
                                />
                        </Template>
                    </div>
                </div>

                <div className="card-content">
                    <p className="card-title" onClick={() => {}}>
                        <span className="card-title-text"
                            dangerouslySetInnerHTML={{
                                __html: utils.highLightText(cardTitle || '--', keyword, chartType === 'table')
                            }}></span>
                    </p>
                    <p className="card-info">
                        {config.file_data ? JSON.parse(config.file_data).receive_date && JSON.parse(config.file_data).receive_date.split(' ')[0] : ''}
                    </p>

                    {/* 卡片底部信息　*/}
                    <Template isShow={true || source || company || industry || author || type}>
                        <div className={'card-bottom-info'}>
                            {/* isShow={!config.hideSource && source} */}
                            <Template isShow={hideList.indexOf('from') === -1}>
                                <p className={'source text-overflow'}>
                                    来源:&nbsp;
                                    <span style={{cursor: 'pointer'}} onClick={() => {
                                        !config.hideSource && this.linkToFileViewer(this.state || {});
                                    }} className={`${hasOri ? 'underline' : ''}`} dangerouslySetInnerHTML={{
                                        __html: utils.highLightText(source, keyword, chartType === 'table')
                                    }}></span>
                                </p>
                            </Template>
                            <Template isShow={hideList.indexOf('type') === -1}>
                                <p className={'source text-overflow'}>
                                    类别:&nbsp;
                                    <span dangerouslySetInnerHTML={{
                                        __html: type ? utils.highLightText(type, keyword) : ''
                                    }}></span>
                                </p>
                            </Template>
                            <Template isShow={hideList.indexOf('author') === -1}>
                                <p className={'source text-overflow'}>
                                    作者:&nbsp;
                                    <span dangerouslySetInnerHTML={{
                                        __html: author ? utils.highLightText(author, keyword) : ''
                                    }}></span>
                                </p>
                            </Template>
                            {/* <Template isShow={config.hideSource}>
                                <p className={"source text-overflow"}>
                                    所在位置:&nbsp;
                                    <span>文档第{(config.pageIndex || 0) + 1}页</span>
                                </p>
                            </Template> */}

                            {/* <Template isShow={chartType === "chart"}>
                                <Template isShow={company || industry || author || type}>
                                    <div className={"d-flex text-overflow"}>
                                        <Template isShow={company}>
                                            <p className="compony">
                                                <span dangerouslySetInnerHTML={{
                                                    __html: utils.highLightText(company, keyword)
                                                }}></span>
                                            </p>
                                        </Template>
                                        
                                        <Template isShow={industry}>
                                            <p className="industry">
                                                <span dangerouslySetInnerHTML={{
                                                    __html: utils.highLightText(industry, keyword)
                                                }}></span>
                                            </p>
                                        </Template>

                                        <Template>
                                            <p className="author">
                                                <span dangerouslySetInnerHTML={{
                                                    __html: author?utils.highLightText(author, keyword):''
                                                }}></span>
                                            </p>
                                        </Template>

                                        <Template isShow={type}>
                                            <p className="type">
                                                <span>{type}</span>
                                            </p>
                                        </Template>
                                    </div>
                                </Template>
                            </Template> */}
                        </div>
                    </Template>
                </div>
            </div>
        );
    }
}

export default withRouter(ChartCard);