import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import { inject, observer } from 'mobx-react';
import { Affix, Modal, Spin } from 'antd';
import moment from 'moment';
import Cookie from 'js-cookie';
import { getMailDetail } from 'config/mail';
import { downloadFile,downloadAllFiles,getAttachDetail } from 'config/attach';
import FileViewer from 'components/FileViewer';
import moren from './moren.png';
import './detail.scss';

const FILE_TYPE = ['UNKNOWN', 'EXCEL', 'PDF', 'IMAGE', 'WORD', 'TEXT', 'PPT'];

@withRouter @inject('listStore') @observer
class MailDetail extends Component {
  constructor() {
    super();
    this.state = {
      dataset: {},
      fileIdArr: [],
      fileNameArr: [],
      visible: false,
      loading: true,
      count: 3,
        attachId: 0,
        attachName: '',
        showmore:false,
        downId:''
    };
    this.downloadAll = this.downloadAll.bind(this);
    this.showMore = this.showMore.bind(this);
    this.showDown = this.showDown.bind(this);
    this.leaveDown = this.leaveDown.bind(this);
  }
  showDown(id){
    this.setState({downId:id});
  }
  leaveDown(){
    this.setState({downId:''});
  }
    downloadAll= async()=>{
        let dataset = this.props.dataset;
        let _attaches = dataset ? dataset.attachments : [];
        let fileIdArr = [];
        let fileNameArr = [];
        if (_attaches && _attaches.length === 1 && _attaches[0]){
            let onlyAttach = _attaches[0];
            // const response = await downloadFile({ file_id:onlyAttach.fileId,record_id:dataset.id })
            // // 创建隐藏的可下载链接
            // var eleLink = document.createElement('a');
            // eleLink.download = onlyAttach.name;
            // eleLink.style.display = 'none';
            // // 字符内容转变成blob地址
            // var blob = new Blob([response]);
            // eleLink.href = URL.createObjectURL(blob);
            // // 触发点击
            // document.body.appendChild(eleLink);
            // eleLink.click();
            // // 然后移除
            // document.body.removeChild(eleLink);
            // var a = document.createElement('a');
            // a.href = window.URL.createObjectURL(response)
            // a.download = _splitName[_splitName.length - 1];
            // document.body.appendChild(a);
            // a.click();
            // a.remove();
             window.open(
            '/resapi/view/attachUrl?user_id=' +
            Cookie.get('userId') +
            '&file_id=' + onlyAttach.fileId +
            '&record_id=' + dataset.id);
             return ;
        } else if (_attaches) {
          window.open(
            '/resapi/view/attachMutiUrl?user_id=' +
            Cookie.get('userId') +
            '&record_id=' + dataset.id);
            // _attaches.map((attach, index) => {
            //     fileIdArr.push(attach.fileId);
            //     fileNameArr.push(encodeURIComponent(attach.name));
            //     return null;
            // })
            // const response = await downloadAllFiles({record_id:dataset.id })
            // var eleLink = document.createElement('a');
            // eleLink.download = 'xx.zip';
            // eleLink.style.display = 'none';
            // // 字符内容转变成blob地址
            // var blob = new Blob([response]);
            // eleLink.href = URL.createObjectURL(blob);
            // // 触发点击
            // document.body.appendChild(eleLink);
            // eleLink.click();
            // // 然后移除
            // document.body.removeChild(eleLink);
        }
        // window.open(
        //       '/resapi/view/attachMutiUrl?user_id=' +
        //       Cookie.get('userId') +
        //       '&title=' + encodeURIComponent(dataset.subject) +
        //       '&file_id=' +
        //       JSON.stringify(fileIdArr) +
        //       '&file_name=' +
        //       JSON.stringify(fileNameArr)
        //   )
        }
        download = async(e,attach)=>{
          e.stopPropagation();
          e.preventDefault();
          window.open(
            '/resapi/view/attachUrl?user_id=' +
            Cookie.get('userId') +
            '&file_id=' + attach.fileId +
            '&record_id=' + this.props.dataset.id);
            // const response = await downloadFile({ file_id:attach.fileId,record_id:this.props.dataset.id })
            // // 创建隐藏的可下载链接
            // var eleLink = document.createElement('a');
            // eleLink.download = attach.name;
            // eleLink.style.display = 'none';
            // // 字符内容转变成blob地址
            // var blob = new Blob([response]);
            // eleLink.href = URL.createObjectURL(blob);
            // // 触发点击
            // document.body.appendChild(eleLink);
            // eleLink.click();
            // // 然后移除
            // document.body.removeChild(eleLink);
        
        }

