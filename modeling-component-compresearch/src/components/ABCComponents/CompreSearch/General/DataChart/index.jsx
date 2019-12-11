/**
 * @description 数据图 组件
 * @date 2018.06.07
 * @author wsh
 */

import React, { Component } from 'react'
import { withRouter, Link } from 'react-router-dom';
import PropTypes from 'prop-types'
import moment from 'moment'
import { inject, observer } from 'mobx-react'
import _ from 'lodash'
import Highcharts from 'highcharts'
import Exporting from 'highcharts/modules/exporting';
import ExportData from 'highcharts/modules/export-data.js'
import ejson from 'mongodb-extjson';
import { Menu, Dropdown, Icon, Spin } from 'antd';
import { CardLoading } from './component/CardLoading'
import { CardError } from './component/CardError'
import { Process } from './component/Process'
import { Manual } from './component/Manual'
import { origin_report } from '../constants';
import ask from '../../../../../lib/ask'
import axios from 'axios';
import './style.scss'
import resources from '../assets/image/icons/my_resources.png'


Exporting(Highcharts)
ExportData(Highcharts)


const oririn = 'https://www.analyst.ai';
const oririn_data = 'https://charttable.analyst.ai';
const oririn_v1 = 'https://v1.analyst.ai';

@withRouter
@inject('cuttingStore')
@observer
class DataUnitCard extends Component {
  constructor(props) {
    super(props)

    this.state = {
      showRightIcon: false,
      showAuditIcon: this.props.showAuditIcon || 1,    //修改按钮状态  1：可以进行修复 2：禁用
      newestVersion: this.props.newsetVersion || null,  //最新版本号
      cardPopupType: null,
      data: this.props.data || {},
      chartloading: true,
      isBitPicture: props.data.confidence <= 0.5 ? true : false
    }
  }

  componentDidMount() {
    let { id, data_file, chart_data, confidence, image_title } = this.props.data;
    let { chartDataIdArr, data = {} } = this.props;
    let _this = this;

    _this.setState({ chartloading: false })
    if (!this.state.isBitPicture) {
      chart_data = chart_data || "";
      if (data_file) {
        axios.get(data_file.replace("http:", "https:").replace('-internal.', '.'))
        .then(function (response) {
          _this.setState({ chartloading: false })
          if (response.hasOwnProperty('chartType')) {
            chart_data = response;
          } else {
            chart_data = ejson.parse(response.request.responseText);
            chart_data = JSON.parse(ejson.stringify(chart_data, { relaxed: true }));
          }
          

          if (chart_data.confidence <= 0.5 || _.isEmpty(data_file)) {
            
            _this.setState({
              isBitPicture: true
            });
          } else {
            _this.setState({
              isBitPicture: false
            });
          }
          _this.chart = Highcharts.chart(_this.chartContainerId, chart_data.data);
          let filename = chart_data.title ? chart_data.title : image_title;
          _this.chart.update({
            title: {
              text: null
            },
            exporting: {
              filename: filename,
              enabled: false   // 关闭导出按钮
            },
            credits: {
              enabled: false // 禁用版权信息
            }
          });
        })
        .catch(function (error) {
          
          _this.setState({
            isBitPicture: true
          });
        });
      } else if (!_.isEmpty(chart_data)) {
        // debugger
        try {
          chart_data = JSON.parse(chart_data);
          _this.setState({
            isBitPicture: false
          }, () => {
            _this.chart = Highcharts.chart(_this.chartContainer, chart_data);
            let filename = chart_data.title ? chart_data.title : image_title;
            _this.chart.update({
              title: {
                text: null
              },
              exporting: {
                filename: filename,
                enabled: false   // 关闭导出按钮
              },
              credits: {
                enabled: false // 禁用版权信息
              }
            });
          });
        } catch (e) {
          console.error(e);
          _this.setState({
            isBitPicture: false
          });
        } finally {
          _this.setState({ chartloading: false });
        }
      } else {
        _this.setState({ chartloading: false });
      }
    } else {
      _this.setState({ chartloading: false });
    }
  }

  exportExcle = (e, chart_data) => {
    this.chart.downloadXLS();
  }

  mouseEnter = () => {
    this.setState({ showRightIcon: true })
  }
  mouseLeave = () => {
    this.setState({ showRightIcon: false })
  }

  auditCheck = (e, image_id, id, image_url, real_id) => {
    this.props.changeShowAuditIcon(2);
    this.setState({
      cardPopupType: 2,
    })
    if (e.target.dataset.type === 'old') {  //老版本走插队
      this.setState({
        cardPopupType: 1,
      })
      this.hasCutting(image_id, id, image_url, real_id);

    } else if (e.target.dataset.type === 'new') { //新版本走人工标注

    }
  }

