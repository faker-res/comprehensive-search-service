/**
 * @description 数据表组件
 * @date 2018.06.15
 * @author wsh
 */

import React, { Component }  from 'react'
import {Link} from 'react-router-dom'
import PropTypes from 'prop-types'
import moment from 'moment'
import queryString from 'query-string';
import { withRouter } from 'react-router-dom';
import TBlist from './TBlist'
import './index.scss'
const origin_v1 = 'https://v1.analyst.ai';

@withRouter
class  ResultTable extends Component {
    state = {
        dataError: false,
    };
    
    constructor(props) {
        super(props)
    }

    componentDidCatch(error, errorInfo) {
        this.setState({
            dataError: true,
        })
    }

    render() {
        let UrlKeyword =  window.ParseKeyword( this.props);
        let { item, total_count} = this.props.data;
        let {className, match, titleSuffix} = this.props;
        const basePath = match.params.lang ? '/' + match.params.lang : '';
        let style = "ResultTable " + className;
        if (this.state.dataError) {
            return null;
        }
        const result_list = item.slice(0,3);
        const NewsListView = <div className={style}>
            <Link className="title" to={`tableModule?keyword=${UrlKeyword}`}><span className="highLight">{UrlKeyword}</span><span>{`${titleSuffix}`}搜索结果</span></Link>
            {/* <Link className="title" to={`tableModule?keyword=${UrlKeyword}`}><p className="result_num">全部结果 <span>{total_count}</span> 个</p></Link> */}
            <div>
                <Link className="title" to={`tableModule?keyword=${UrlKeyword}`}>
                    <a className="result_num">全部结果 <span>{total_count}</span> 个</a>
                </Link>
            </div>
            <div  className="ResultTableCon">
                <ul>
                    {
                        result_list.map(function (item, idx) {
                            return <li key={item.id}>
                                <div className="rtc-TitleWarp">
                                  <a href={`/tableDetail?id=${encodeURIComponent(item.id)}`} target="_blank"  dangerouslySetInnerHTML={{__html: item.table_title}} className="rtc-title"></a>
                                  <div className="rtc-time">{moment(item.time * 1000).format('YYYY/MM/DD')}</div>
                                </div>
                                <a href={`/tableDetail?id=${encodeURIComponent(item.id)}`} target="_blank" className="ResultTableConlistCon">
                                  <TBlist data={item} />
                                </a>
                                <p className="ResultTableSubTit">
                                  <a href={parseFloat(item.table_ori) === 2 ? item.table_source : item.table_source ?
                                      origin_v1 + `/chart/details?id=${item.table_id}&type=table&tab=fileviewer&typesource=${item.table_source}` :
                                      undefined} target="_blank">来源：{item.title}</a>
                                </p>
                                <p className="ResultTableSubTit">类别：{item.type}</p>
                            </li>
                        })
                    }
                </ul>
            </div>
        </div>
        return (
            <div>{NewsListView}</div>
        )
    }
}


// props 类型
ResultTable.propTypes = {
    title: PropTypes.string,
    data:PropTypes.any
}
export default ResultTable