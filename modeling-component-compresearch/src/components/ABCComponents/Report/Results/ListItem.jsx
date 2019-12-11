import React, { Component } from 'react';
import { List, Modal } from 'antd';
import PropTypes from 'prop-types';
import moment from 'moment';
import Cookie from 'js-cookie';
import { inject, observer } from 'mobx-react';
import { getMailDetail } from 'config/mail';
import FileViewer from 'components/FileViewer';
import { getAttachDetail } from './../../config/attach';
import './listItem.scss';
// import jsCookie from 'js-cookie'

const FILE_TYPE = ['UNKNOWN', 'EXCEL', 'PDF', 'IMAGE', 'WORD', 'TEXT', 'PPT'];

@inject('listStore')
@observer
class MailListItem extends Component {
  constructor() {
    super();
    this.state = {
      visible: false,
      activeItem: 0,
      attachId: 0,
      attachName: '',
      count: 3,
      num:0,
      downId:''
    };
    this.showDown = this.showDown.bind(this);
    this.leaveDown = this.leaveDown.bind(this);
  }

  static propTypes = {
    item: PropTypes.object
  }

  // componentDidMount () {
  //   const { location } =  this.props
  //   const id = location.pathname.split('/')[2]
  //   const { item, index, listStore } = this.props
  //   if (item.id === id) {
  //     listStore.changeSelected(index)
  //   }
  // }

