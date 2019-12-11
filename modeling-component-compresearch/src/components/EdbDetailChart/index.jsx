import React, { Component } from "react";
import "./index.scss";
import ChartEditor from "abc-chart-editor";
import TestData from "./testData";
import "abc-chart-editor/build/lib/css/main.css";
class EdbDetailChart extends Component {
  constructor(props) {
    super(props);
    this.state = {
      dataConf: TestData.edbChartDatas,
      ChartEditorConfig:{
        id: "chart-editor",
        dType: "datetime",
        viewMode: "chart",
        mapType: "",
        highData: "",
        bubble: "",
        //自定义配置
        showTimeDuring: false, //是否展示时间范围
        showType: false, //是否展示图表切换
        showIndexEditor: false,
        showIndexPanel: false,
        showEditorPanel: false,
        customConfig: null,
        showTopToolbar: false,
        showLabel: true,
        showButtonGroup: true,
        showChartToolbar: true,
        showTimeDuring: false,
        showCalendar: false,
        showDataChart: false, //是否展示 图表 数据切换功能
        //图表区域显示隐藏
        showChartTitle: true,
        showAbbreviatedAxis: true,
        showIndexEditor: false, //是否展示指标编辑列表
        custIndexColumns: null,
        chartWidth: 1300,
        chartConf_pre: null,
        chartConf_next: null,
        showChartToolbar: null,
        //高级功能
        timePlay: false,
        timeInterval: 1000
      }
    };
  }
  ExportChart = () => {
    this.charteditor.exportData();
  };
  handleData = (data) => {
      let edbChartDatas=[];
      (data || []).map((item, index) => {
      edbChartDatas.push({
        time: item.date,
        value: item.value
      });
    });
    return edbChartDatas
  };
  render() {
    let {data,name,unit,frequency,source,country,area} =this.props;
    let {ChartEditorConfig}= this.state
    console.log("this is props" , this.props.unit);
    console.log('unit',unit);
    let customConfig={
        yAxis: [{ title: { text: `单位(${this.props.unit})` } }]
    }
    console.log('ChartEditorConfig-',ChartEditorConfig);
    let indexConf = [
      {
        title:name,
        custTitle: `${name}:(${unit})`,
        chartType: "column",
        period:frequency||'月',
        yAxis: 0,
        unit: unit||'%',
        origin:source||'国家统计局',
        startTime: "2008-12-31",
        endTime: "2014-12-31",
        xKey: "date",
        yKey: "value",
        country: country||'中国',
        area: area||'亚洲'
      }
    ];
    let newData = data.map((item)=>{
      return {
        date:item.date,
        value:Number(item.value)
      }
    })
    return (
      <div className="edbDetailChart_components">
        <div className="title">
          <div>{name}</div>
          <nav>
            <span
              className="edb-chart-download"
              onClick={() => this.ExportChart()}
            >
              导出
            </span>
          </nav>
          <nav>
            <span className="share_button">分享</span>
          </nav>
        </div>
        <div>
          {
            ChartEditorConfig&&indexConf && newData && (
            <ChartEditor
              {...ChartEditorConfig}
              indexConf={indexConf}
              dataConf={newData}
              customConfig={customConfig}
              ref={ce => {
                this.charteditor = ce;
              }}
            />
          )}
        </div>
      </div>
    );
  }
}

export default EdbDetailChart;