  /* 判断解析插队 */
  hasCutting(image_id, id, imageUrl, real_id) {
    let info = {};
    info.source = 'hb';
    info.requester = 'Analysis.AI';
    info.imageId = image_id;
    ask("fetchHasCutting", {
      params: info
    })
      .then((resp) => {
        try {
          let { code, data } = resp;
          if (code === 200 && data.data.imported.length) {
            this.getCuttingResultCheck(data.data.imported, image_id, id, imageUrl, real_id);
          } else {
            this.props.changeShowAuditIcon(1);
            this.setState({
              cardPopupType: 2,
            });
          }
          // if (data[0] && data[0].imported.length) {
          //   let res = JSON.parse(data.res);
          //   if (res.ok == false || res.ok == 'false' || !res.data || res.data.length <= 0) {
          //     this.props.changeShowAuditIcon(1);
          //     this.setState({
          //       cardPopupType: 2,
          //     })
          //   } else {
          //     //  成功取结果
          //     this.getCuttingResultCheck(res, image_id, id, imageUrl);
          //   }
          // } else {
          //   this.props.changeShowAuditIcon(1);
          // }
        } catch (e) {
          this.props.changeShowAuditIcon(1);
          this.setState({
            cardPopupType: 2,
          })
        }
      }, (err) => {
        console.error(err);
        this.props.changeShowAuditIcon(1);
        this.setState({
          cardPopupType: 2,
        })
      })
  }

  /* 查询解析状态过程 */
  getCuttingResultCheck(data, imageId, id, imageUrl, realId) {
    let runCount = 10,
      siv,
      self = this;
    if (!data.length) {
      // 失败
      this.setState({
        cardPopupType: 2,
      })
      this.props.changeShowAuditIcon(1);
    } else {
      siv = setInterval(() => {
        this.getResultStatus(imageId, (res) => {
          --runCount;
          if (!res || (res.data[0] && res.data[0]['state'] === 1) || runCount <= 0) {
            // 失败
            this.setState({
              cardPopupType: 2,
            })
            clearInterval(siv);
            this.props.changeShowAuditIcon(1);
          } else {
            if (res.data[0] && res.data[0]['state'] === 2) {
              this.props.changeShowAuditIcon(1);
              let info = {};
              info.source = 'hb';
              info.dataId = imageId;
              info.url = imageUrl;

              /* 解析成功去获取单条数据 */
              this.getBGData(imageId, id, realId, (data) => {
                let _this = this;
                let { data_file, chart_data, image_title } = this.props.data;

                if (_.isEmpty(data)) {
                  this.setState({
                    cardPopupType: 2,
                  })
                } else {
                  //成功
                  data = data || {};
                  // 获取数据
                  if (data.data_file !== '') {
                    axios.get(data.data_file.replace("http:", "https:"))
                      .then(function (response) {
                        _this.props.changeShowAuditIcon(3);
                        chart_data = ejson.parse(response.request.responseText);
                        chart_data = JSON.parse(ejson.stringify(chart_data, { relaxed: true }));

                        if (chart_data.confidence <= 0.5 || _.isEmpty(data_file)) {
                          
                          _this.setState({
                            isBitPicture: true,
                            cardPopupType: 2,
                            chartloading: false
                          });
                          return;
                        } else {
                          _this.setState({
                            isBitPicture: false,
                            cardPopupType: null,
                            chartloading: false
                          })
                        }

                        _this.chart = _this.chartContainerId && Highcharts.chart(_this.chartContainerId, chart_data.data);
                        let filename = chart_data.title ? chart_data.title : image_title;
                        _this.chart.update({
                          title: {
                            text: null
                          },
                          exporting: {
                            filename: filename,
                            enabled: false   // 关闭导出按钮
                          },
                          credits: {
                            enabled: false // 禁用版权信息
                          },
                          legend: {
                            enabled: false //禁用图例
                          }
                        });
                      })
                      .catch(function (error) {
                        
                        _this.setState({
                          cardPopupType: 2,
                          isBitPicture: true
                        });
                        _this.props.changeShowAuditIcon(3);
                      });
                  } else {
                    
                    // data_file为空
                    _this.setState({
                      cardPopupType: 2,
                      isBitPicture: true
                    });

                  }

                }
                _this.props.changeShowAuditIcon(3);
              });
              clearInterval(siv);
            } else {
              /* 解析失败判断是否可以进行人工标注 */


            }
          }
        })
      }, 2000);
    }

  }

