/**
 * @description 个股卡片包装
 * @author kygeng
 * date: 2018-08-20
 */
import React, { Component } from 'react';
import PropTypes from 'prop-types';
import ask from '../../../../lib/ask';
import isEmpty from 'lodash/isEmpty';
import { Spin } from 'antd';
import styled from 'styled-components';
import ConvertStockCard from '../ConvertStockCard';
import StockCard from '../StockCard';
import NoDataTip from '../NoDataTip';

import { mock_data } from './mock';

const StockWrap = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    width: 870px;
    height: 435px;
`;

const Divider = styled.div`
    width: 100%;
    height: 20px;
    background: transparent;
`;

export default class StockCardWrap extends Component {
    
    static defaultProps = {
        // 个股代码
        stockCode: '',
        // 个股名称
        stockName: ''
    }

    static propTypes = {
        stockCode: PropTypes.string,
        stockName: PropTypes.string,
    }
    
    constructor(props) {
        super(props)

        this.state = {
            loadStatus: 'pending', // done, error
            data: {}

        }
    }

    componentDidMount = async () => {
        this.loadStockCard();
    }

    // 加载个股卡片数据
    loadStockCard = () => {
        const { stockCode, stockName, code } = this.props;
        if (isEmpty(stockCode)) return;
        this.setState({ loadStatus: 'pending' });
        ask('StockCard', { params: {data : JSON.stringify({ code: code, name: stockName, cName: stockCode, type: 'Industry' })}})
            .then(resp => {
                const { code, message, data } = resp;
                if (code !== 200 || isEmpty(data) || isEmpty(data.result && data.result[0] || {})) {
                    throw new Error(`Response Exception: ${message}, code:${code}`);
                }
                this.setState({ loadStatus: 'done', data: data.result && data.result[0] || {} });
            })
            .catch(err => {
                this.setState({ loadStatus: 'error' });
                console.error(err);
            })
    }

    render() {
        const { loadStatus, data } = this.state;
        return (
            <React.Fragment>
                {
                    loadStatus === 'pending' ?
                    <StockWrap><Spin size="large" /></StockWrap> :
                    loadStatus === 'error' ?
                    null : // <StockWrap><NoDataTip/></StockWrap> :
                    <ConvertStockCard WrapComponent={StockCard} params={data}/>
                }
                {
                    loadStatus === 'done' && <Divider/>
                }
            </React.Fragment>
        )
    }
}