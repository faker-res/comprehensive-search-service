/**
 * @description 个股内部研究员
 * @author kygeng
 * date: 2018-08-21
 */
import React, { Component } from 'react';
import PropTypes from 'prop-types';
import ask from '../../../../lib/ask';
import ABCPersonListView from '../ABCPersonListView';
import styled from 'styled-components';
import isEmpty from 'lodash/isEmpty';

import { mock_data } from './mock';

const StockAnalystWrap = styled.div`
    width: 350px;
`;

export default class StockAnalyst extends Component {

    static defaultProps = {
        stockCode: "",
        stockName: ""
    }

    static propTypes = {
        stockCode: PropTypes.string,
        stockName: PropTypes.string,
    }

    constructor(props) {
        super(props)

        this.state = {
            loadStatus: 'pending',
            // 内部相关研究员
            invernal_analyst: [],
            // 外部相关研究员
            vender_analyst: []
        }
    }

    componentDidMount = async () => {
        this.loadStockAnalysts();
    }

    loadStockAnalysts = () => {
        const { stockCode, stockName } = this.state;
        this.setState({ loadStatus: 'pending' });
        ask('StockAnalyst', { params: { code: stockCode }})
            .then(resp => {
                const { code, message, data } = resp;
                if (code !== 200 || isEmpty(data)) {
                    throw new Error(`Response Exception:${message},code:${code}`);
                }
                const { invernal = [], vender = [] } = data;
                this.setState({
                    loadStatus: 'done',
                    invernal_analyst: (invernal || []).map(item => {
                        return {
                            id: item.id,
                            name: item.name,
                            avatar: item.avatar,
                            tags: [item.department || ""]
                        }
                    }),
                    vender_analyst: (vender || []).map(item => {
                        return {
                            id: item.id,
                            name: item.name,
                            avatar: item.avatar,
                            tags: [item.company || ""]
                        }
                    })
                });
            })
            .catch(err => {
                this.setState({ loadStatus: 'error' });
                console.error(err);
            })
            .finally(resp => {
                resp = mock_data;
                const { code, message, data } = resp;
                if (code !== 200 || isEmpty(data)) {
                    throw new Error(`Response Exception:${message},code:${code}`);
                }
                const { invernal = [], vender = [] } = data;
                this.setState({
                    loadStatus: 'done',
                    invernal_analyst: (invernal || []).map(item => {
                        return {
                            id: item.id,
                            name: item.name,
                            avatar: item.avatar,
                            tags: [item.department || ""]
                        }
                    }),
                    vender_analyst: (vender || []).map(item => {
                        return {
                            id: item.id,
                            name: item.name,
                            avatar: item.avatar,
                            tags: [item.company || ""]
                        }
                    })
                });
            })
    }

    render() {
        const { stockCode, stockName } = this.props;
        const { loadStatus, invernal_analyst, vender_analyst } = this.state;
        return (
            <StockAnalystWrap>
                <div style={{with:'100%',height:'20px'}}></div>
                <ABCPersonListView
                    title={`${stockName}内部研究员推荐`}
                    data={invernal_analyst}
                    load={loadStatus}
                    mode="grid"/>
                <div style={{with:'100%',height:'20px'}}></div>
                <ABCPersonListView
                    title={`${stockName}外部研究员推荐`}
                    data={vender_analyst}
                    load={loadStatus}
                    mode="grid"/>
            </StockAnalystWrap>
        )
    }
}