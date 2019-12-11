/**
 * @description 主题-主题简介
 * @author momoxsy
 * date: 2018-06-26
 */

import React, { Component } from 'react';
import { Row, Col ,Spin} from 'antd';
import PropTypes from 'prop-types';
import { Scrollbars } from 'react-custom-scrollbars';
import isEmpty from 'lodash/isEmpty';
import NoDataTip from '../NoDataTip';

import './style.scss';

import { data } from './data.js';

class ThemeBriefIntroduction extends Component{

    constructor(props) {
        super(props);

    }

    render() {

        const intro = this.props.intro;

        return (
            <div className="brief-intro">
                <Row className="data-title border-bottom">
                    <Col span={24}>
                        <span className="title">主题简介</span>
                    </Col>
                </Row>
                <div className="intro-desc">
                    <Scrollbars style={{ width: "100%", height: 490 }}>
                        <div className="content">
                            {
                                isEmpty(intro) ? <div className="intro-desc-nodata"><NoDataTip/></div> : <span>{intro}</span>
                            }
                        </div>
                    </Scrollbars>
                </div>
            </div>
        );

    }

}

export default ThemeBriefIntroduction;

// 默认props值
ThemeBriefIntroduction.defaultProps = {
    intro: data.intro
}
// props 类型
ThemeBriefIntroduction.propTypes = {
    intro: PropTypes.string
}