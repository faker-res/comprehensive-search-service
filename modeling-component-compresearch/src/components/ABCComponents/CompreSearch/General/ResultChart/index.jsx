/**
 * @description 数据图组件
 * @date 2018.06.15
 * @author wsh
 */

import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import PropTypes from 'prop-types';
import moment from 'moment';
import utils from '../lib/utils';
import './index.scss'

//数据图组件
import DataChart from '../DataChart'

// 图表样式类型：1代表左图右信息结构，2代表highchart图表
const listType = 2;
@withRouter
class ResultChart extends Component {
    constructor(props) {
        super(props);

        this.state = {};
    }
    
    render() {
        let UrlKeyword = window.ParseKeyword(this.props);
        let { item, total_count } = this.props.data;
        const {
            className,
            match,
            titleSuffix
        } = this.props;
        const basePath = match.params.lang ? '/' + match.params.lang : '';
        let style = "ResultChart " + className;
        const result_list = item.slice(0, 6);
        let listCon = '', listTypeClass = "chartType2";
        if (listType === 2) {
            listTypeClass = "chartType2";
            listCon = result_list.map(function (data, idx) {
                return <li key={idx}>
                    <DataChart data={data} />
                </li>
            })
        } else {
            listTypeClass = "chartType1";
            listCon = result_list.map(function (data, idx) {
                let titlestr = utils.delHtmlTag(data.title)
                return <li key={idx}>
                    <div className="img-warp">
                        <img src={data.image_url.replace("http:", "https:")} alt="" />
                    </div>
                    <div className="ResultChartConlistCon">
                        <div className="titlewarp">
                            <span className="atitle" dangerouslySetInnerHTML={{ __html: data.image_title }}></span>
                            <span>{moment(data.time * 1000).format('YYYY/MM/DD')}</span>
                        </div>
                        <div className="sourcewarp">
                            <span>来源：</span>
                            <span title={titlestr} dangerouslySetInnerHTML={{ __html: data.title }}></span>
                        </div>
                        <div className="catewarp">
                            <span className="span-common">
                                <span>类别：</span><span>{data.type || "--"}</span>
                            </span>
                            <span className="span-common">
                                <span>作者：</span><span dangerouslySetInnerHTML={{ __html: data.author || "--" }}></span>
                            </span>
                        </div>
                    </div>
                </li>
            })
        }
        const NewsListView = <div className={style}>
            <a className="title" href={`/chart?keyword=${UrlKeyword}`} >
              <span className="highLight">{UrlKeyword}</span>
              <span>{`${titleSuffix}搜索结果`}</span>
            </a>
            <p className="result_num">
                <a href={`/chart?keyword=${UrlKeyword}`}>
                    全部结果 <span>{total_count}</span> 个
                            </a>
                </p>
            <div className="ResultChartCon">
                <ul className={listTypeClass}>
                    {listCon}
                </ul>
            </div>
        </div>
        return result_list && result_list.length ? (
            <div>{NewsListView}</div>
        ) : null
    }
}


// props 类型
ResultChart.propTypes = {
    title: PropTypes.string,
    data: PropTypes.any,
    // title后缀
    titleSuffix: PropTypes.string,
}

ResultChart.defaultProps = {
    title: "",
    data: [],
    titleSuffix: ""
}
export default ResultChart