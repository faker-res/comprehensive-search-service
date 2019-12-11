import React, { Component } from 'react';
import { Row, Col } from 'antd';
import ReactHighcharts from 'react-highcharts';
import './HotList.less';
import moment from 'moment'
import empty from '../../../../../assets/image/new-report/icon_empty.png';

class HotList extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }
  clickHandle = (item) => {
    const { clickHandleCallback, type } = this.props;
    if (clickHandleCallback) clickHandleCallback(item, type)
  }
  getConfigData = () => {
    const { Highcharts } = ReactHighcharts;
    const dataList = this.props.hotList[0].deail;
    const data1 = [];
    const data2 = [];
    
    if (dataList) {
      dataList.map((ele) => {
        let date = moment(ele.date).format('MM/DD')
        data1.push([date,ele.org]);
        data2.push([date,ele.report]);
      })
    }
    const configData = {
      chart: {
        zoomType: 'xy',
        height: '150px',
      },
      title: {
        text: ''
      },
      xAxis: {
        visible: false
      },
      yAxis: { visible: false },
      tooltip: {
        shared: true
      },
      credits: {
        enabled: false
      },
      series: [{
        name: '机构数量',
        type: 'column',
        data: [],
        tooltip: {
          valueSuffix: ''
        }
      }, {
        name: '研报数量',
        type: 'spline',
        color: '#FCAD88',
        data: [],
        tooltip: {
          valueSuffix: ''
        }
      }]
    };
    configData.series[0].data = data1;
    configData.series[1].data = data2;
    return configData;
  }
  render() {
    let { extraInfo, hotList = [] } = this.props;
    if (Object.prototype.toString.apply(hotList).indexOf('Array') == -1) {
      hotList = [];
    }
    return (
      <div className='hot_list_container'>
        <div className='hot_list_title_container'>
          {extraInfo}
        </div>
        {
          hotList.length === 0 &&
          <div className={`col_center_middle subscribe_empty`}>
            <img src={empty} alt={empty} />
            <div>暂无内容</div>
          </div>
        }
        {
          hotList.length !== 0 && <div className='hot_list_content'>
            {
              hotList.map((ele, i) => {
                return (
                  <div key={i} className='hot_list_item'>
                    <Row>
                      <Col className='hot_list_item_name' span={8} onClick={() => { this.clickHandle(ele) }}>{ele.name}</Col>
                      <Col className='hot_list_item_number' span={8}>{`机构：${ele.org_total}`}</Col>
                      <Col className='hot_list_item_number' span={8}>{`研报：${ele.report_total}`}</Col>
                    </Row>
                    {
                      i == 0 && <ReactHighcharts config={this.getConfigData()} />
                    }
                  </div>
                );
              })
            }
          </div>
        }
        
      </div>
    );
  }
}
export default HotList;
