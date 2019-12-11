import React, { Component } from "react";
import PropTypes from "prop-types";
import { withRouter } from "react-router-dom";
import { translate } from "react-i18next";
import StandardChart from "../../components/StandardChart";
import numeral from "numeral";
import { ThemeCardDteailsData } from "./data.js";
import moment from "moment";
import i18n from "../../i18n";
import { Icon } from "antd";
import isEmpty from "lodash/isEmpty";
import classNames from "classnames";
import NoDataTip from "../NoDataTip";
import morearrow from "../../theme/default/images/morearrow.svg";
import "./style.scss";

class ThemeCard extends Component {
  constructor(props) {
    super(props);
  }

  parseData = data => {
    let colorUp = "#dc2841",
      colorDown = "#4ca04c";
    let customConfig = {
      chart: {
        marginRight: 20
      },
      tooltip: {
        split: false,
        shared: true,
        useHTML: true,
        backgroundColor: "rgba(0,0,0,0.6)",
        borderWidth: 0,
        shadow: false,
        style: {
          color: "#fff",
          lineHeight: "18px"
        },
        formatter: function() {
          let _data = data[this.points[0].point.index];
          if (!_data) return false;
          let {
            open,
            close,
            high,
            low,
            differ,
            differ_range,
            volume,
            trade_date
          } =
            _data || {};
          let _time = moment(trade_date).format("YYYY-MM-DD");
          let _content = "";
          _content += `<div class="abc-stock-kchart-card-highcharts-tooltip">${_time}<br/>`;
          _content += `${i18n.t("StockKChartCard.open", {
            defaultValue: "开盘价"
          })}:${numeral(open).format("0,0.00")}<br/>`;
          _content += `${i18n.t("StockKChartCard.high", {
            defaultValue: "最高价"
          })}:${numeral(high).format("0,0.00")}<br/>`;
          _content += `${i18n.t("StockKChartCard.low", {
            defaultValue: "最低价"
          })}:${numeral(low).format("0,0.00")}<br/>`;
          _content += `${i18n.t("StockKChartCard.close", {
            defaultValue: "收盘价"
          })}:${numeral(close).format("0,0.00")}<br/>`;
          differ_range = parseFloat(differ_range);
          let color = differ_range > 0 ? colorUp : colorDown;
          _content += `${i18n.t("StockKChartCard.differ_range", {
            defaultValue: "涨跌幅"
          })}:<span style="color:${color};">${
            differ_range > 0 ? "+" : ""
          }${numeral(differ_range).format("0,0.00")}%</span><br/>`;
          _content += `${i18n.t("StockKChartCard.differ", {
            defaultValue: "涨跌额"
          })}:<span style="color:${color};">${
            differ_range > 0 ? "+" : ""
          }${numeral(differ).format("0,0.00")}</span><br/>`;
          _content += `${i18n.t("StockKChartCard.volume", {
            defaultValue: "成交量"
          })}:${numeral(volume / 10000).format("0,0.00")}万股<br/></div>`;
          return _content;
        }
      },
      exporting: { enabled: false },
      legend: { enabled: false },
      yAxis: [
        {
          // 主轴
          opposite: false,
          showFirstLabel: true,
          showLastLabel: true,
          title: {
            text: null
          },
          height: "70%",
          lineWidth: 1,
          lineColor: "#e6e6e6"
        },
        {
          // 成交量y坐标轴
          opposite: false,
          title: {
            text: null
          },
          labels: {
            enabled: false
          },
          top: "75%",
          height: "25%",
          offset: 0,
          lineWidth: 1,
          lineColor: "#e6e6e6"
        }
      ],
      xAxis: {
        categories: [],
        labels: {
          step: 1
        },
        tickLength: 0
        // formatter: function() {
        //   let dateArr = this.value.split('-');
        //   return `${dateArr[0]}年${dateArr[1]}月${dateArr[2]}日`
        // }
      },
      series: [
        {
          type: "area",
          data: [],
          borderColor: "#407CD5",
          fillColor: {
            linearGradient: {
              x1: 0,
              y1: 0,
              x2: 0,
              y2: 1
            },
            stops: [[0, "#7cb5ec"], [1, "#fff"]]
          },
          marker: {
            enabled: false
          },
          lineWidth: 1,
          yAxis: 0
        },
        {
          type: "column",
          colorByPoint: true,
          data: null,
          borderWidth: 0,
          yAxis: 1
        }
      ]
    };
    let area = [];
    let colunm = [];
    let categories = [];
    let min = data[0] && data[0]["close"];
    for (let i = 0; i < data.length; i++) {
      const item = data[i];
      if (item["close"] < min) min = item["close"];
      area.push({ y: item["close"] });
      colunm.push({
        y: item["volume"],
        color: item["differ"] > 0 ? "red" : "green"
      });
      categories.push(moment(item["trade_date"]).format("YYYY-MM"));
    }
    customConfig.yAxis[0].min = min;
    customConfig.xAxis.categories = categories;
    customConfig.xAxis.labels.step = Math.floor(categories.length / 5);
    customConfig.series[0].data = area;
    customConfig.series[1].data = colunm;
    return customConfig;
  };

