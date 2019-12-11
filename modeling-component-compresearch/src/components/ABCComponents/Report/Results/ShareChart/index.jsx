import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {
    Modal,
    Menu,
    Icon,
    Button,
} from 'antd';
import { withRouter } from 'react-router-dom';

import ChartItem from './../ChartItem/';

import './index.scss';

@withRouter
class DataShareDetail extends Component {
    constructor(props) {
        super(props);

        let size = this.getClientSize();
        this.state = {
            // 图表尺寸
            chartSize: {
                width: size.clientWidth - 48 * 2,
                height: size.clientHeight - 74
            },
            // 是否显示
            isShow: !!props.isShow
        };

        this.handleResize = this.handleResize.bind(this);
    }

    getClientSize() {
        let {clientWidth = 0, clientHeight = 0} = document.documentElement || {};
        return {
            clientWidth,
            clientHeight
        };
    }

    handleResize() {
        this.timer && clearTimeout(this.timer);

        this.timer = setTimeout(() => {
            let size = this.getClientSize();
        
            this.setState({
                chartSize: {
                    width: size.clientWidth - 48 * 2,
                    height: size.clientHeight - 74
                }
            });
        }, 500);
    }

    bindResizeEvent() {
        window.addEventListener('resize', this.handleResize);
    }

    unbindResizeEvent() {
        this.timer && clearTimeout(this.timer);

        window.removeEventListener('resize', this.handleResize);
    }

    /**
     * 移除文本中的html标签
     * @param {string} htmlText 待清洗的html内容 demo: <font color="red">顺丰速递</font> => 顺丰速递
     */
    removeDomOfText(htmlText = '') {
        return htmlText.replace(/<\/?[^>]+>/g, '');
    }

    componentDidMount() {
        // 绑定监听事件
        this.bindResizeEvent();
    }

    componentWillUnmount() {
        // 移除监听事件
        this.unbindResizeEvent();
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.isShow != this.state.isShow) {
            this.setState({
                isShow: nextProps.isShow
            });
        }
    }

    render() {
        let {
            className,
            config
        } = this.props;
        let {
            chartSize,
            isShow
        } = this.state;

        return (
            <FullScreen isShow={isShow}>
                <div className={`abc-fullscrren-chart ${className}`}>
                    <div className={'header-bar'}>
                        <div className={'title'}>
                            <span>{this.removeDomOfText(config.title || '')}</span>
                        </div>

                        <div className="actions">
                            <div className={'action-item download'}>
                                <Menu
                                    onSelect={(item) => {
                                        if (item.key == 'excel') {
                                            let hostname = location.hostname.indexOf('-dev') != -1 ? 'http://www-dev.analyst.ai' : 'http://www-dev.analyst.ai';
                                            window.open(`${hostname}/report-search/report-xls?id=${config.file_id}&origin_from=`);
                                        } else {
                                            window.open(config.url || config.image_url);
                                        }
                                    }}
                                    selectedKeys={['']}
                                    mode="horizontal">

                                    <Menu.SubMenu title={(
                                        <span><Icon type="download" />导出</span>
                                    )}>
                                        <Menu.Item key="excel">导出数据</Menu.Item>
                                        <Menu.Item key="img">导出原图</Menu.Item>
                                    </Menu.SubMenu>
                                </Menu>
                            </div>
                            <div className={'action-item close'}>
                                <Button type="primary" onClick={() => {
                                    this.setState({
                                        isShow: false
                                    });
                                }}>
                                    <span><Icon type="close" />退出全屏</span>
                                </Button>
                            </div>
                        </div>
                    </div>
                    <div className="content-wrap">
                        <ChartItem config={Object.assign({}, config)}
                            size={chartSize}
                            manualRender={true}
                            render={true}
                            onClick={() => {

                            }}/>
                    </div>
                </div>
            </FullScreen>
        );
    }
}

DataShareDetail.defaultProps = {
    language: 'zh-CN',
    className: '',
    handleRef: () => {},
    isShow: false,
    config: {},
    showCloseBtn: true
};

DataShareDetail.propTypes = {
    // 语言环境
    language: PropTypes.string,
    // 附加类名
    className: PropTypes.string,
    // ref回调
    handleRef: PropTypes.func,
    // 是否全屏
    isShow: PropTypes.bool,
    // 图表配置
    config: PropTypes.object,
    // 是否显示关闭按钮
    showCloseBtn: PropTypes.bool,
};

export default DataShareDetail;