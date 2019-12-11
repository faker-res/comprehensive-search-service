/**
 * @description MiniChart 组件
 * @date 2018.01.16
 * @author wsh
 */

import React, { Component } from 'react'
import Highcharts from 'highcharts';
import PropTypes from 'prop-types'
import './style.scss'

export default class MiniChart extends Component {
    constructor(props) {
      super(props);
      this.chartContainer = React.createRef();
    }
  
    componentDidMount() {
      const {width, height, color,  name, showTooltip, seriesArr} = this.props;
      const mockdata = {
        "2018-03-29": "10740",
        "2018-03-30": "10265",
        "2018-03-31": "8539",
        "2018-04-01": "7380",
        "2018-04-02": "8317",
        "2018-04-03": "10279",
        "2018-04-04": "9495",
        "2018-04-05": "9687"
      }
      const _seriesArr = Object.keys(seriesArr).length > 0 ? seriesArr : mockdata;

      let _data = [];
      for (const time in _seriesArr) {
        if (_seriesArr.hasOwnProperty(time)) {
          const value = _seriesArr[time];
          _data.push([time, parseFloat(value)])
        }
      }
      const options = {
					chart: {
            type: 'area',
            width: width,
            height: height,
            backgroundColor: 'transparent',
            margin: [0, 0, 0, 0],
            spacing: [0, 0, 0, 0]
					},
					credits: {
							enabled: false
					},
					exporting: {
							enabled: false
					},
					title: {
							text: null
					},
					legend: {
							enabled: false
					},
					tooltip: {
							enabled: showTooltip
					},
					xAxis: {
              visible:false,
							lineWidth: 0,
              tickWidth:0, //刻度标签宽度
							labels: {
									align: 'left',
                  enabled:false
							}
					},
					yAxis: {
							visible: false
					},
					plotOptions: {
							series: {
									marker: {
											enabled: false
									},
									states: {
											hover: {
													enabled: false
											}
									}
							}
					},
					series: [{
							color: color,
							name: name,
							lineWidth: 1,
							fillColor: {
									linearGradient: {
											x1: 0,
											y1: 0,
											x2: 0,
											y2: 1
									},
									stops: [
											[0, Highcharts.Color(color).setOpacity(0.2).get('rgba')],
											[1, Highcharts.Color(color).setOpacity(0).get('rgba')]
									]
							},
							data: _data
					}]
    	};
			this.chart = new Highcharts['Chart'](
				this.chartContainer.current,
				options
			);
    }
  
    componentWillUnmount() {
      this.chart.destroy();
    }
  
    render() {
      return <div ref={this.chartContainer} />;
    }
  }
  
  

// 默认props值
MiniChart.defaultProps = {
  width: 266,
  height: 50,
  color: '#7cb5ec',
	name: '百度指数',
	showTooltip: true,
	seriesArr:  {}
}
// props 类型
MiniChart.propTypes = {
  width: PropTypes.number,
  height: PropTypes.number,
  color:PropTypes.string,
	name: PropTypes.string,
	showTooltip: PropTypes.bool,
	seriesArr: PropTypes.object
}

