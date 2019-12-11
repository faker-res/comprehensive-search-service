/**
 * @description 暂无数据提示组件
 * @author kygeng
 * date: 2018-07023
 */
import React from 'react';
import { Row } from 'antd';

export default (props) => {
    return (
        <Row type="flex" justify="center" align="middle" style={{
            width: '100%',
            height: '100%'
        }} {...props}>
            <Row style={{
                width:'60px',
                flexDirection: 'column'
            }} type="flex" align="middle" justify="center">
                <i className="iconfont-web" dangerouslySetInnerHTML={{__html:'&#xe698;'}} style={{
                    fontSize: '20px',
                    color: '#e6e6e6',
                    lineHeight: 1,
                    marginBottom: '16px'
                }}></i>
                <p style={{
                    fontSize: '14px',
                    color: '#a6a6a6',
                    lineHeight: 1
                }}>暂无数据</p>
            </Row>
        </Row>
    )
}