  /* 查询解析状态结果*/
  getResultStatus(imageId, callback) {
    let info = {};
    info.source = 'hb';
    info.requester = 'Analysis.AI';
    info.imageId = imageId;
    // info = JSON.stringify(info);

    ask("fetchGetCuttingResult", {
      params: info
      // headers: {
      //   "Content-Type": "application/json; charset=UTF-8"
      // }
    })
      .then((resp) => {
        let { code, data } = resp;
        callback(data);
      }, (err) => {
        console.error(err);
        this.setState({
          cardPopupType: 2,
        })
      })
  }

  /* 获取搜图单条数据 */
  getBGData(imageId, id, realId, callback) {
    realId = realId ? realId : id.split('!')[1];
    ask("fetchGetGBData", {
      params: {
        id,
        imageId,
        realId,
        source: 'hb',
        requester: 'Analysis.AI'
      }
    })
      .then((resp) => {
        let { code, data } = resp;
        if (data) {
          callback(data);
        } else {
          this.setState({
            cardPopupType: 2,
          })
        }

      }, (err) => {
        console.error(err);
        this.setState({
          cardPopupType: 2,
        })
      })
  }


  /* 判断是否可以进行人工标注 */
  hasArtificial(param, imageId, callback) {
    ask("fetchHasArtificial", {
      params: {
        post_json: param
      }
    })
      .then((resp) => {
        let { code, data } = resp;
        data = JSON.parse(data.res);
        if (callback && typeof (callback) === "function") {
          callback(data);
        } else {
          if (data.code === 0) {
            // window.location.href = data.data + '?userid=' + window['USER_ID'] + '&imageid=' + imageId;  //todo 暂时不进入人工标注，临时失败
            this.setState({
              cardPopupType: 2,
            })
          } else if (data.code = 103) {
            this.setState({
              cardPopupType: 3,
            })
          } else {
            this.setState({
              cardPopupType: 2,
            })
          }
        }

      }, (err) => {
        console.error(err);
        this.setState({
          cardPopupType: 2,
        })
      })
  }
  auditIcon(isBitPicture, bitmap_ver, chart_type, image_id, id, image_url, real_id) {

    // let oldVersionCode = ['BAR_CHART', 'COLUMN_CHART', 'COLUMN_CHART+LINE_CHART', 'COLUMN_CHART+LINE_POINT_CHART', 'LINE_CHART', 'LINE_POINT_CHART', 'PIE_CHART', 'AREA_OVERLAP_CHART', 'AREA_CHART'];
    // let newVersionCode = ['BAR_CHART', 'COLUMN_CHART', 'COLUMN_CHART+LINE_CHART', 'COLUMN_CHART+LINE_POINT_CHART', 'LINE_CHART', 'LINE_POINT_CHART', 'PIE_CHART'];
    let oldDom = <a href="javascript:void(0)"><Icon type="tool" className="audit" data-type="old" onClick={(e) => this.auditCheck(e, image_id, id, image_url, real_id)} style={{ fontSize: 17, color: "#333", marginRight: "10px" }} /></a>;
    let newDom = <a href="javascript:void(0)"><Icon type="tool" className="audit" data-type="new" onClick={(e) => this.auditCheck(e, image_id)} style={{ fontSize: 17, color: "#333", marginRight: "10px" }} /></a>;
    let disabledDom = <Icon type="tool" className="audit" style={{ fontSize: 17, color: "#ccc", marginRight: "10px" }} />;
    let auditDom;
    let { newestVersion } = this.state;
    let { showAuditIcon } = this.props;
    if (isBitPicture) {   //是否为位片
      if (showAuditIcon === 1) {
        if ((bitmap_ver - 0) !== newestVersion) { //不是最新版本
          switch (chart_type) {
            case 'BAR_CHART':
              auditDom = oldDom;
              break;
            case 'COLUMN_CHART':
              auditDom = oldDom;
              break;
            case 'COLUMN_CHART+LINE_CHART':
              auditDom = oldDom;
              break;
            case 'COLUMN_CHART+LINE_POINT_CHART':
              auditDom = oldDom;
              break;
            case 'LINE_CHART':
              auditDom = oldDom;
              break;
            case 'LINE_POINT_CHART':
              auditDom = oldDom;
              break;
            case 'PIE_CHART':
              auditDom = oldDom;
              break;
            case 'AREA_OVERLAP_CHART':
              auditDom = oldDom;
              break;
            case 'AREA_CHART':
              auditDom = oldDom;
              break;
            default:
              auditDom = '';
          }
        } else { //是最新版本  TODO:目前不做人工标注所以留空
          switch (chart_type) {
            case 'BAR_CHART':
              // auditDom = newDom;  
              auditDom = '';
              break;
            case 'COLUMN_CHART':
              // auditDom = newDom;
              auditDom = '';
              break;
            case 'COLUMN_CHART+LINE_CHART':
              // auditDom = newDom;
              auditDom = '';
              break;
            case 'COLUMN_CHART+LINE_POINT_CHART':
              // auditDom = newDom;
              auditDom = '';
              break;
            case 'LINE_CHART':
              // auditDom = newDom;
              auditDom = '';
              break;
            case 'LINE_POINT_CHART':
              // auditDom = newDom;
              auditDom = '';
              break;
            case 'PIE_CHART':
              // auditDom = newDom;
              auditDom = '';
              break;
            default:
              auditDom = '';
          }
        }
      } else if (showAuditIcon === 2) {
        auditDom = disabledDom;
      } else {
        auditDom = '';
      }

    } else {
      auditDom = '';
    }

    return auditDom;
  }

