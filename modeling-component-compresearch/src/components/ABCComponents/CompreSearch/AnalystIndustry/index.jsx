/**
 * @description 分析师相关行业
 * @author kygeng
 * date: 3018-08-23
 */
import React, { Component } from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';
import { Card, Tag, List, Spin, Row } from 'antd';
import NoDataTip from '../NoDataTip';
import numeral from 'numeral';
import isEmpty from 'lodash/isEmpty';
import ask from '../../../../lib/ask';

import { mock_data } from './mock';

const CardWrap = styled(Card)`
    width: 350px;
    background: #fff;
    border: 1px solid #eee;
    box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.04);
    .ant-card-head-title {
        padding: 13px 0;
    }
`;

const CardContent = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    min-height: 400px;
`;

const ListWrap = styled(List)`
    padding: 20px;
    width: 100%;
    .ant-list-item {
        padding-top: 0;
        .ant-list-item-content {
            flex-direction: column
        }
    }
`;

const RowFirst = styled(Row)`
    position: relative;
    width: 100%;
    overflow: hidden;
`;

const TagItem = styled(Tag)`
    &.ant-tag {
        color: #3F7CD5;
        border: none;
        border-radius: 0px;
        background: none;
    }
    &.ant-tag.first {
        color: #fff;
        background: #ce5542;
    }
    &.ant-tag.second {
        color: #fff;
        background: #ee8015;
    }
    &.ant-tag.third {
        color: #fff;
        background: #f0c027;
    }
`;

const ItemTitle = styled.span`
    font-size: 15px;
    color: #333;
    font-weight: bold;
`;

const ItemResearchCount = styled.span`
    float: right;
    font-size: 13px;
    color: #999;
`;

const RowSecond = styled(Row)`
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin: 8px 0 0 30px;
    &::before, &::after {
        display: none;
    }
`;

const StockItem = styled.div`
    display: inline-block;
    width: 130px;
    color: ${props => (props.differStatus === 'rise' ? '#d0021b' : '#04a92b')};
    font-size: 12px;
    .stock-name {
        margin: 0 5px;
    }
`;

export default class AnalystIndustry extends Component {
    
    static defaultProps = {
        code: "",
        name: "",
        cName: "",
        type: "Analyst"
    }

    static propTypes = {
        code: PropTypes.string,
        name: PropTypes.string,
        cName: PropTypes.string,
        type: PropTypes.string
    }
    
    constructor(props) {
        super(props)

        this.state = {
            loadStatus: 'pending',
            data: []
        }
    }

    componentDidMount() {
        this.loadAnalystIndustrys();
    }

    loadAnalystIndustrys = () => {
        const { code } = this.props;
        let loadStatus = 'pending';
        ask('AnalystIndustry', { params: { code: code }})
            .then(resp => {
                const { code, message, data } = resp;
                if (code !== 200 && isEmpty(data)) {
                    throw new Error(`Response Exception:${message}, code:${code}`);
                }
                loadStatus = 'done';
                this.setState({ loadStatus, data });
            })
            .catch(err => {
                let loadStatus = 'error';
                this.setState({ loadStatus });
                console.error(err);
            })
    }

    renderItem = (item, index) => {
        return (
            <List.Item>
                <RowFirst>
                    <TagItem className={index === 0 ? 'first' : index === 1 ? 'second' : index === 2 ? 'third' : ''}>{index + 1}</TagItem>
                    <ItemTitle>{item.name}</ItemTitle>
                    <ItemResearchCount>研究{item.count}次</ItemResearchCount>
                </RowFirst>
                <RowSecond>
                    {
                        item.stocks.map(stock => {
                            let differStatus = stock.differ_range > 0 ? 'rise' : 'drop';
                            return (
                                <StockItem differStatus={differStatus}>
                                    <i className={`iconfont-web ${differStatus}`} dangerouslySetInnerHTML={
                                        {__html: differStatus === 'rise' ? '&#xe60d;' : '&#xe60c;'}
                                    }></i>
                                    <span className="stock-name">{stock.name}</span>
                                    <span className="stock-differ">{differStatus === 'rise' ? '+' : ''}{numeral(stock.differ_range * 100).format('0.00')}%</span>
                                </StockItem>
                            )
                        })
                    }
                </RowSecond>
            </List.Item>
        )
    }

    render() {
        const { code, name, cName, type } = this.props;
        const { loadStatus, data } = this.state;
        return (
            <CardWrap title={`${name}研究相关行业`}>
                {
                    loadStatus === 'pending' ?
                    <CardContent><Spin/></CardContent> :
                    loadStatus === 'error' ?
                    <CardContent><NoDataTip/></CardContent> :
                    <ListWrap itemLayout="horizontal"
                        dataSource={data}
                        split={false}
                        renderItem={this.renderItem}/>
                }
            </CardWrap>
        )
    }
}