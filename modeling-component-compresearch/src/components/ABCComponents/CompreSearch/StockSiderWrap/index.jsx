/**
 * @description 个股侧边组件群
 * @author kygeng
 * date: 2018-08-23
 */
import React, { Component } from 'react';
import PropTypes from 'prop-types';
import ask from '../../../../lib/ask';
import { withRouter } from 'react-router-dom';
import SideStockList from '../SideStockList';
import ABCPersonListView from '../ABCPersonListView';
import isEmpty from 'lodash/isEmpty';
import styled from 'styled-components';
import { Spin } from 'antd';
import NoDataTip from '../NoDataTip';
import queryString from 'query-string';

@withRouter
export default class StockSiderWrap extends Component {

    static defaultProps = {
        code: "",
        name: "",
        cName: "",
        type: "Industry"
    }

    static propTypes = {
        code: PropTypes.string,
        name: PropTypes.string,
        cName: PropTypes.string,
        type: PropTypes.string,
    }

    constructor(props) {
        super(props)

        this.state = {
            data: {},
            loadStatus: 'pending',
            internalStatus: 'pending',
            internal_analyst: []
        };
    }

    componentDidMount() {
        this.loadStockSider();
        // this.loadInternalAnalyst();
    }

    loadInternalAnalyst = () => {
        const { code, name, cName, type } = this.props;
        this.setState({ internalStatus: 'pending' });
        ask('InternalAnalystSimilar', { params: { code, name, cName, type }})
            .then(resp => {
                const { code, message, data } = resp;
                if (code !== 200 || isEmpty(data)) {
                    throw new Error(`Response Exception:${message}, code:${code}`);
                }
                this.setState({
                    internalStatus: 'done',
                    internal_analyst: (data || []).map(item => {
                        return {
                            id: item.id,
                            name: item.name,
                            avatar: item.avatar,
                            tags: [item.department]
                        }
                    })
                })
            })
            .catch(err => {
                this.setState({ internalStatus: 'error' });
                console.error(err);
            })
    }

    loadStockSider = () => {
        const { code, name, cName, type } = this.props;
        let loadStatus = 'pending';
        this.setState({ loadStatus });
        ask('StockSider', { params: {data: JSON.stringify({ code, name, cName, type })}})
            .then(resp => {
                const { code, message, data } = resp;
                if (code !== 200 || isEmpty(data)) {
                    throw new Error(`Response Exception:${message},code:${code}`);
                }
                loadStatus = 'done';
                this.setState({ data, loadStatus });
            })
            .catch(err => {
                loadStatus = 'error';
                this.setState({ loadStatus });
                console.error(err);
            })
    }

    handleItemClick = (item) => {
        let queryParams = queryString.parse(this.props.location.search);
        const { keyword = "" } = queryParams;
        if (item.sec_name === keyword) return;
        queryParams.keyword = item.sec_name;
        this.props.history.push({
            pathname: this.props.location.pathname,
            search: `?${queryString.stringify(queryParams)}`
        });
    }

    // 证券同行业公司侧边栏点击跟多url
    stockUrlLink = () => {
        let indu_name = this.state.data.company.indu_name
            ? this.state.data.company.indu_name
            : "";
        // let type = this.state.sortModel.stock.data.result[0].stock_info.abc_code
        //     ? this.state.sortModel.stock.data.result[0].stock_info.abc_code
        //     : "";
        let type = this.props.cName  /** 临时模拟 **/
        type = type !== "" ? type.split(".")[1] : type;
        if (type === "SZ" || type === "SH") {
            type = "HZ";
        } else if (type === "HK") {
            type = "HK";
        } else {
            type = "US";
        }
        return `/individual-stock-more?indu_name=${indu_name}&type=${type}`;
    };

    render() {
        const { code, name, cName, type } = this.props;
        const { loadStatus, data } = this.state;
        const { company, stock_expert } = data;
        const { internalStatus, internal_analyst } = this.state;
        let vender_analyst = (stock_expert && stock_expert.result || []).map(item => {
            return {
                id: item.peo_uni_code,
                name: item.name,
                avatar: item.image,
                tags: [item.organ]
            }
        });
        return (
            <React.Fragment>
                {
                    loadStatus === 'done' &&
                    <SideStockList
                        title={`${name}的相关股票`}
                        stocks={company && company.result || []}
                        onStockItemClick={this.handleItemClick}
                        more={true}
                        UrlLink={this.stockUrlLink()}
                    />
                }
                <div style={{width:'350px',height:'20px'}} hidden={loadStatus !== 'done'}></div>
                {/* {
                    loadStatus === 'done' &&
                    <ABCPersonListView
                        title={`${name}外部研究员推荐`}
                        mode="grid"
                        load="done"
                        data={vender_analyst}/>
                }
                <div style={{width:'350px;',height:'20px'}} hidden={loadStatus !== 'done'}></div>
                {
                    internalStatus === 'done' &&
                    <ABCPersonListView
                        title={`${name}同领域内部部研究员`}
                        load={internalStatus}
                        mode="grid"
                        data={internal_analyst}/>
                } */}
            </React.Fragment>
        )
    }
}