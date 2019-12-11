/**
 * @description 新闻消息卡片，适用新闻、公告、研报类型消息列表
 * @author kygeng
 * date: 2018-06-20
 */
import React, { Component } from 'react';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import { Row, Icon } from 'antd';
import moment from 'moment';
import findIndex from 'lodash/findIndex';
import { siteDomain, newsOrigin } from '../General/constants';
import NoDataTip from '../NoDataTip';
import Loading from '../General/Loading';
import './style.scss';

const preFixCls = 'abcft-news-list-card';

export default class NewsListCard extends Component {
    
    static defaultProps = {
        title: '',
        extra: null,
        type: '',// news、notice、report
        data: [],
        onRightClick: () => { return undefined }
    }

    static propTypes = {
        title: PropTypes.string.isRequired,
        extra: PropTypes.oneOfType([
            PropTypes.string,
            PropTypes.element
        ]),
        type: PropTypes.string,
        data: PropTypes.array.isRequired,
        onRightClick: PropTypes.func
    }
    
    constructor (props) {
        super(props)
    }

    handleClick=(_data)=>{
        const {type} = this.props;
        if (type === 'news') {
            window.open(`/report-detail/article/${_data.id}?from=news`)
        }
        if (type === 'notice') {
            window.open(`/report-detail/article/${_data.srcId}?from=notice`)
        }
        if (type === 'report') {
            window.open(`/report-detail/article/${_data.id}?from=report`)
            // return `https://report.${siteDomain}/report/article/${_data.id}`;
            // return null
        }
        return null;

    }
    render() {
        const { className, title, extra, type, data, status } = this.props;
        const { onRightClick } = this.props;

        const Desc = (_data) => {
            const { analyst_honor } = _data;
            const { author } = _data;

            if (type === 'news') {
                return (
                    <React.Fragment>
                        <span className={`${preFixCls}-item-source`} hidden={!_data.source_name}>{_data.source_name && _data.source_name.replace(/<[^>]*>/g, '')}</span>
                        <span className={`${preFixCls}-item-time`}>{moment(_data.time * 1000).format('YYYY/MM/DD')}</span>
                    </React.Fragment>
                )
            }
            if (type === 'notice') {
                return (
                    <React.Fragment>
                        <span className={`${preFixCls}-item-category`} hidden={!_data.category}>
                        {/* <a target="_blank" href={`/comprehensive-search/notice?keyword=${_data.category && _data.category.replace(/<[^>]*>/g, '')}`}> */}
                        {_data.category && _data.category.replace(/<[^>]*>/g, '')}
                        {/* </a> */}
                        </span>
                        <span className={`${preFixCls}-item-time`}>{moment(_data.publishAt * 1000).format('YYYY/MM/DD')}</span>
                    </React.Fragment>
                )
            }
            if (type === 'report') {
                const IconHonor = (<img src={require('../General/assets/image/new-report/icon_honor.png')} className={`${preFixCls}-icon-honor`}/>)
                return (
                    <React.Fragment>
                        <span className={`${preFixCls}-item-source`} hidden={!_data.source}>
                        {/* <a target="_blank" href={`/comprehensive-search?keyword=${_data.source && _data.source.replace(/<[^>]*>/g, '')}`}> */}
                        {_data.source && _data.source.replace(/<[^>]*>/g, '')}
                        {/* </a> */}
                        </span>
                        <span className={`${preFixCls}-item-category`} hidden={!_data.category}>{_data.category && _data.category.replace(/<[^>]*>/g, '')}</span>
                        {
                            author && author.map((_author, index) => {
                                let _index = findIndex(analyst_honor, { name: _author});
                                return (
                                    <span className={`${preFixCls}-item-author`} key={index}>
                                        { _index > -1 && IconHonor }
                                        { _author && _author.replace(/<[^>]*>/g, '')}
                                    </span>
                                )
                            })
                        }
                        <span className={`${preFixCls}-item-time`}>{moment(_data.publish_at * 1000).format('YYYY/MM/DD')}</span>
                    </React.Fragment>
                )
            }
            return null;
        }

        const parseUrl = (_data) => {
            console.log(newsOrigin)
            console.log(_data)
            if (type === 'news') {
                return `${newsOrigin}/report-detail/article/${_data.id}?from=news`;
            }
            if (type === 'notice') {
                return `${newsOrigin}/report-detail/article/${_data.srcId}?from=notice`;
            }
            if (type === 'report') {
                return `${newsOrigin}/report-detail/article?id=${_data.id}&from=report`;
                // return `https://report.${siteDomain}/report/article/${_data.id}`;
                // return null
            }
            return null;
        }
        const Items = data.map((_item, index) => {
            let _title = _item.title.replace(/<[^>]*>/g, '');
            return (
                <div className={`${preFixCls}-item`} key={index}>
                    <div className={`${preFixCls}-item-title`}>
                        <a target="_blank" title={_title} onClick={()=>{this.handleClick(_item)}}>
                        {_title}
                        </a>
                    </div>
                    <div className={`${preFixCls}-item-desc`}>
                        {Desc(_item)}
                    </div>
                </div>
            )
        });
        return (
            <div className={classNames(`${preFixCls}`, className)}>
                <Row className={`${preFixCls}-header`} type="flex" justify="space-between" align="middle">
                    <div className={`${preFixCls}-header-title`}>
                        {title}
                    </div>
                    {/* <div className={`${preFixCls}-header-extra`}>
                        <div className={`${preFixCls}-header-extra`} onClick={onRightClick}>
                            { extra ? extra : ( <span className={`${preFixCls}-header-more`}>更多<Icon type="right"/></span>) }
                        </div>
                    </div> */}
                </Row>
                <div className={`${preFixCls}-content`}>
                    {
                      status === 'pending'
                      ? <Loading style={{height: '326px'}}/> :
                      (status === 'error' || (status === 'done' && data && data.length === 0))
                      ? <NoDataTip style={{height: '326px'}}/>
                      : Items
                    }
                </div>
            </div>
        );
    }
    
}