/**
 * @description 人物列表组件
 * @author kygeng
 * date: 2018-06-20
 */
import React, { Component } from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';
import { Row, Icon, Avatar, Spin } from 'antd';
import Marquee from 'react-marquee';
import isEmpty from 'lodash/isEmpty';
import './style.scss';

const preFixCls = 'abcft-person-list-view';

export default class ABCPersonListView extends Component {

    static defaultProps = {
        title: '',
        extra: null,
        data: [],
        contentHeight: null,
        style: {},
        showSubscribe: false,
        load: 'pending',// done, error
        mode: 'list', // list、 grid
        noDataTip: '暂无数据',
        suscribeTip: '订阅',
        onItemClick: () => { return undefined; },
        onSuscribe: () => { return undefined; },
        onRightClick: () => { return undefined; }
    }

    static propTypes = {
        title: PropTypes.string.isRequired,
        extra: PropTypes.oneOfType([
            PropTypes.string,
            PropTypes.element
        ]),
        data: PropTypes.array.isRequired,
        contentHeight: PropTypes.number,
        style: PropTypes.object,
        showSubscribe: PropTypes.bool,
        load: PropTypes.string,
        mode: PropTypes.string,
        noDataTip: PropTypes.string,
        suscribeTip: PropTypes.string,
        onRightClick: PropTypes.func,
        onItemClick: PropTypes.func,
        onRightClick: PropTypes.func
    }

    constructor (props) {
        super(props)
    }

    handleTipClick = (item) => {
        if (isEmpty(item.news) || isEmpty(item.news.url)) return;
        window.open(item.news.url, '_blank');
    }

    render() {
        const { className } = this.props;
        const { title, extra, data, contentHeight, showSubscribe, load, mode, style } = this.props;
        const { onRightClick, onItemClick, onSuscribe } = this.props;

        const NoDataTip = (props) => {
            return (
                <Row type="flex" justify="center" align="middle" style={{width: '100%',height: '100%'}} {...props}>
                    <Row style={{flexDirection: 'column'}} type="flex" align="middle" justify="center">
                        <Icon type="exception" style={{
                            fontSize: '20px',
                            color: '#e6e6e6',
                            lineHeight: 1,
                            marginBottom: '16px'
                        }}/>
                        <p style={{
                            fontSize: '14px',
                            color: '#a6a6a6',
                            lineHeight: 1
                        }}>{this.props.noDataTip}</p>
                    </Row>
                </Row>
            )
        }

        const Loading = (props) => {
            return (
                <Row type="flex" justify="center" align="middle" style={{width: '100%',height: '100%'}} {...props}>
                    <Spin/>
                </Row>
            )
        }

        const Tags = (tags) => {
            return tags.map((_tag, index) => {
                return (
                    <span className={`${preFixCls}-item-tags-item`} key={index}>{_tag}<i>|</i></span>
                );
            });
        }

        const Items = data && data.map((_item,index) => {
            return (
                <Row className={`${preFixCls}-item`} type="flex" key={`${_item.id}-${index}`} justify="space-between" align="middle">
                    <div className={`${preFixCls}-item-avatar`} onClick={() => onItemClick(_item)}>
                        {
                            _item.avatar ? <img src={_item.avatar} alt={_item.name} /> : <Avatar shape="square" size="large" icon="user"/>
                        }
                    </div>
                    <div className={`${preFixCls}-item-info`}>
                        <div className={`${preFixCls}-item-name`} onClick={() => onItemClick(_item)}>{_item.name}</div>
                        <div className={`${preFixCls}-item-tags`}>
                            {Tags(_item.tags)}
                        </div>
                        <div className={`${preFixCls}-item-news`} hidden={isEmpty(_item.news)} onClick={() => this.handleTipClick(_item)}>
                            { !isEmpty(_item.news) && !isEmpty(_item.news.title) && <Marquee text={_item.news.title.replace(/<[^>]*>/g, '')} /> }
                        </div>
                        <div className={`${preFixCls}-item-subscribe`} hidden={!showSubscribe} onClick={() => onSuscribe(_item)}>
                            <Icon type="plus"/><span>{this.props.suscribeTip}</span>
                        </div>
                    </div>
                </Row>
            )
        });

        return (
            <div className={classNames(`${preFixCls}`, mode, className)}>
                <Row className={`${preFixCls}-header`} type="flex" justify="space-between" align="middle">
                    <div className={`${preFixCls}-header-title`}>
                        {title}
                    </div>
                    <div className={`${preFixCls}-header-extra`}>
                        <div className={`${preFixCls}-header-extra`} onClick={onRightClick}>
                            { extra }
                        </div>
                    </div>
                </Row>
                <div className={`${preFixCls}-content`} style={Object.assign({}, style, {height:contentHeight ? `${contentHeight}px` : 'auto'})}>
                    {
                        load === 'pending'
                        ? <div className='loadingDate'><Loading/></div>:
                        (load === 'error' || (load === 'done' && data && data.length === 0))
                        ? <NoDataTip/>
                        : Items
                    }
                </div>
            </div>
        )
    }
}