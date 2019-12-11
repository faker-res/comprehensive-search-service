import React from 'react';
import { withRouter, Link } from 'react-router-dom';
import { RIGHT_RULES } from '../../lib/constants';
import { inject, observer } from 'mobx-react';
import { toJS } from 'mobx';

@inject('authStore')
@observer
class Index extends React.Component {
  state = {
    subNavShow: true,
  }


  render() {
    const { match, location, navMenu, subNavMenu } = this.props;
    const { subNavShow } = this.state ;
    const activeNav = match.params.item;
    const activeSubNav = location.pathname.split('/')[2];

    return (
      <div className="menu-wrapper">
        <div className="menu-container">
          <ul className="menu">
            {
              navMenu.map((k) => {
                return (
                  <li key={k.id} className={`menu-item${k.id === activeNav ? ' menu-item-active' : ''}`}>
                    <Link
                      to={k.href || `/${k.path}`}
                      target={k.href && '_blank'}
                      onClick={() => (k.href || this.setState({ subNavShow: true }))}>
                      <div className="menu-item-title">{k.name}</div>
                    </Link>
                  </li>
                );
              })
            }
          </ul>
        </div>
        {subNavMenu && subNavMenu.length > 0 && subNavShow && <div className="sub-menu-container">
          <ul className="sub-menu">
            {
              subNavMenu.map((k,index) => {
                return (
                  <li key={k.id} className={`sub-menu-item${(k.path === activeSubNav) || (index === 0 && !activeSubNav) ? ' sub-menu-item-active' : ''}`}>
                    <Link
                      to={k.href || `${match.url}/${k.path}`}
                      target={k.href && '_blank'}>
                      {k.name}
                    </Link>
                  </li>
                );
              })
            }
          </ul>
        </div>}
      </div>
    );
  }
}

export default withRouter(Index);
