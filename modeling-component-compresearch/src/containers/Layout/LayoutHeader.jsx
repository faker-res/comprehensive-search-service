import React, { Component } from 'react'
import Account from './Account'
import AbcMenu from './Menu'
import SuggestedSearchBox from '../../components/ABCComponents/SuggestedSearchBox';
import styled from 'styled-components';
import { RIGHT_RULES } from '../../lib/constants';
import { withRouter } from 'react-router-dom';
import { inject, observer } from 'mobx-react';
import { toJS } from 'mobx';

const AccountWrap = styled.div`
    position: absolute;
    top: 15px;
    right: 16px;
    width: 200px;
`;

const SearchWrap = styled.div`
    position: absolute;
    top: 18px;
    right: 240px;
    width: 280px;
`;

@withRouter
@inject('authStore')
@observer
class LayoutHeader extends Component {
    constructor(props) {
        super(props)
        this.state = {}
    }

    handleSearch = ({ keyword = "" }) => {
        if (!keyword.trim()) return;
        const { location } = this.props;
        if (location.pathname === '/report/secret' || location.pathname === '/report') {
            window.open(`/internal-report?keyword=${encodeURIComponent(keyword)}`);
        } else if (location.pathname === '/viewpoint/report-research' || location.pathname === '/viewpoint') {
            window.open(`/vender-report?keyword=${encodeURIComponent(keyword)}`);
        } else {
            window.open(`?keyword=${encodeURIComponent(keyword)}`);
        }
    }

    render() {
        const { match, authStore } = this.props;
        const userInfo = toJS(authStore.userInfo);
        const activeNav = match.params.item;
        const access = (userInfo.access && userInfo.access.length > 0) ? userInfo.access : RIGHT_RULES;
        const NavMenu = access.filter(item => {
            return item.type === 'nav' && item.view_access;
        });
        const CurNavMenu = NavMenu.filter(item => {
            return item.id === activeNav && item.view_access;
        });
        const SubNavMenu = CurNavMenu[0] && CurNavMenu[0].children && CurNavMenu[0].children.filter(item => item.view_access) || [];
        const showSubNavMenu = (SubNavMenu && SubNavMenu.length > 0);

        return (
            <div className="layout-header" style={{ height: showSubNavMenu ? '104px' : '70px' }}>
                <div className="header">
                    <a href="/home" className="logo">ABC智能投研</a>
                    <AbcMenu navMenu={NavMenu} subNavMenu={SubNavMenu} />
                    <SearchWrap>
                        <SuggestedSearchBox
                            className={`report-results__search-box`}
                            placeholder={"输入关键词"}
                            defaultQuery=""
                            searchHistoryTitle={"最新搜索"}
                            onSearch={this.handleSearch} />
                    </SearchWrap>

                    <AccountWrap>
                        <Account />
                    </AccountWrap>
                </div>
                <div className="sider" style={{ display: showSubNavMenu ? 'block' : 'none' }}></div>
            </div>
        );
    }
}

export default LayoutHeader