  showModal = async(e, attach, iId) => {
    e.stopPropagation();
    e.preventDefault();
    // if(!attach.file_type) {
    //   // this.setState({num:1})
    //   return 
    // }
    let types = [1,2,4,6,7];
    if (types.indexOf(attach.file_type) !== -1){
      let _open = window.open();
      const respData = await getAttachDetail({
          file_id: attach.fid,
          id: iId
      });
      if (respData.success){
        _open.location.href = respData.data.file_url;
      } else {
        // message.error(respData.message)
        _open.location.href = '/waitMes';
      }
    } else {
      this.setState({
        num:1,
        visible: true,
        attachId: attach.fid,
        attachName: attach.name
      });
    }
    
  }
  showDown(id){
    this.setState({downId:id});
  }
  leaveDown(){
    this.setState({downId:''});
  }
  download = async(e,attach,id)=>{
    e.stopPropagation();
    e.preventDefault();
    window.open(
      '/resapi/view/attachUrl?user_id=' +
      Cookie.get('userId') +
      '&file_id=' + attach.fid +
      '&record_id=' + id);
      // const response = await downloadFile({ file_id:attach.fid,record_id:this.props.dataset.id })
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

  changeSelected = async (e, item, index) => {
    if (this.state.num) return;
    e.stopPropagation();
    const id = item.id;
    window.open('/emaildetail?id=' + id);
    const { listStore } = this.props;
    const response = await getMailDetail({ id });
    listStore.changeSelected(response.data);
    listStore.changeSelectedIndex(index);
    // 浏览器阻止弹出
    // let w=window.open()
    // setTimeout(function(){
    //   w.location='/emaildetail?id='+id
    // },500)
    // if(a==null){
    //   alert('新窗口看起来是被一个弹出窗口拦截程序所阻挡。 如果想打开新窗口，我们建议您将本站点加入到这个拦截程序设定的允许弹出名单中。有的弹出窗口拦截程序允许在长按Ctrl键时可以打开新窗口。')
    // }
    // window.location.href='/emaildetail?id='+id
  }

  toggleAttachCount = (e) => {
    e.stopPropagation();
    e.preventDefault();
    const { item } = this.props;
    this.setState((prevState) => {
      prevState.count = prevState.count === 3 ? item.attachments.length : 3;
      return prevState;
    });
  }

  render() {
    const { item, index, listStore } = this.props;
    const _attaches = (item && item.attachments && !!item.attachments.length && item.attachments.slice(0, this.state.count)) || [];
    const attaches = _attaches.map((attach, index) => {
      const content = item.attachments.filter(
        _item => _item.fid === attach.fid
      );
      let attach_content = null;
      if (content.length) {
        attach_content = content[0].simple_content;
      }
      return (
        <div
          className="mail-attach"
          key={index}
          onClick={(e) => this.showModal(e, attach,item.id)}
          onMouseEnter={()=>this.showDown(attach.fid)}
          onMouseLeave={this.leaveDown}
        >
          

          <div className="attach-info">
            <div className="attach-title"><h5 dangerouslySetInnerHTML={{__html:attach.name}}></h5>
            <svg className="icon">
            <use xlinkHref={'#icon-file' + FILE_TYPE[attach.file_type == 7 ? 6 : attach.file_type]} />
          </svg>
            </div>
            
            <p className="simple-content" style={{display: attach.content ? 'block' : 'none'}} dangerouslySetInnerHTML={{__html:attach.content}}></p>
          </div>
          <div className="right">
            <p className="attach-size">{attach.size / 1024 >= 1024 ? (attach.size / (1024 * 1024)).toFixed(2) + 'MB' : (attach.size >= 1024 ? (attach.size / 1024).toFixed(2) + 'KB' : attach.size + 'B')}</p>
            {this.state.downId == attach.fid ? <i className="iconfont" onClick={(e)=>{this.download(e,attach,item.id);}}>&#xe625;</i> : <p className="kong"></p>}
            </div>
        </div>
      );
    });
    let returnPs = () => {
      return  <p className="title" dangerouslySetInnerHTML={{__html:this.state.attachName}}></p>;
  };
    return (
      
      <List.Item
        className={
          'mail-item ' + (item && item.id === listStore.selectedItem.id ? 'active' : '')
        }
        onClick={(e) => this.changeSelected(e, item, index)}
        target="_blank"
      >
        {/* <svg className="icon mail-icon">
          <use xlinkHref="#icon-mail" />
        </svg> */}
        <div className="mail-detail">
          <a
            className="mail-title"
          >
            <span className="mail-subject" dangerouslySetInnerHTML={{__html:item && item.subject}}></span>
            
          </a>
          {item && item.content ? <p className="mail-desc" dangerouslySetInnerHTML={{__html:item.content}}></p> : <p className="mail-descnone"></p>}
          <div className="mail-attaches">
            {attaches}
            {
              item && item.attachments.length > 3
                ? (
                  <div className="more-attach">
                    <a onClick={(e)=>{this.toggleAttachCount(e);}}>
                      {/* {item.attachments.length}个附件 */}
                      {this.state.count === 3 ? '查看' : '收起'}
                      <i
                        className="iconfont icon-xiala3"
                        style={{
                          display: 'inline-block',
                          fontSize: '12px',
                          transform: this.state.count === 3 ? 'rotate(0deg)' : 'rotate(180deg)',
                          marginLeft:2
                        }}
                      />
                    </a>
                  </div>
                )
                : null
            }
          </div>
          <div className="mail-sender">
            发件人：
            {/* <svg className="icon-sender">
              <use xlinkHref="#icon-contacts" />
            </svg> */}
            <p className="fajian">
            <span dangerouslySetInnerHTML={{__html:item && item.from_name}}></span>&nbsp;&nbsp;
            <span dangerouslySetInnerHTML={{__html:item && item.from_mail}}></span></p>
            <span className="stand"></span>
            收件人：
            {
              listStore.search_words ?
              <div className="shoujian">
              {
                item && item.hit_toes.length > 0 ? (item && item.hit_toes.length == 1 ?
                  <p><span dangerouslySetInnerHTML={{__html:item && item.hit_toes[0].name}}></span>&nbsp;&nbsp;
            <span dangerouslySetInnerHTML={{__html:item && item.hit_toes[0].mail}}></span>{item && item.toes.length + item.hit_toes.length > 1 ? `等人共${item && item.toes.length + item.hit_toes.length}人` : ''}</p> :
                <p>{item && item.hit_toes.map((_item,_index)=>{
                  return <p key={_index}><span dangerouslySetInnerHTML={{__html:_item.name}}></span>&nbsp;&nbsp;
            <span dangerouslySetInnerHTML={{__html:_item.mail}}></span>&nbsp;&nbsp;</p>;
                })} 等人共{item.toes.length + item.hit_toes.length}人</p>)
                
                : <p><span dangerouslySetInnerHTML={{__html:item && item.toes ? item.toes[0] && item.toes[0].name && item.toes[0].name : ''}}></span>&nbsp;&nbsp;
            <span dangerouslySetInnerHTML={{__html:item.toes ? item.toes[0] && item.toes[0].mail : ''}}></span>{item && item.toes.length > 1 ? `等人共${item && item.toes.length}人` : ''}</p>
                }
              </div>
              : <p className="shoujian1"><span dangerouslySetInnerHTML={{__html:item && item.toes[0] ? item.toes[0].name : ''}}></span>&nbsp;&nbsp;
            <span dangerouslySetInnerHTML={{__html:item && item.toes[0] ? item.toes[0].mail : ''}}></span>{item && item.toes.length > 1 ? `等人共${item && item.toes.length}人` : ''}</p>
            }
            
            <span className="stand"></span>
            <span className="attach">附件：{item && item.attachments.length}</span>
            <span className="mail-date">
              {item && moment(item.receive_time).format('YYYY-MM-DD H:mm')}
            </span>
          </div>
        </div>
        <Modal
          title={returnPs() }
          visible={this.state.visible}
          onCancel={() => this.setState({ visible: false ,num:0})}
          destroyOnClose={true}
          className="viewFileModal"
          wrapClassName="vertical-center-modal"
        >
          <FileViewer mailId={item && item.id} attachId={this.state.attachId} attachName={this.state.attachName}/>
        </Modal>
      </List.Item>
    );
  }
}

export default MailListItem;
