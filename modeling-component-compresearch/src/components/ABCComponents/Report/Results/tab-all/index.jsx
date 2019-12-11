import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {
    Row,
    Col,
    notification,
} from 'antd';
import { withRouter } from 'react-router-dom';
import { inject, observer } from 'mobx-react';
import _ from 'lodash';
import { getMails, getSearchMails } from 'config/mail';
import MailList from 'components/Results/List';
import { getQueryString } from '../../../lib/lib';
// import MailDetail from 'components/Results/Detail'
import MailSortAction from 'components/Action';
import moment from 'moment';
import empty from '../empty.png';
import '../index.scss';

@withRouter @inject('listStore', 'searchStore') @observer
class TabOfAll extends Component {
    constructor() {
        super();
        this.state = {
            list: [],
            totalCount: 0,
            moreResults: true,
            // 能否加载更多
            loadMore: true
        };
    }

    static propTypes = {
        isSearched: PropTypes.bool
    }
    reflashPage = () => {
        window.location.reload();
    }

    async componentWillReceiveProps(nextProps) {
        const { listStore } = this.props;
        let {
            searchStore = {}
        } = nextProps;
        let searchTime = (searchStore.search && searchStore.search.cur_time) || '';
        if (!searchTime) return;
        let newOp = '' + searchTime;
        let oriOp = '' + this.oriOp;
        // search
        if (newOp != oriOp) {
            this.oriOp = newOp;
            listStore.setList([]);
            listStore.setLoading(true);
            const response = await getSearchMails({ ...searchStore.search });
            listStore.setLoading(false);
            if (response.data) {
              listStore.setList(response.data.items);
              let num = response.data.total_count ? response.data.total_count : 0;
              listStore.totalCount = num;
              listStore.changeCount(num);
            }
        }
    }

    async componentDidMount() {
        if (getQueryString(this.props.location.search,'search')){
            // 需要搜索
            return;
        }
        const { listStore } = this.props;
        listStore.setList([]);
        listStore.setLoading(true);
        const mailList = await getMails({ end_time: Number(moment().format('X')) * 1000, page_num: listStore.current, page_size: 10, prior: 'score', search_words: {}, involves: listStore.involves });
        listStore.setLoading(false);
        if (!mailList || !mailList.success) {
            return notification.error({
                message: '错误',
                description: mailList ? mailList.message : ''
            });
        }
        let num = mailList.data.total_count ? mailList.data.total_count : 0;
        listStore.totalCount = num;
        listStore.changeCount(num);
        listStore.setList(mailList.data.items);
    }

    // 加载更多
    async onLoadMore(page) {
        const { listStore, searchStore } = this.props;
        if (listStore.loading){
            return;
        }
        searchStore.filter.page_num = listStore.current + 1;
        const o = _.assign({}, searchStore.filter, searchStore.search);
        o.page_num = listStore.current + 1;
        o.involves = listStore.involves;
        o.prior = listStore.prior ? listStore.prior : 'score';
        listStore.setLoading(true);
        let mailList = await getSearchMails({ ...o });
        listStore.setLoading(false);
        this.setState({
            loadMore: (mailList.data.items || []).length >= searchStore.search.page_size
        });
        if (!mailList) return;
        let num = mailList.data.total_count ? mailList.data.total_count : 0;
        listStore.totalCount = num;
        listStore.changeCount(num);
        listStore.setList(listStore.mailList.concat(mailList.data.items));
        listStore.current = listStore.current + 1;
    }
    

    render() {
        const { listStore, searchStore } = this.props;
        const emptyDefaultContent = (
            <div className="noResult">
                {/* <div className="mail-sort-action"></div> */}
                <div className="noResult" style={{ marginTop: '12rem' }}>
                    <img src={empty} className="icon noMail" />
                    <div className="re-input">邮件正在同步，请稍后。可点击<span onClick={this.reflashPage}>刷新</span>查看最新同步进度</div>
                </div>
            </div>
        );
        const emptySearchContent = (
            <div className="noResult">
                {/* <div className="mail-sort-action"></div> */}
                <div className="noResult" style={{ marginTop: '12rem' }}>
                    <img src={empty} className="icon noMail" />
                    <div className="re-input">暂无搜索结果，请重新输入</div>
                </div>
            </div>
        );
        const resultContent = (
            <Row gutter={8}>
                <Col span={24} style={{ paddingRight: '0px' }}>
                    {searchStore.isSearched && <p className="result-content">相关搜索<span className="result-number"> {listStore.totalCount} </span> 条</p>}
                    {/* <MailSortAction /> */}
                    <MailList dataset={listStore.mailList && listStore.mailList.slice()} current={listStore.current} onLoadMore={() => this.onLoadMore(listStore.current)} showLoadMore={true} total={listStore.totalCount} loading={listStore.loading} loadMore={this.state.loadMore}/>
                    <div className="mail-divider" />
                </Col>
            </Row>
        );

        return (
            <div className="mail-list-results">
                <MailSortAction />
                {/* 全部搜索结果 */}
                {
                    listStore.loading ? resultContent :
                    listStore.mailList && listStore.mailList.length ? resultContent : (
                        listStore.search_words ? emptySearchContent : emptyDefaultContent
                    )
                }
            </div>
        );
    }
}

export default TabOfAll;