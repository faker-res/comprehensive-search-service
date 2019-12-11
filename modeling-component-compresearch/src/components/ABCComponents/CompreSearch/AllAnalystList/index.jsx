/**
 * @description @全部分析师
 * @date 2018-07-31
 * @author jywang
 */

import React, { Component } from 'react'
import PropTypes from 'prop-types'
import './index.scss';
import { Card, Pagination, Menu, Dropdown, Row } from 'antd';
// import { siteDomain, newsOrigin } from '../../constants';
import Loading from '../Loading';
import _ from 'lodash'
import NoDataTip from '../NoDataTip';
import queryString from 'query-string';
import { withRouter } from 'react-router-dom';

@withRouter
class AllAnalystList extends Component {
    goParam = (objParam) => {
        let { currentOrderIndex, current } = this.state;
        let { limit } = this.props;
        let info = {
            prior: currentOrderIndex,
            offset: (current - 1) * limit
        }
        info = Object.assign(info, objParam);
        this.props.getListParams(info);
    }
    getMunu = (paramArray) => {
        return (
            <Menu>
                {
                    paramArray && paramArray.map((item, idx) => {
                        return (
                            <Menu.Item key={idx}>
                                <div style={{ textAlign: 'center' }}>
                                    <span style={{ marginRight: '20px' }}>{item.rankYear + '年'}</span>
                                    <span>{'第' + item.rankNum + '名'}</span>
                                </div>
                            </Menu.Item>
                        )
                    })
                }
            </Menu>
        )
    }
    itemRender(current, type, originalElement) {
        if (type === 'prev') {
            return <a style={{ border: '1px solid #d9d9d9', borderRadius: '4px', padding: '0 10px', textDecoration: 'none', display: 'inline-block' }}>上一页</a>;
        } if (type === 'next') {
            return <a style={{ border: '1px solid #d9d9d9', borderRadius: '4px', padding: '0 10px', textDecoration: 'none', display: 'inline-block' }}>下一页</a>;
        }
        return originalElement;
    }
    onChange = (page) => {
        this.setState({
            current: page,
        }, () => {
            this.goParam();
        });
    }
    orderByTypeCheck = (index) => {
        this.setState({
            currentOrderIndex: index
        }, () => {
            this.goParam();
        });
    }
    constructor(props) {
        super(props)
        this.state = {
            currentOrderIndex: 'ranking',
            current: 1,
        }
    }
    componentDidMount() {

    }

