/**
 * @description 分析师卡片
 * @author kygeng
 * date: 2018-08-22
 */
import React, { Component } from 'react';
import ask from '../../../../lib/ask';
import styled from 'styled-components';
import PropTypes from 'prop-types';
import isEmpty from 'lodash/isEmpty';
import { Spin } from 'antd';
import NoDataTip from '../NoDataTip';
import CardAnalyst from '../CardAnalyst';

const CardAnalystContent = styled.div`
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

export default class CardAnalystWrap extends Component {

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
        type: PropTypes.string,
    }

    constructor(props) {
        super(props)

        this.state = {
            loadStatus: 'pending', // done, error
            data: {}
        }
    }

    componentDidMount() {
        this.loadAnalyst();
    }

    loadAnalyst = () => {
        const { code, name, type, cName } = this.props;
        this.setState({ loadStatus: 'pending' });
        ask('AnalystCard', { params: { id: code }})
            .then(resp => {
                const { code, message, data } = resp;
                if (code !== 200 || isEmpty(data)) {
                    throw new Error(`Response Exception:${message},code:${code}`);
                }
                let _data = data.result && data.result[0] || {};
                this.setState({ loadStatus: 'done', data: _data });
            })
            .catch(err => {
                this.setState({ loadStatus: 'error' });
                console.error(err);
            })
    }

    render() {
        const { loadStatus, data } = this.state;
        const { code, name, type, cName } = this.props;
        return (
            <React.Fragment>
                {
                    loadStatus === 'pending' ?
                    <CardAnalystContent><Spin size="large"/></CardAnalystContent> :
                    loadStatus === 'error' ?
                    null : //<CardAnalystContent><NoDataTip/></CardAnalystContent> :
                    <CardAnalyst {...data} type={type}/>
                }
                {
                    loadStatus === 'done' && <Divider/>
                }
            </React.Fragment>
        )
    }
}