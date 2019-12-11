import React, { Component, Fragment } from 'react'
import PropTypes from 'prop-types'
import { inject, observer } from 'mobx-react'
import { withRouter, Link, Redirect, Fra } from 'react-router-dom';
// import Favorite from '../Favorite';
import _ from 'lodash'
import ejson from 'mongodb-extjson';
import axios from 'axios';
import DataTable from 'abc-data-table-card'

@withRouter
// @inject('favoriteStore')
@observer
class TableCard extends Component {
    constructor(props) {
        super(props)
        this.state = {
            tableData: this.isTableDataUrl() ? null : props.data.table_data,
        }
    }
    componentDidMount() {
        // this.props.favoriteStore.fetchFavoriteBySourceId(this.props.data.id, 'table');
        // console.log(tags);

        // 如果表格数据为URL，根据该URL获取表格数据
        this.isTableDataUrl() && this.fetchTableData();
    }

    // 判断表格数据是否为一个URL地址
    isTableDataUrl = () => {
        return /^https?:\/\//.test(this.props.data.table_data);
    }

    // 获取表格数据
    fetchTableData = async () => {
        const url = this.props.data.table_data.replace(/^http:/, 'https:');
        const res = await axios.get(url);
        const tableData = ejson.stringify(
            ejson.parse(res.request.responseText).data,
            {relaxed: true}
        );
        this.setState({tableData});
    }

    sourceLink = (val)=>{
        // let url;
        // if(parseFloat(val.table_ori) === 2){
        //     url = val.table_source;
        // }else if(val.table_source){
        //     const pageNum = this.getPageNum(val.src_id, val.id);
        //     if(val.table_source.indexOf('juchao_tables') > -1){//跳到公告
        //         const page = pageNum? `&page=${pageNum}` : '';
        //         url = `${origin_notice}/detail/text?srcId=${val.src_id}${page}`;
        //     }else if(val.table_source.indexOf('hb_tables') > -1){//跳到研报
        //         const page = pageNum? `?page=${pageNum}` : '';
        //         url = `${origin_report}/report/article/${val.src_id}${page}`;
        //     };
        // };
        // if(url) window.open(url);
    }

    render() {
        let {data, keyword, favoriteStore} = this.props
        // let { saveFavorite, favoriteList, removeFavorite } = favoriteStore;
        let val = _.cloneDeep(data || {});
        val.table_data = this.state.tableData;
        let detailLink = `/tableDetail?id=${val.id}`

        // const favorite = favoriteList.filter(item => item.source_id === data.id)[0] || {
        //     source_id: data.id,
        //     favorite_id: '',
        //     type: 'table',
        //     tags: [],
        // };

        return (
           <Fragment>
             {val.table_data && val.table_data.length &&
               <DataTable data={val} keyword={keyword} detailLink={detailLink} sourceLink={''}/>}
           </Fragment>
        )
    }
}

// props 类型
TableCard.propTypes = {
    data: PropTypes.object
}

export default TableCard