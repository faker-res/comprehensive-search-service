import React, { Component } from 'react';
import { Menu } from 'antd';
import {BrowserRouter,Route,Switch,NavLink,withRouter} from 'react-router-dom';
import './ComNav.less';
// import '../../common/common.less';

export default class ComNav extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }
  render() {
    const { RouterList, selectedKey } = this.props;
    return (
      <div className={`container center_middle`}>
        <Menu
          theme="light"
          mode="horizontal"
          selectedKeys={[selectedKey]}
          style={{ lineHeight: '49px', width: '1260px' }}
          onClick={this.clickMenu}
        >
          {
            RouterList.map((item) => {
              return (
                <Menu.Item key={item.key || item.url} disabled={item.disabled || false}>
                  <NavLink to={item.url} key={item.url}>
                    {item.name}
                  </NavLink>
                </Menu.Item>
              );
            })
          }
        </Menu>
      </div>
    );
  }
}
