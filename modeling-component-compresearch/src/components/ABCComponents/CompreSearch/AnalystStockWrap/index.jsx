/**
 * @description 分析师相关个股
 * @author kygeng
 * date: 2018-09-06
 */
import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import queryString from 'querystring';
import PropTypes from 'prop-types';
import ask from '../../../../lib/ask';
import isEmpty from 'lodash/isEmpty';
import SideStockList from '../SideStockList';

@withRouter
export default class AnalystStockWrap extends Component {

    static  defaultProps = {
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
            loadStatus: 'pending',
            data: [],
        }
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

    componentDidMount() {
        this.loadAnalystStocks();
    }

    loadAnalystStocks = () => {
        const { code } = this.props;
        this.setState({ loadStatus: 'pending' });
        ask('AnalystStockCompany', { params: { analystId: code }})
            .then(resp => {
                const { code, message, data } = resp;
                if (code !== 200 || isEmpty(data)) {
                    throw new Error(`Response Exception:${message},code:${code}`);
                }
                let _data = data.map(item => {
                    return {
                        sec_name: item.stockName,
                        abc_code: item.stockCode,
                        stock_newsset: {
                            stock_data: {
                                current_price: item.now,
                                zdf: item.differRange,
                                suspend: false
                            }
                        }
                    }
                });
                this.setState({ loadStatus: 'done', data: _data });
            })
            .catch(err => {
                console.error(err);
                this.setState({ loadStatus: 'error' });
            })
    }

    render() {
        const { loadStatus, data } = this.state;
        const { name } = this.props;
        return (
            <React.Fragment>
                {
                    loadStatus === 'done' &&
                    <SideStockList
                        title={`${name}研究的相关公司`}
                        stocks={data}
                        onStockItemClick={this.handleItemClick}/>
                }
                {
                    loadStatus && <div style={{width:'350px',height:'20px'}} hidden={loadStatus !== 'done'}></div>
                }
            </React.Fragment>
        )
    }
}