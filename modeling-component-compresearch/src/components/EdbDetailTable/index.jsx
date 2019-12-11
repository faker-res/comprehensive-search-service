import React, { Component } from 'react';
import DataView from 'data-viewer'
import 'data-viewer/build/lib/css/main.css'
import './index.scss'
class EdbDetailTable extends Component {
    constructor(props){
        super(props);
        this.state={
            // name:props.dataSource.name,
            // unit:props.dataSource.unit,
            // dataSource:this.handleData(props.dataSource.data),
            loading:false
        }
    }
    componentDidMount=()=>{
        console.log('componentDidMount',this.state.name);
    }
    // handleData=()=>{
    //     let {data} = this.props.dataSource
    //     let newData=[];
    //     data.map((item)=>{
    //         newData.push({
    //            key:item.key, 
    //            date:item.date,
    //            indicator:this.state.name,
    //            value:item.value,
    //            unit:this.state.unit
    //         })
    //     })
    //     console.log('处理后数据',newData);
    // }    
    render() {
        let {name,data,unit} = this.props
        const pagination = {
            showSizeChanger: true,//每页显示条数
            showQuickJumper: true,//跳转
            hideOnSinglePage: false,
            pageSize: 20,
            pageSizeOptions: ['10', '20', '30', '40'],
            onShowSizeChange: (current, pageSize) => {
                console.log(current, pageSize);
            },
            onChange: (pageNumber, pageSize) => { }
        }
        let newData= data.map(item=>{
           return {
                key:item.key, 
                date:item.date,
                indicator:name,
                value:item.value,
                unit:unit
             }
        })
        const rowSelection = {
            selectedRowKeys: [],
            onChangeCallBack: (selectedRowKeys) => {
                console.log('selectedRowKeys changed: ', selectedRowKeys);
            },
        }
        const fixedAndSortConfig = {
            columns: [
                {
                    title: '日期',
                    dataIndex: 'date',
                    filterMode: 'complex',//none | simple | complex
                    extType: 'none',//  chart | progressBar | colorgradation | stockInfo
                    // fixed: 'left',//指定宽度,指定左边，放在最上面
                    width: 200,
                    sorter: (a, b, sortOrder) => a.date - b.date,
                    align:'center'
                }, {
                    title: '指标',
                    dataIndex: 'indicator',
                    filterMode: 'simple',//none | simple | complex
                    sorter: (a, b, sortOrder) => a.indicator - b.indicator,
                    align:'center'
                }
                ,{
                    title: '数值',
                    dataIndex: 'value',
                    sorter: (a, b, sortOrder) => a.value - b.value,
                    align:'center',
                    render:(text, record, index)=>{
                        return  <div dangerouslySetInnerHTML={{ __html: text }}></div>
                    }
                }, {
                    title: '单位',
                    dataIndex: 'unit',
                    align:'center'
                }],
            pagination: pagination,
            dataSource: newData,
            config: {
                rowColor: 'zebra',
                paginationMode: 'pager',
                // morePageSize: 10,
                showTableTitle: true,
                filterUrl: newData,
                hasOrderRow: false,
                tableTitle: name,
                sourceType: 'dynamic',
                //需要显示的话，columns里固定列的宽度必须写成数字，不能是百分比，且scroll.x 也不能是百分比
                showHoverButton: false
            },
            bordered: true,
            scroll: { x: 1200 },
            showHeader: true
        }
        return (
            <div className='edbDetailTable_components'>
                {
                    data &&
                    <DataView {...fixedAndSortConfig}  />
                }
           
            </div>
        );
    }
}

export default EdbDetailTable;