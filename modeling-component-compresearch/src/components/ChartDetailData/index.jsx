/**
 * @description 数据图
    @author ypeng
**/

import React, { Component } from "react";
import Highcharts from "highcharts";
import Exporting from "highcharts/modules/exporting";
import OfflineExporting from "highcharts/modules/offline-exporting";
import ExportData from "highcharts/modules/export-data.js";
import { localClass } from "./style.scss";

Exporting(Highcharts);
OfflineExporting(Highcharts);
ExportData(Highcharts);
export default class DataChart extends Component {
  componentDidMount() {
    if (!this.props.config) {
      return null;
    }
    this.chart = Highcharts.chart(this.chartCon, this.props.config);
  }
  componentDidUpdate() {
    if (!this.props.config) {
      return null;
    }
    this.chart = Highcharts.chart(this.chartCon, this.props.config);
  }
  exportData = () => {
    this.chart.downloadXLS();
  };
  exportPic = picName => {
    this.chart.exportChartLocal(
      {
        filename: picName,
        type: "image/jpeg"
      },
      {
        chart: {
          // width: parseInt(sizetype[0]),
          // height: parseInt(sizetype[1]),
          backgroundColor: "#FFFFFF"
        },
        // title: {text: picName},
        legend: {
          enabled: true
        }
        // series: colorseries
      }
    );
  };
  render() {
    return (<div className={localClass} ref={e => (this.chartCon = e)} >
    </div>);
  }
}
