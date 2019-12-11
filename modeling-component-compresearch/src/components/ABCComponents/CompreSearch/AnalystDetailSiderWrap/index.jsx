/**
 * @description 分析师侧边组件群
 * @author kygeng
 * date: 2018-08-23
 */
import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import PropTypes from 'prop-types';
import ask from '../../../../lib/ask';
import isEmpty from 'lodash/isEmpty';
import queryString from 'query-string';
import ABCPersonListView from '../ABCPersonListView';
import Cookies from 'js-cookie';

@withRouter
export default class AnalystDetailSiderWrap extends Component {

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

    componentWillMount(){
        let keyword = this.props.name
        this.setState({keyword})
    }

    componentDidMount() {
        // const { keyword = "" } = queryString.parse(this.props.location.search);
        const { keyword } = this.state
        this.loadCompreSearchCard()
            .then(resp => {
                const { code, message, data } = resp;
                if (code !== 200 || isEmpty(data)) {
                    throw new Error(`Response Exception:${message},code:${code}`);
                }
                let cards = data.filter(item => {
                    return item.type === 'Industry' || item.type === 'Analyst' || item.type === 'InternalAlyst';
                });
                let curCard = cards[parseInt(Cookies.get('curIdx') || 0)] || {};
                if (isEmpty(curCard)) {
                    this.loadAllAnayst(keyword);
                } else {
                    this.loadInternalAnalyst(curCard);
                    this.loadVenderAnalyst(curCard);
                }
            })
            .catch(err => {
                console.error(err);
                this.loadAllAnayst(keyword);
            })
    }

    loadCompreSearchCard = () => {
        // const { keyword = "" } = queryString.parse(this.props.location.search);
        const { keyword } = this.state
        this.setState({ internalStatus: 'pending', venderStatus: 'pending' });
        return ask('CompreSearchCard', { params: { keyword }})
            
    }

    loadAllAnayst = (keyword) => {
        this.setState({ internalStatus: 'pending', venderStatus: 'pending' });
        ask('AllAnalystSimilar', { params: {keyword: keyword }})
            .then(resp => {
                const { code, message, data } = resp;
                if (code !== 200 || isEmpty(data)) {
                    throw new Error(`Response Exception:${message}, code:${code}`);
                }
                const { internal, vender } = data;
                this.setState({
                    internal_analyst: (internal || []).map(item => {
                        return {
                            id: item.id,
                            name: item.name,
                            avatar: item.avatar,
                            tags: [item.department]
                        }
                    }),
                    vender_analyst: (vender || []).map(item => {
                        return {
                            id: item.id,
                            name: item.name,
                            avatar: item.avatar,
                            tags: [item.company]
                        }
                    }),
                    internalStatus: 'done',
                    venderStatus: 'done'
                });
            })
            .catch(err => {
                console.error(err);
                this.setState({ internalStatus: 'error', venderStatus: 'error' })
            })
    }

    loadInternalAnalyst = ({ code, name, cName, type }) => {
        this.setState({ internalStatus: 'pending' });
        ask('InternalAnalystSimilar', { params: { code, name, cName, type }})
            .then(resp => {
                const { code, message, data } = resp;
                if (code !== 200 || isEmpty(data)) {
                    throw new Error(`Response Exception:${message}, code:${code}`);
                }
                this.setState({
                    internalStatus: 'done',
                    internal_analyst: (data || []).map(item => {
                        return {
                            id: item.id,
                            name: item.name,
                            avatar: item.avatar,
                            tags: [item.department],
                            isExternal:false
                        }
                    })
                })
            })
            .catch(err => {
                this.setState({ internalStatus: 'error' });
                console.error(err);
            })
    }

    loadVenderAnalyst = ({ code, name, cName, type }) => {
        this.setState({ venderStatus: 'pending' });
        ask('VenderAnalystSimilar', { params: { code, name, cName, type }})
            .then(resp => {
                const { code, message, data } = resp;
                if (code !== 200 || isEmpty(data)) {
                    throw new Error(`Response Exception:${message}, code:${code}`);
                }
                this.setState({
                    venderStatus: 'done',
                    vender_analyst: (data || []).map(item => {
                        return {
                            id: item.id,
                            name: item.name,
                            avatar: item.avatar,
                            tags: [item.company],
                            isExternal:true
                        }
                    })
                })
            })
            .catch(err => {
                this.setState({ venderStatus: 'error' });
                console.error(err);
            })
    }

    handleItemClick = (item) => {
        // console.log(item)
        let queryParams = queryString.parse(this.props.location.search);
        const { id = "" } = queryParams;
        // const { keyword } = this.state
        if (item.id === id) return;
        queryParams.type = item.isExternal ? 'Analyst' : 'InternalAlyst'
        queryParams.id = item.id;
        window.open(`${this.props.location.pathname}?${queryString.stringify(queryParams)}`)
        // this.props.history.push({
        //     pathname: this.props.location.pathname,
        //     search: `?${queryString.stringify(queryParams)}`
        // });
    }

    render() {
        // const { keyword = "" } = queryString.parse(this.props.location.search);
        const { venderStatus, vender_analyst } = this.state;
        const { internalStatus, internal_analyst } = this.state;
        const {isExternal} = this.props
        // console.log(isExternal)
        return (
            <React.Fragment>
                {isExternal ? <div>
                <ABCPersonListView
                    // title={`${keyword}同领域内部研究员`}
                    title={`相关内部研究员推荐`}
                    load={internalStatus}
                    mode="grid"
                    style={{height:'861px'}}
                    contentHeight='809'
                    noDataTip="没有找到相关研究员"
                    data={internal_analyst}
                    onItemClick={this.handleItemClick}/>
                
                <div style={{width:'100%',height:'9px'}}></div>

                <ABCPersonListView
                    // title={`${keyword}同领域外部研究员`}
                    title={`相关外部研究员推荐`}
                    load={venderStatus}
                    mode="grid"
                    style={{height:'852px'}}
                    contentHeight='800'
                    noDataTip="没有找到相关研究员"
                    data={vender_analyst}
                    onItemClick={this.handleItemClick}/>
                </div> : <div>
                <ABCPersonListView
                    // title={`${keyword}同领域内部研究员`}
                    title={`相关内部研究员推荐`}
                    load={internalStatus}
                    mode="grid"
                    style={{height:'861px'}}
                    noDataTip="没有找到相关研究员"
                    data={internal_analyst}
                    onItemClick={this.handleItemClick}/>
                
                <div style={{width:'100%',height:'9px'}}></div>

                <ABCPersonListView
                    // title={`${keyword}同领域外部研究员`}
                    title={`相关外部研究员推荐`}
                    load={venderStatus}
                    mode="grid"
                    style={{height:'852px'}}
                    noDataTip="没有找到相关研究员"
                    data={vender_analyst}
                    onItemClick={this.handleItemClick}/>
                </div>
                }
            </React.Fragment>
        )
    }
}