  cardPopup(type, data) {
    let cardPopupDom = '';
    switch (type) {
      case 1:
        cardPopupDom = <CardLoading />     //Loading
        break;
      case 2:
        cardPopupDom = <CardError />     //解析失败
        break;
      case 3:
        cardPopupDom = <Process />       //审核中
        break;
      case 4:
        cardPopupDom = <Manual url='http://baidu.com' /> //手动解析（人工标注）
        break;
      default:
        cardPopupDom = ''

    }
    if (type === 2) {
      setTimeout(() => {
        this.setState({
          cardPopupType: null,
        })
      }, 3000)
    }
    return cardPopupDom;
  }

  render() {
    let { cardPopupType, isBitPicture } = this.state;
    let { id, image_title, type, title, time, chart_data, confidence, image_url, publish, source_type, source_title, author, source_url,
        bitmap_ver, chart_type, image_id, source_id: file_id = "", real_id, chart_source_type } = this.state.data;
    let { chartDataIdArr, data = {} } = this.props;
    id = id ? id : new Date().getTime();
    this.chartContainerId = `chart-container-${id}`;

    if (data.confidence >= 0.6 && data.chart_data && Object.prototype.toString.apply(data.chart_data).indexOf('String') != -1) {
      try {
        chart_data = JSON.parse(data.chart_data);
      } catch (e) {
        console.error(data.chart_data);
      } finally {
        chart_data = {};
      }
    }
    const menu = (
      <Menu>
        {
          !isBitPicture &&
          <Menu.Item key="0">
            <a onClick={(e) => this.exportExcle(e, chart_data)}>导出</a>
          </Menu.Item>
        }
        <Menu.Item key="1">
          <a href={image_url} target="_blank">查看原图</a>
        </Menu.Item>
      </Menu>
    );

    return (
      <div className="DataUnitCard" onMouseEnter={this.mouseEnter} onMouseLeave={this.mouseLeave}>
        {
          this.state.showRightIcon &&
          <div className="pos-right">
            {this.auditIcon(isBitPicture, bitmap_ver, chart_type, image_id, id, image_url, real_id)}
            <Dropdown overlay={menu} placement="bottomRight" >
              <a className="ant-dropdown-link" href={"javascript:void(0)"}>
                <Icon type="bars" />
              </a>
            </Dropdown>
          </div>
        }
        <div className="unitUrl" href={source_url}>
          <div className="title">
            <h3>
              <strong dangerouslySetInnerHTML={{ __html: image_title || title }}></strong>
            </h3>
            <p>{moment(time * 1000).format('YYYY/MM/DD')}</p>
          </div>
          {
            !isBitPicture &&
            (
              this.state.chartloading
                ?
                (<div className="unitCardPic"><Spin size="large" /></div>)
                :
                (<a href={`/chartDetail?id=${real_id||id}&type=${this.props.type}`} target="_blank">  <div className="unitCardPic" id={this.chartContainerId} ref={e => this.chartContainer = e}></div></a>)
            )
          }
          {
            isBitPicture &&
            <a href={`/chartDetail?id=${real_id||id}&type=${this.props.type}`} target="_blank">
              <div className="unitCardPic" id={this.chartContainerId} ref={e => this.imgContainer = e}>
                <img src={image_url} alt="" />
              </div>
            </a>
          }
          <div className="sourceType" >
            <p>来源：<a href={`/report-detail/article/${file_id}`} title={source_title} target="_blank" dangerouslySetInnerHTML={{ __html: source_title }}></a></p>
            <p>类别：{type}</p>
            <p>作者：<span dangerouslySetInnerHTML={{ __html: author }}></span></p>
            {!this.props.myChart && chart_source_type && chart_source_type === 'modelingChart' && <p className='my_resources'><img src={resources}/><span>我的资源</span></p>}
          </div>
        </div>
        <div className="cardPopup">
          {cardPopupType && this.cardPopup(cardPopupType)}
        </div>
      </div>

    )
  }

}

// props 类型
DataUnitCard.propTypes = {
  data: PropTypes.object
}
export default DataUnitCard

