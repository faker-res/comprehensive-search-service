import React, { Component } from 'react';
import Account from './Account';
import AbcMenu from './Menu';

class Index extends Component {
  constructor(props) {
    super(props)
    this.state = {}
  }

  render() {
    return (
      <div className="header">
        <div className="logo"/>
        <div className="menu-wrapper">
          <div className="menu-container">
            <ul className="menu">
              <li className="menu-item menu-item-active">
                <div className="menu-item-title"><a>系统管理</a></div>
              </li>
            </ul>
          </div>
          {/* <div className="sub-menu-container">
            <ul className="sub-menu">
              {
                Object.keys(subMenu).map((k) => {
                  const item = subMenu[k];
                  return (
                    <li key={k} className={`sub-menu-item${item.path === activeSubNav ? ' sub-menu-item-active' : ''}`}>
                      <Link
                        to={item.href || `${match.url}/${item.path}`}
                        target={item.href && '_blank'}
                      >
                        {item.title}
                      </Link>
                    </li>
                  );
                })
              }
            </ul>
          </div>} */}
        </div>
        <div style={{ width: "200px",marginTop: 15 }}>
          <Account/>
        </div>
      </div>
    );
  }
}

export default Index;