  // shouldComponentUpdate(nextProps) {
  //   if (nextProps.match.params.id === this.props.match.params.id) {
  //     return false
  //   }
  //   return true
  // }

  // async componentDidUpdate(prevProps, prevState) {
  //   await this._getMailDetail.call(this)
  // }

  // async _getMailDetail() {
  //   const id = this.props.match.params.id
  //   const response = await getMailDetail(id)
  //   this.setState({
  //     dataset: response.data
  //   })
  // }

  // componentDidUpdate() {
  //   const { dataset } = this.props
  //   const doc = document.getElementById('detail-iframe').contentWindow.document
  //   doc.open()
  //   doc.write(dataset.content_html)
  //   doc.close()
  // }

    showModal = async(attach, iId) => {
      let types = [1,2,4,6,7];
      if (types.indexOf(attach.fileType) !== -1){
        let _open = window.open();
        const respData = await getAttachDetail({
            file_id: attach.fileId,
            id: iId
        });
        if (respData && respData.success){
          _open.location.href = respData.data.file_url;
        } else {
          // message.error(respData.message)
          _open.location.href = '/waitMes';
        }
      } else {
        this.setState({
          visible: true,
          attachId: attach.fileId,
          attachName: attach.name
        });
      }
    }
    showMore(num){
      this.setState({showmore:!this.state.showmore},()=>{
        if (this.state.showmore){
          document.querySelector('.mail-content-receiveuser').style.height = 'auto';
          document.querySelector('.mail-receiveusers').style.width = '1120px';
          document.querySelector('.mail-content-receiveuser').style.width = '1120px';
        } else {
          document.querySelector('.mail-content-receiveuser').style.height = '17px';
          document.querySelector('.mail-receiveusers').style.width = '720px';
          document.querySelector('.mail-content-receiveuser').style.width = '650px';
        }
      });
     
    }
  toPrev = async () => {
    const { listStore } = this.props;
    const selectedIndex = listStore.selectedIndex;
    const list = listStore.mailList;

    if (selectedIndex === 0) return;
    const prevItem = list[selectedIndex - 1];
    const response = await getMailDetail({
      id: prevItem.id
    });
    listStore.changeSelectedIndex(selectedIndex - 1);
    listStore.changeSelected(response.data);
  }

  toNext = async () => {
    const { listStore } = this.props;
    const selectedIndex = listStore.selectedIndex;
    const list = listStore.mailList;

    if (selectedIndex === list.length - 1) return;
    const nextItem = list[selectedIndex + 1];
    const response = await getMailDetail({ id: nextItem.id });
    listStore.changeSelectedIndex(selectedIndex + 1);
    listStore.changeSelected(response.data);
  }

