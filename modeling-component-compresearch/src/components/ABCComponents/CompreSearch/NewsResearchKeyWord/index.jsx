/**
 * @description 首页搜索-相关资讯-24小时最热资讯组件
 * @author 
 * date: 2018-10-12
 */
import React, { Component } from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';
import { Row, Icon, Avatar, Spin } from 'antd';
// import Marquee from 'react-marquee';
import isEmpty from 'lodash/isEmpty';
import './index.scss';
import ask from '../../../../lib/ask';
import { newsOrigin } from '../../../../lib/constants'
// import MOCK_DATA from './mock.json';
// import numeral from 'numeral';
const preFixCls = 'abcft-person-list-view';

export default class NewsResearchKeyword extends Component {

    static defaultProps = {
        title: '',
        extra: null,
        data: [],
        contentHeight: null,
        style: {},
        showSubscribe: false,
        load: 'pending',// done, error
        mode: 'list', // list、 grid
        noDataTip: '暂无数据',
        suscribeTip: '订阅',
        onItemClick: () => { return undefined; },
        onSuscribe: () => { return undefined; },
        onRightClick: () => { return undefined; }
    }

    static propTypes = {
        title: PropTypes.string.isRequired,
        extra: PropTypes.oneOfType([
            PropTypes.string,
            PropTypes.element
        ]),
        data: PropTypes.array.isRequired,
        contentHeight: PropTypes.number,
        style: PropTypes.object,
        showSubscribe: PropTypes.bool,
        load: PropTypes.string,
        mode: PropTypes.string,
        noDataTip: PropTypes.string,
        suscribeTip: PropTypes.string,
        onRightClick: PropTypes.func,
        onItemClick: PropTypes.func,
        onRightClick: PropTypes.func
    }

    constructor(props) {
        super(props)
        this.state = ({
            load: 'pending',
            dataSource:[]
        })
    }

    // fetchData(opts = {}) {
    //     // 搜索热词数据
    //     let {
    //         data = {},
    //         loadDataSuc
    //     } = this.props;
    //     let {
    //         curCompId
    //     } = this.state;

    //     let params = {
    //     };

    //     this.setState({
    //         loading: true,
    //         dataSource: [],
    //     });
    componentDidMount() {
            ask("NewsTopMostSearch").then((resp) => {
            let { code, data = [] } = resp;
            if (code == 200) {
                this.setState({
                    load: 'done',
                    dataSource: data
                })
            } else {
                this.setState({
                    load: 'error',
                });
            }
        }, () => {
            this.setState({
                load: 'error',
                // dataSource: data,
            });
        }).catch(() => {
            this.setState({
                load: 'error',
            });
        })
    }

    handleTipClick = (id) => {
        if (isEmpty(id)) return;
        window.open(`${newsOrigin}/news/detail/${id}`, '_blank');
    }

    render() {
        const { className } = this.props;
        const { title, extra, data, contentHeight, showSubscribe, mode, style } = this.props;
        const { onRightClick, onItemClick, onSuscribe } = this.props;
        const {dataSource, load} = this.state
        const NoDataTip = (props) => {
            return (
                <Row type="flex" justify="center" align="middle" style={{ width: '100%', height: '100%' }} {...props}>
                    <Row style={{ flexDirection: 'column' }} type="flex" align="middle" justify="center">
                        <Icon type="exception" style={{
                            fontSize: '20px',
                            color: '#e6e6e6',
                            lineHeight: 1,
                            marginBottom: '16px'
                        }} />
                        <p style={{
                            fontSize: '14px',
                            color: '#a6a6a6',
                            lineHeight: 1
                        }}>{this.props.noDataTip}</p>
                    </Row>
                </Row>
            )
        }

        const Loading = (props) => {
            return (
                <Row type="flex" justify="center" align="middle" style={{ width: '100%', height: '100%' }} {...props}>
                    <Spin/>
                </Row>
            )
        }

        const Tags = (tags) => {
            return tags.map((_tag, index) => {
                return (
                    <span className={`${preFixCls}-item-tags-item`} key={index}>{_tag}<i>|</i></span>
                );
            });
        }


        // const Items = data && data.map((_item,index) => {
        const Items = (
            <table>
                <tbody>
                    {
                        dataSource.map((v, i) => {
                            return (
                                <tr key={i}>
                                    <td style={{width: 30}}>
                                        <i className={classNames("order-x", {
                                            first: i == 0,
                                            second: i == 1,
                                            third: i == 2
                                        })}>{i + 1}</i>
                                    </td>
                                    <td>
                                        <a onClick={()=>{this.handleTipClick(v.article_id)}} style={{ lineHeight: "25px",display:'inline-block',width:'230px',overflow: 'hidden',textOverflow: 'ellipsis',whiteSpace: 'nowrap',color: '#333'}}>{v.title || "--"}</a>
                                    </td>
                                </tr>
                            )
                        })
                    }
                </tbody>
            </table>
        )

        return (
            <div className={classNames(`${preFixCls}`, mode, className)}>
                <Row className={`${preFixCls}-header`} type="flex" justify="space-between" align="middle">
                    <div className={`${preFixCls}-header-title`}>
                        {title}
                    </div>
                    <div className={`${preFixCls}-header-extra`}>
                        <div className={`${preFixCls}-header-extra`} onClick={onRightClick}>
                            {extra}
                        </div>
                    </div>
                </Row>
                <div className={`${preFixCls}-content`} style={Object.assign({}, style, { height: contentHeight ? `${contentHeight}px` : 'auto' })}>
                    {
                        load === 'pending'
                            ? <Loading /> :
                            (dataSource && dataSource.length === 0)
                                ? <NoDataTip />
                                : Items
                    }

                </div>
            </div>
        )
    }
}