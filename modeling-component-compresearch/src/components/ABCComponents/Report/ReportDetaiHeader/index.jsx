import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import PropTypes from 'prop-types';
import {
    Layout,
    Menu
} from 'antd';
import styled from 'styled-components';
import './index.scss';

const LogoWrap = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    width: 170px;
    cursor: pointer;
`;
const { Header } = Layout;

@withRouter
class ReportDetailHeader extends Component {
	constructor(props) {
        super(props);
        
        let {match = {}} = props;
		this.state = {
            // 当前激活menu
            activeMenu: match.params.type,
            // 当前文件ID
            fileId: match.params.fileId,
        };

        this.handleRef = this.handleRef.bind(this);
        this.handleClickLogo = this.handleClickLogo.bind(this);
    }
    
	componentWillMount() {

    }
    
    handleRef(e) {
        let {
            handleRef
        } = this.props;

        this.refSearchBar = e;

        handleRef && handleRef(e);
    }

    handleClickLogo() {
        this.props.history.push({
            pathname: '/'
        });
    }

	render() {
        let {
            className,
            fileInfo,
        } = this.props;
        let {
            activeMenu,
            fileId
        } = this.state;
        
		return (
			<div ref={this.handleRef} className={`abc-report-detail-header ${className}`}>
                <Layout>
                    <Header>
                        <LogoWrap className="m-logo nav-overview" onClick={this.handleClickLogo}>
                            <img src={require('../../../../assets/image/logo-white-modeling.png')} alt="" width="170" />
                        </LogoWrap>
                        <span className={'header-filetitle'}>
                            {fileInfo.title || ''}
                        </span>
                        <div className={'header-right'}>
                            <Menu mode="horizontal"
                                defaultSelectedKeys={[activeMenu]}
                                style={{ lineHeight: '64px' }}
                                onSelect={(item) => {
                                    this.setState({
                                        activeMenu: item.key
                                    }, () => {
                                        this.props.history.push({
                                            pathname: `/report-detail/${item.key}/${fileId}`,
                                        });
                                    });
                                }}>

                                <Menu.Item key={'article'}>看全文</Menu.Item>
                                {/* <Menu.Item key={'chart'}>看图表</Menu.Item> */}
                                {/* <Menu.Item key={'viewpoint'}>看观点</Menu.Item> */}
                                <Menu.Item key={'info'}>基本信息</Menu.Item>
                            </Menu>
                        </div>
                    </Header>
                </Layout>
			</div>
		);
	}
}

ReportDetailHeader.defaultProps = {
    language: 'zh-CN',
    className: '',
    handleRef: () => {},
    fileInfo: {}
};

ReportDetailHeader.propTypes = {
    // 语言环境
    language: PropTypes.string,
    // 附加类名
    className: PropTypes.string,
    // ref回调
    handleRef: PropTypes.func,
    // 文件信息
    fileInfo: PropTypes.object
};

export default ReportDetailHeader;