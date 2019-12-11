import React, { Component } from 'react';
import './BasicFooter.less';
import '../../common/common.less';
import axios from 'axios';
// import ask from '../../../../../lib/ask';

export default class BasicFooter extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }
  gotoWiki = () => {
    // let p = ask('wiki',{
    //   data:{
    //     os_username: 'hhxu.abcft',
    //     os_password: 'Tianlai2017!',
    //     login: '登录',
    //     os_destination: ''
    //   }
    // });
    // p.then((res) => {
    //   console.log('res: ', res)
    //   alert('ok');
    // });

    //http://10.11.255.44:8090/rest/api/space
    
    //'http://10.11.255.44:8090/dologin.action'
    axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'; 
    axios({
      method:"POST",
      url:"http://10.11.255.44:8090/dologin.action",
      data:{
        os_username: 'hhxu.abcft',
        os_password: 'Tianlai2017!',
        login: '登录',
        os_destination: ''
      },
      withCredentials:true
  }).then(function(res){
      console.log('success:', res);
      dispatch(getUserInfoSuccess(res.data));
  }).catch(function(error){
      dispatch(getUserInfoFail());
      console.log('error:', error);
  });
  }
  render() {
    return (
      <div className={`footer_container space_between middle`}>
        <div className={'footer_nva'}>
          <a onClick={()=>{this.gotoWiki()}}>open wiki</a>|
          <a>关于我们</a>|
          <a>联系我们</a>|
          <a>使用条款</a>
        </div>
        <span className={'footer_company'}>Copyright © 2016-2017&nbsp;&nbsp;&nbsp;北京阿博茨科技有限公司</span>
      </div>
    );
  }
}
