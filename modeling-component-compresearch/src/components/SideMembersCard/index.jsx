/**
 * @description 分析师成员卡片组件
 * @author kygeng
 * date: 2018-05-16
 */
// lib
import React, { Component } from 'react'
import PropTypes from 'prop-types'
import { Layout, Row, Col, Icon } from 'antd'
import imgDefualt from '../../theme/default/images/icon-avatar-default.png'
// style
import './index.scss'

class SideMembersCard extends Component {
    constructor(props) {
        super(props)

        // this._onRighClick = this._onRighClick.bind(this);
        this._onItemClick = this._onItemClick.bind(this);
    }

    // _onRighClick() {
    //     if (this.props.onRightClick) {
    //         this.props.onRightClick()
    //     }
    // }

    _onItemClick(event, member) {
        if (this.props.onItemClick) {
            this.props.onItemClick(member);
        }
    }

    render() {
        let { title, titleDesc, headerExtrad, members, UrlLink } = this.props;

        const extrad = headerExtrad ? headerExtrad : (<Icon type="right" />);
        const Members = members.map((_member, index) => {
            let { avatar, name, orgSName, desc } = _member;
            return (
                <Col className="abc-side-members-card-item" key={index} onClick={(e) => this._onItemClick(e, _member)}>
                    <div className="imgwarp">
                      <img src={avatar ? `${avatar}?x-oss-process=image/resize,m_fixed,h_110,w_85` : imgDefualt} alt="avatar" className="avatar"/>
                    </div>
                    <span className="name">{name}</span>
                    <span className="company">{orgSName}</span>
                    <span className="desc">{desc}</span>
                </Col>
            )
        })

        return (
            <Layout className="abc-side-members-card-wrap">
                <Row className="abc-side-members-card-header" type="flex" justify="space-between" align="middle">
                    <Col className="title-wrap">
                        <span className="title">{title}</span>
                        {titleDesc && <span className="desc">{titleDesc}</span>}
                    </Col>
                    <Col className="header-extrad" ><a className="header-extrad-a" href={`${UrlLink}?indu_name=${titleDesc}`} target='_blank'>{extrad}更多<Icon type="right"/></a></Col>
                </Row>
                <Row className="abc-side-members-card-content" type="flex" justify="space-between" align="middle">
                    {Members}
                </Row>
            </Layout>
        )
    }
}

SideMembersCard.defaultProps = {
    title: '',
    titleDesc: '',
    headerExtrad: null,
    members: []
}

SideMembersCard.propTypes = {
    title: PropTypes.string,
    titleDesc: PropTypes.string,
    // headerExtrad: PropTypes.element,
    members: PropTypes.array,
    // onRightClick: PropTypes.func,
    onItemClick: PropTypes.func
}

export default SideMembersCard;