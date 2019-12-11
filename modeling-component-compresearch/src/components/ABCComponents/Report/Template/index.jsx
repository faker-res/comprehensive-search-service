/**
 * 条件渲染控制组件
 * @author lzhang
 * @description 根据isShow来判断是否显示内部组件内容
 */
import React from 'react';

export default (props) => {
    return (
        <React.Fragment>
            {props.isShow ? props.children : null}
        </React.Fragment>
    );
};