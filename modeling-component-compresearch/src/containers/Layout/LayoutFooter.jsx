import React from "react"
import ask from '../../lib/ask';

const gotoWiki = () => {
    // let p = ask('wikiLogin',{
    //   data:{
    //     os_username: 'hhxu.abcft',
    //     os_password: 'Tianlai2017!',
    //     login: '登录',
    //     os_destination: ''
    //   }
    // });
    let p = ask('wikiAccess', {
        params: {},
        xhrFields: {
            withCredentails: true
        }
    });
    p.then((res) => {
      alert('ok');
    });
  }

const LayoutFooter = () => {
    return (
        <div className="layout-footer">
            <div className="inner">
                <div className="nav">
                <a onClick={()=>{gotoWiki()}}>open wiki</a>|
                    <span>|</span>
                    <a href="">关于我们</a>
                    <span>|</span>
                    <a href="">联系我们</a>
                    <span>|</span>
                    <a href="">使用条款</a>
                </div>
                <div className="copyright">
                    Copyright 版权所有 &copy;<a href="http://www.abc.com/">北京阿博茨科技有限公司</a>
                </div>
            </div>
        </div>
    )
}

export default LayoutFooter