  toggleAttachCount = () => {
    const { dataset } = this.props;
    this.setState((prevState) => {
      prevState.count = prevState.count < 4 ? dataset.attachments.length : 3;
      return prevState;
    });
  }
  showAttach = () => {
    document.getElementById('attach-show').scrollIntoView();
  }
  render() {
    const { dataset } = this.props;
    const _attaches = (dataset.attachments && !!dataset.attachments.length && dataset.attachments.slice(0, this.state.count)) || [];

    const attaches = _attaches.map((attach, index) => {
      // const content = dataset.attachments.filter(
      //   _item => _item.fileId === attach.fileId
      // )
      // let attach_content = null
      // if (content.length) {
      //   attach_content = content[0].simple_content
      // }
      return (
        <div className="mail-detail-file" key={index} >
          {/* 单个文件具体内容 */}
          <div className="mail-detail-attaches" onClick={()=>this.showModal(attach,dataset.id)} onMouseEnter={()=>this.showDown(attach.fileId)} onMouseLeave={this.leaveDown}>
          <svg className="icon-file">
            <use xlinkHref={'#icon-file' + (FILE_TYPE[attach.fileType == 7 ? 6 : attach.fileType] ? FILE_TYPE[attach.fileType == 7 ? 6 : attach.fileType] : FILE_TYPE[0])} />
          </svg>
            <div className="mail-attach-info">
              <h5 dangerouslySetInnerHTML={{__html:attach.name}}></h5>
              <p className="simple-content" style={{ display: attach.content ? 'block' : 'none' }} dangerouslySetInnerHTML={{__html:attach.content}}>
              </p>
            </div>
            <div className="right">
            {this.state.downId == attach.fileId ? <i className="iconfont" onClick={(e)=>{this.download(e,attach);}}>&#xe625;</i> : <p className="kong"></p>}
            <p className="attach-size">{attach.size / 1024 >= 1024 ? (attach.size / (1024 * 1024)).toFixed(2) + 'MB' : (attach.size >= 1024 ? (attach.size / 1024).toFixed(2) + 'KB' : attach.size + 'B')}</p>
            </div>
          </div>
        </div>
      );
    });

    return (
      <div className="mail-detail-affix">
        <div style={{ textAlign: 'center',background:'#f8f8f8'}}>
          <Spin spinning={!dataset.id} />
        </div>
        <div
          className="mail-detail-content"
          style={{ display: !dataset.id ? 'none' : 'block' }}
        >
          {/* 邮件标题 */}
          <div className="mail-detail-title">
           
            <div className="mail-content-title">{dataset.subject}</div>
          </div>

          {/* 用户部分 */}
          <div className="mail-content-user">
          <img src={moren} className="icon-concat"/>
            <div className="mail-info">
              <div className="mail-content-userInfo">
                <span className="mail-content-userName">
                  {dataset.fromname}&nbsp;&nbsp;
                </span>
                <span className="mail-content-userMail">
                  {'<'}
                  {dataset.frommail}
                  {'>'}
                </span>
              <div className="mail-content-date">
                {/* {moment(+dataset.time).utcOffset(-480).add(1, 'days').format('YYYY-MM-DD HH:mm:ss')} */}
                {moment(dataset.time).format('YYYY-MM-DD HH:mm:ss')}
              </div>
              {dataset.has_attachment ? <span className="mail-attach-num" onClick={this.showAttach}>
                <i className="iconfont">&#xe697;</i>
                <span>{dataset.attachments ? dataset.attachments.length : 0}</span>
              </span> : ''}
              </div>
              <div className="mail-receiveusers">
              <div className="mail-content-receiveuser">
                <span>收件人：</span>{dataset.to && dataset.to.map((item,index)=>{
                    return <span key={index}>{item.name + '：' + item.mail + ' ;'}&nbsp;</span>;
                })}
                
              </div>
              <div className="more">{dataset.to && dataset.to.length > 3 ? <span onClick={()=>{this.showMore(dataset.to.length);}}>{this.state.showmore ? <span>收起<i
                        className="iconfont icon-xiala3"
                        style={{
                          display: 'inline-block',
                          fontSize: '12px',
                          marginLeft:'5px',
                          transform:'rotate(180deg)'
                        }}
                      /></span> : <span>展开更多<i
                        className="iconfont icon-xiala3"
                        style={{
                          display: 'inline-block',
                          fontSize: '12px',
                          marginLeft:'5px'
                        }}
                      /></span>}</span> : ''}</div>
              </div>
            </div>
            {/* <div className="change-item">
              <a onClick={this.toPrev}>
                <i
                  className="iconfont icon-jiantou"
                  style={{
                    width: '16px',
                    height: '16px',
                    display: 'inline-block',
                    transform: 'rotate(180deg)',
                    paddingBottom: '24px',
                    color: '#cccccc'
                  }}
                />
              </a>
              <a onClick={this.toNext}>
                <i
                  className="iconfont icon-jiantou"
                  style={{
                    width: '16px',
                    height: '16px',
                    display: 'inline-block',
                    color: '#999999'
                  }}
                />
              </a>
            </div> */}
          </div>

          {/* 具体文件内容 */}
          <div className="mail-detail-text" dangerouslySetInnerHTML={{__html:dataset.content}}>
          </div>
        
          {/* 文件部分 */}
          <div className="file-content">
            <p className="top">
              {
                dataset.attachments && dataset.attachments.length >= 1 && (
                  <a className="download-more" onClick={this.downloadAll}>
                    { dataset.attachments.length > 1 ? '全部下载' : '下载' }
                  </a>
                )
              }
              {dataset.attachments && dataset.attachments.length > 0 ? (
                <a onClick={this.toggleAttachCount} id="attach-show" style={{color:dataset.attachments.length > 3 ? '#3A6BB1' : '#333'}}>
                  附件({dataset.attachments.length})
                  {dataset.attachments.length > 3 ?
                      <i
                        className="iconfont icon-xiala3"
                        style={{
                          display: 'inline-block',
                          fontSize: '12px',
                          marginLeft:'5px',
                          transform: this.state.count === 3 ? 'rotate(0deg)' : 'rotate(180deg)'
                        }}
                      /> : '' }
                </a>
              ) : null}</p>
          {attaches}
          
            <div className="more-attach">
              

              
            </div>
            </div>
            </div>
        <Modal
          title={this.state.attachName}
          visible={this.state.visible}
          onCancel={() => this.setState({ visible: false })}
          className="viewFileModal"
          wrapClassName="vertical-center-modal"
        >
            <FileViewer mailId={dataset.id} attachId={this.state.attachId} attachName={this.state.attachName}/>
        </Modal>
      </div>
    );
  }
}

export default MailDetail;