    render() {
        const rootClass = `allAnalystBox`
        let {
            orderByType,
            data,
            limit,
            dataState,
            current,
        } = this.props;
        let {
            currentOrderIndex
        } = this.state;

        return (
            <Card title="全部分析师" bordered={false} extra={dataState==='done'&&<span>当前共有<b>{data.totalCount || data.items&&data.items.length}</b>个分析师</span>} className={rootClass}>
                <Row hidden={dataState!=='done'}>
                    <div className="orderByType">
                        {
                            orderByType.map((item, idx) => {
                                return <a key={idx} className={currentOrderIndex === item.key ? 'active' : null} onClick={() => this.orderByTypeCheck(item.key)} href="javascript:;">{item.text}</a>
                            })
                        }
                    </div>
                    <ul>
                        {
                            data.items && data.items.map((item, idx) => {
                                return (
                                    <li key={idx}>
                                        <div className="personNamePic">
                                            <div className="personPic">
                                            {/*<a target="_blank" href={`/entity-search/analyst?id=${item.peo_uni_code}`}><img src={`${item.image&&item.image+'?x-oss-process=image/resize,m_fixed,h_110,w_85'||item.image}`} alt="" /></a>*/}
                                            <a><img src={`${item.image&&item.image+'?x-oss-process=image/resize,m_fixed,h_110,w_85'||item.image}`} alt="" /></a>
                                            </div>

                                            <div className="personName">
                                                {/*<h3><a target="_blank" href={`/entity-search/analyst?id=${item.peo_uni_code}`}>{item.name || '--'}</a></h3>*/}
                                                <h3><a>{item.name || '--'}</a></h3>
                                                {/*<p><span><a target="_blank" href={`/entity-search/broker/${item.org_uni_code}`}>{item.org_sname || '--'}</a></span><span>{item.indu_name || '--'}</span></p>*/}
                                                <p><span><a>{item.org_sname || '--'}</a></span><span>{item.indu_name || '--'}</span></p>
                                            </div>
                                        </div>
                                        <div className="personIndex">
                                            <div className="reportTotal">
                                                <b>{item.reportCount || '--'}</b>
                                                <p>研报总量</p>
                                            </div>
                                            <div className="awardTotal">
                                                <b>{item.prizeCount || '--'}</b>
                                                <p>获奖次数</p>
                                            </div>
                                            <div className="treasureTotal">
                                                <b>{item.rank&&item.rank[0]&&item.rank[0].rankNum || '--'}<small> <Dropdown overlay={this.getMunu(item.rank)}>
                                                    <a className="ant-dropdown-link" href="javascript:;">
                                                        其他排行
                                                    </a>
                                                </Dropdown></small></b>
                                                <p>{item.rank&&item.rank[0]&&item.rank[0].rankYear || '--'}新财富排行</p>
                                            </div>
                                            <div className="reachDays">
                                                <b>{item.days || '--'}</b>
                                                <p>平均达成天数</p>
                                            </div>
                                        </div>
                                        <div className="personDesc">
                                            {/*<a href={`https://report.analyst.ai/report/article/${item.report&&item.report.id}`} target="_blank" rel="noopener noreferrer">*/}
                                            <a rel="noopener noreferrer">
                                                {
                                                    item.report&&item.report.title || '--'
                                                }
                                            </a>
                                        </div>
                                    </li>
                                )
                            })
                        }
                    </ul>

                    <div className="page">
                        <Pagination onChange={this.onChange} current={current} total={data.totalCount} defaultPageSize={limit} itemRender={this.itemRender} />
                    </div>
                </Row>
                <Row
                        type="flex"
                        justify="center"
                        style={{
                            minHeight: '900px'
                        }}
                        hidden={dataState !== 'pending'}><Loading /></Row>
                    <Row
                        type="flex"
                        justify="center"
                        style={{
                            minHeight: '900px'
                        }}
                        hidden={dataState !== 'error'}><NoDataTip/></Row>
            </Card>

        );
    }
}

export default AllAnalystList

//props默认值
AllAnalystList.defaultProps = {
    orderByType: [{ text: '新财富排名', key: 'ranking' }, { text: '获奖次数', key: 'prizeCount' }, { text: '研报数量', key: 'reportCount' }, { text: '平均达成天数', key: 'days' }],
    data: {
        "items": [
            {
                "reportCount": 1,
                "indu_name": 1,
                "prizeCount": 1,
                "rankYear": 1,
                "report": {
                    "title": 1,
                    "id": 1
                },
                "days": 1,
                "rank": 2,
                "org_sname": "中泰证券",
                "name": "笃慧",
                "image": "http://abc-crawler.oss-cn-hangzhou.aliyuncs.com/376ae6ab-75cd-3a05-8143-6737048c9321.jpg"
            },
            {
                "reportCount": 1,
                "indu_name": 1,
                "prizeCount": 1,
                "rankYear": 1,
                "report": {
                    "title": 1,
                    "id": 1
                },
                "days": 1,
                "org_sname": "天风证券",
                "name": "姜浩",
                "image": "http://abc-crawler.oss-cn-hangzhou.aliyuncs.com/cf7441c8-e69c-3425-9e0b-19d8a0dd52ea.jpg"
            },
            {
                "reportCount": 1,
                "indu_name": 1,
                "prizeCount": 1,
                "rankYear": 1,
                "report": {
                    "title": 1,
                    "id": 1
                },
                "days": 1,
                "rank": 2,
                "org_sname": "华泰证券",
                "name": "陈羽锋",
                "image": "http://abc-crawler.oss-cn-hangzhou.aliyuncs.com/52c55e64-c50d-3cf1-bd89-e8ffc062b038.jpg"
            }
        ]
    },
    limit: 10,
    dataState:'pending',
    current:1,
}

// props 类型

AllAnalystList.propTypes = {
    orderByType: PropTypes.array,
    // data: PropTypes.object,
    limit: PropTypes.number,
    dataState:PropTypes.string,
}
