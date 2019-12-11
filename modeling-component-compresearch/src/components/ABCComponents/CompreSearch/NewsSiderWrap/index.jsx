/**
 * @description 相关公告侧边组件
 * @author kygeng
 * date: 2018-08-23
 */
import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import PropTypes from 'prop-types';
import ask from '../../../../lib/ask';
import isEmpty from 'lodash/isEmpty';
import queryString from 'query-string';
import NewsResearchKeyWord from '../NewsResearchKeyWord';
import Cookies from 'js-cookie';

@withRouter
export default class NewsSiderWrap extends Component {

    static  defaultProps = {
        code: "",
        name: "",
        cName: "",
        type: ""
    }

    static propTypes = {
        code: PropTypes.string,
        name: PropTypes.string,
        cName: PropTypes.string,
        type: PropTypes.string,
    }

    constructor(props) {
        super(props)

        this.state = {
            venderStatus: 'pending',
            internalStatus: 'pending',
            vender_analyst: [],
            internal_analyst: []
        }
    }

    // componentDidMount() {
    //     const { keyword = "" } = queryString.parse(this.props.location.search);
    //     this.loadCompreSearchCard()
    //         .then(resp => {
    //             const { code, message, data } = resp;
    //             if (code !== 200 || isEmpty(data)) {
    //                 throw new Error(`Response Exception:${message},code:${code}`);
    //             }
    //             let cards = data.filter(item => {
    //                 return item.type === 'Industry' || item.type === 'Analyst' || item.type === 'InternalAlyst';
    //             });
    //             let curCard = cards[parseInt(Cookies.get('curIdx') || 0)] || {};
    //             if (isEmpty(curCard)) {
    //                 this.loadAllAnayst(keyword);
    //             } else {
    //                 this.loadInternalAnalyst(curCard);
    //                 this.loadVenderAnalyst(curCard);
    //             }
    //         })
    //         .catch(err => {
    //             console.error(err);
    //             this.loadAllAnayst(keyword);
    //         })
    // }

    // loadCompreSearchCard = () => {
    //     const { keyword = "" } = queryString.parse(this.props.location.search);
    //     this.setState({ internalStatus: 'pending', venderStatus: 'pending' });
    //     return ask('CompreSearchCard', { params: { keyword }})
            
    // }

    // loadAllAnayst = (keyword) => {
    //     this.setState({ internalStatus: 'pending', venderStatus: 'pending' });
    //     ask('AllAnalystSimilar', { params: {keyword: keyword }})
    //         .then(resp => {
    //             const { code, message, data } = resp;
    //             if (code !== 200 || isEmpty(data)) {
    //                 throw new Error(`Response Exception:${message}, code:${code}`);
    //             }
    //             const { internal, vender } = data;
    //             this.setState({
    //                 internal_analyst: (internal || []).map(item => {
    //                     return {
    //                         id: item.id,
    //                         name: item.name,
    //                         avatar: item.avatar,
    //                         tags: [item.department]
    //                     }
    //                 }),
    //                 vender_analyst: (vender || []).map(item => {
    //                     return {
    //                         id: item.id,
    //                         name: item.name,
    //                         avatar: item.avatar,
    //                         tags: [item.company]
    //                     }
    //                 }),
    //                 internalStatus: 'done',
    //                 venderStatus: 'done'
    //             });
    //         })
    //         .catch(err => {
    //             console.error(err);
    //             this.setState({ internalStatus: 'error', venderStatus: 'error' })
    //         })
    // }

    // loadInternalAnalyst = ({ code, name, cName, type }) => {
    //     this.setState({ internalStatus: 'pending' });
    //     ask('InternalAnalystSimilar', { params: { code, name, cName, type }})
    //         .then(resp => {
    //             const { code, message, data } = resp;
    //             if (code !== 200 || isEmpty(data)) {
    //                 throw new Error(`Response Exception:${message}, code:${code}`);
    //             }
    //             this.setState({
    //                 internalStatus: 'done',
    //                 internal_analyst: (data || []).map(item => {
    //                     return {
    //                         id: item.id,
    //                         name: item.name,
    //                         avatar: item.avatar,
    //                         tags: [item.department]
    //                     }
    //                 })
    //             })
    //         })
    //         .catch(err => {
    //             this.setState({ internalStatus: 'error' });
    //             console.error(err);
    //         })
    // }

    // loadVenderAnalyst = ({ code, name, cName, type }) => {
    //     this.setState({ venderStatus: 'pending' });
    //     ask('VenderAnalystSimilar', { params: { code, name, cName, type }})
    //         .then(resp => {
    //             const { code, message, data } = resp;
    //             if (code !== 200 || isEmpty(data)) {
    //                 throw new Error(`Response Exception:${message}, code:${code}`);
    //             }
    //             this.setState({
    //                 venderStatus: 'done',
    //                 vender_analyst: (data || []).map(item => {
    //                     return {
    //                         id: item.id,
    //                         name: item.name,
    //                         avatar: item.avatar,
    //                         tags: [item.company]
    //                     }
    //                 })
    //             })
    //         })
    //         .catch(err => {
    //             this.setState({ venderStatus: 'error' });
    //             console.error(err);
    //         })
    // }

    // handleItemClick = (item) => {
    //     let queryParams = queryString.parse(this.props.location.search);
    //     const { keyword = "" } = queryParams;
    //     if (item.name === keyword) return;
    //     queryParams.keyword = item.name;
    //     this.props.history.push({
    //         pathname: this.props.location.pathname,
    //         search: `?${queryString.stringify(queryParams)}`
    //     });
    // }

    render() {
        const { keyword = "" } = queryString.parse(this.props.location.search);
        const { venderStatus, vender_analyst } = this.state;
        const { internalStatus, internal_analyst } = this.state;
        return (
            <React.Fragment>
                <NewsResearchKeyWord
                    title={'24小时最热资讯'}
                    load={internalStatus}
                    mode="grid"
                    style={{minHeight:'332px'}}
                    noDataTip="没有关键词"
                    data={internal_analyst}
                    onItemClick={this.handleItemClick}/>
                
                <div style={{width:'100%',height:'20px'}}></div>

            </React.Fragment>
        )
    }
}