  unitsFormat(num) {
    let oNum = isNaN(parseFloat(num)) ? 0 : parseFloat(num);
    let num_length = (Math.floor(Math.abs(oNum)) + "").length;
    if (num_length < 5) {
      return `${numeral(oNum).format("0,0.00")}`;
    } else if (num_length < 9) {
      return `${numeral(oNum / 10000).format("0,0.00")}万`;
    } else if (num_length < 13) {
      return `${numeral(oNum / 100000000).format("0,0.00")}亿`;
    } else if (num_length < 17) {
      return `${numeral(oNum / 1000000000000).format("0,0.00")}万亿`;
    }
  }

  render() {
    const { title, data, hideMore } = this.props;
    let ThemeCardChartData = data;
    let customConfig =
      ThemeCardChartData && this.parseData(ThemeCardChartData.graph);
    const differStatus =
      ThemeCardChartData && ThemeCardChartData.differ_range > 0
        ? "rise"
        : "drop";
    const mainFlowStatus =
      ThemeCardChartData.main_netin_flow !== null
        ? ThemeCardChartData.main_netin_flow > 0
          ? "rise"
          : "drop"
        : "";
    const differRange5Status =
      ThemeCardChartData.differ_range_5 !== null
        ? ThemeCardChartData.differ_range_5 > 0
          ? "rise"
          : "drop"
        : "";
    const differRange20Status =
      ThemeCardChartData.differ_range_20 !== null
        ? ThemeCardChartData.differ_range_20 > 0
          ? "rise"
          : "drop"
        : "";
    const turnoverRateStatus =
      ThemeCardChartData.turnover_rate !== null
        ? ThemeCardChartData.turnover_rate > 0
          ? "rise"
          : "drop"
        : "";
    return (
      <div className="ThemeCard-container">
        <div className="card-content">
          <p className="stock-title">
            <span className="stock-name">
              {ThemeCardChartData.index_name || "- -"} ({ThemeCardChartData.index_code ||
                "- -"})
            </span>
            <span className="stock-time">
              {ThemeCardChartData.trade_date === null
                ? "- -"
                : ThemeCardChartData.trade_date.substr(0, 10)}（北京时间)
            </span>
          </p>
          <p className={classNames("stock-title-tip", differStatus)}>
            <span className="stock-index1">
              {ThemeCardChartData.close === null
                ? "- -"
                : numeral(ThemeCardChartData.close).format("0.00")}
            </span>
            {!isEmpty(ThemeCardChartData) ? (
              <Icon
                type={
                  ThemeCardChartData.differ_range > 0
                    ? "arrow-up rise"
                    : "arrow-down drop"
                }
              />
            ) : null}
            <span className="stock-index2">
              {`${ThemeCardChartData.differ.toFixed(2)}` || "- -"}({`${ThemeCardChartData.differ_range.toFixed(
                2
              )}%` || "- -"})
            </span>
            <span className="stock-rise">
              涨：{ThemeCardChartData.rise_num || "- -"}
            </span>
            <span className="stock-down">
              跌：{ThemeCardChartData.fall_num || "- -"}
            </span>
            <span className="stock-up-flat">
              平：{ThemeCardChartData.fair_num || "- -"}
            </span>
          </p>
          <div className="stock-card-chart-container">
            <div className="stock-chart">
              {ThemeCardChartData && !isEmpty(ThemeCardChartData.graph) ? (
                <StandardChart
                  chartId="theme-card"
                  customConfig={customConfig}
                />
              ) : (
                <div className="nodata">
                  <NoDataTip />
                </div>
              )}
            </div>
            <div className="stock-chart-tip">
              <div className="ThemeCard-up">
                <p>
                  <span className="tip-name">金额</span>
                  <span className="tip-value">
                    {(ThemeCardChartData &&
                      this.unitsFormat(
                        ThemeCardChartData && ThemeCardChartData.amount
                      )) ||
                      "- -"}
                  </span>
                </p>
                <p>
                  <span className="tip-name">净资金流入</span>
                  <span className={classNames("tip-value", mainFlowStatus)}>
                    {ThemeCardChartData &&
                    ThemeCardChartData.main_netin_flow !== null
                      ? `${numeral(
                          ThemeCardChartData &&
                            ThemeCardChartData.main_netin_flow / 10000
                        ).format("0,0.00")}亿元`
                      : "- -"}
                  </span>
                </p>
                <p>
                  <span className="tip-name">成交量</span>
                  <span className="tip-value">
                    {(ThemeCardChartData &&
                      this.unitsFormat(ThemeCardChartData.volume)) ||
                      "- -"}
                  </span>
                </p>
                <p>
                  <span className="tip-name">开盘</span>
                  <span className="tip-value rise">
                    {(ThemeCardChartData &&
                      this.unitsFormat(ThemeCardChartData.open)) ||
                      "- -"}
                  </span>
                </p>
                <p>
                  <span className="tip-name">最高</span>
                  <span className="tip-value  rise">
                    {(ThemeCardChartData &&
                      this.unitsFormat(ThemeCardChartData.high)) ||
                      "- -"}
                  </span>
                </p>
                <p>
                  <span className="tip-name">最低</span>
                  <span className="tip-value rise">
                    {(ThemeCardChartData &&
                      this.unitsFormat(ThemeCardChartData.low)) ||
                      "- -"}
                  </span>
                </p>
              </div>
              <p className="divider" />
              <div className="ThemeCard-down">
                <div className="left">
                  <p>
                    <span className="tip-name">市盈率</span>
                    <span className="tip-value">
                      {ThemeCardChartData && ThemeCardChartData.pe !== null
                        ? `${ThemeCardChartData &&
                            numeral(ThemeCardChartData.pe).format("0.00")}`
                        : "- -"}
                    </span>
                  </p>
                </div>
                <div className="right">
                  <p>
                    <span className="tip-name">市净率</span>
                    <span className="tip-value">
                      {ThemeCardChartData && ThemeCardChartData.pb !== null
                        ? numeral(ThemeCardChartData.pb).format("0.00")
                        : "- -"}
                    </span>
                  </p>
                </div>
                <p>
                  <span className="tip-name">5日</span>
                  <span className={`tip-value ${differRange5Status}`}>{`${
                    ThemeCardChartData.differ_range_5 !== null
                      ? numeral(ThemeCardChartData.differ_range_5 * 100).format(
                          "0.00"
                        )
                      : "--"
                  }%`}</span>
                </p>
                <p>
                  <span className="tip-name">换手率</span>
                  <span className={`tip-value ${turnoverRateStatus}`}>
                    {ThemeCardChartData.turnover_rate !== null
                      ? `${numeral(
                          ThemeCardChartData.turnover_rate * 100
                        ).format("0.00")}%`
                      : "- -"}
                  </span>
                </p>
                <p>
                  <span className="tip-name">20日</span>
                  <span className={`tip-value ${differRange20Status}`}>{`${
                    ThemeCardChartData.differ_range_20 !== null
                      ? numeral(
                          ThemeCardChartData.differ_range_20 * 100
                        ).format("0.00")
                      : "--"
                  }%`}</span>
                </p>
              </div>
            </div>
          </div>
          <div className="footer">
            <a
              className="ThemeCard-container-more-info"
              href={`/entity-search/theme/${
                ThemeCardChartData.index_code
              }?name=${ThemeCardChartData.index_name}`}
              hidden={hideMore}
            >
              查看更多信息<img src={morearrow} alt="" />
            </a>
          </div>
        </div>
      </div>
    );
  }
}

// 默认props值
ThemeCard.defaultProps = {
  data: ThemeCardDteailsData.data,
  title: "个股行情"
};
// props 类型
ThemeCard.propTypes = {
  title: PropTypes.string
};
// export default withRouter(translate()(ThemeCard));
export default ThemeCard
