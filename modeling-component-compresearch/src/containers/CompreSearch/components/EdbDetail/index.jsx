import React, { Component } from 'react';
import EdbDetailChart from '../../../../components/EdbDetailChart'
import EdbDetailTable from '../../../../components/EdbDetailTable'
import ask from '../../../../lib/ask'
import {GetUrlParam} from '../../../../lib/utils'
class EdbDetail extends Component {
    constructor(props) {
        super(props);
        this.state={
            edbDetailData:[],//数据
            loaded:false
        }
    }
    componentDidMount=()=>{
        this.loadDetailData();
    }    
    loadDetailData=()=>{
        ask('edbDataDetail',{
            params : {
                id : decodeURIComponent(GetUrlParam('id')||'edb2437377')
            }
        }).then( res => {
            this.setState({
                loaded:true,
                name:res.name,
                unit:res.unit,
                edbDetailData:res.data
            });
            // if(res.data) return this.getChartData(res.data);
            // this.getChartData(demoData)
            // this.getChartData(mockDataChart.chartInfo);
        });
    }
    render() {
        let {edbDetailData,name,unit,frequency,source,country,area}= this.state;
        return (
            <div className='edbDetailComponents'>
                {
                    edbDetailData.length>0&& <EdbDetailChart 
                    data={edbDetailData} 
                    name ={name} 
                    unit= {unit}
                    frequency={frequency}
                    source={source}
                    country={country}
                    area={area}
                    />
                }
                {
                  edbDetailData.length>0 && 
                  <EdbDetailTable data={edbDetailData} name ={name} unit= {unit}/>
                }
            </div>
        );
    }
}

export default EdbDetail;