/**
 * @description 个股相关股票列表
 * @author kygeng
 * date: 2018-08-21
 */
import React, { Component } from 'react';
import styled from 'styled-components';
import ask from '../../../../lib/ask';
import { Spin } from 'antd';
import NoDataTip from '../NoDataTip';
import SideStcokList from '../SideStockList';
import isEmpty from 'lodash/isEmpty';

import { mock_data } from './mock';

const SideStcokListBody = styled.div`
    width: 350px;
    height: 578px;
    background: #fff;
    border: 1px solid #eee;
    box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.04);
`;

const SideStcokListHeader = styled.div`
    display: flex;
    align-items: center;
    padding: 20px 0;
    width: 100%;
    height: 50px;
    font-size: 15px;
    color: #333;
    border-bottom: 1px solid #eee;
`;

const SideStcokListContent = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 528px;
`;

export default class SideStockListWrap extends Component {
    constructor(props) {
        super(props)

        this.state = {
            loadStatus: 'pending', // done, error
            data: {}
        }
    }

    componentDidMount = async () => {
        this.loadStockList();
    }

    loadStockList = () => {
        const { stockCode } = this.props;
        this.setState({ loadStatus: 'pending' });
        ask('StockSimilar', { params: { code: stockCode }})
            .then(resp => {
                const { code, message, data } = resp;
                if (code !== 200 || isEmpty(data)) {
                    throw new Error(`Response Exception:${message},code:${code}`);
                }
                this.setState({ loadStatus: 'done', data: data });
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
                this.setState({ loadStatus: 'done', data: data });
            })
    }

    render() {
        const { loadStatus, data } = this.state;
        const { stockCode, stockName } = this.props;

        const Wrap = (props) => {
            return (
                <SideStcokListBody>
                    <SideStcokListHeader>{stockName}的相关股票</SideStcokListHeader>
                    <SideStcokListContent>
                        {props.children}
                    </SideStcokListContent>
                </SideStcokListBody>
            )
        }
        return (
            <React.Fragment>
                {
                    loadStatus === 'pending' ?
                    <Wrap><Spin/></Wrap> :
                    loadStatus === 'error' ?
                    <Wrap><NoDataTip/></Wrap> :
                    <SideStcokList title={`${stockName}的相关股票`} stocks={data}/>
                }
            </React.Fragment>
        )
    }
}