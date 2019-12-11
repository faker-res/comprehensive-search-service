import React, { Component } from 'react';
import EdbTable from '../../../../components/EdbTable'
import './index.scss'
import queryString from 'query-string'
class index extends Component {
    constructor(props){
        super(props);
        this.state={
            keyword:'',
            selected:'edb'
        }
    }
    componentDidUpdate(prevProps, prevState) {
        const oQueryParams = queryString.parse(prevProps.location.search);
        const nQueryParams = queryString.parse(this.props.location.search);
        if (nQueryParams.keyword !== oQueryParams.keyword) {
            this.setState({
                keyword:nQueryParams.keyword
            })
        }
    }
    //顶部菜单切换
    switchMenu=(type)=>{
        this.setState({
            selected:type
        })
    }
    render() {
        let {selected,keyword} = this.state
        return (
            <div className='dataModule-component'>
                {/* <a href={`/edbDetail?id=edb2437377`}>查看测试</a> */}
                <ul className='menus'>
                    <li className={selected==='edb'?'menu_on':''} onClick={()=>this.switchMenu('edb')}>经济数据</li>
                    <li className={selected==='cht'?'menu_on':''} onClick={()=>this.switchMenu('cht')}>创投数据</li>
                </ul>
                {
                   selected==='edb' &&  <EdbTable keyword={keyword}/>
                }
                {
                   selected==='cht' && 'cht'
                }
            </div>
        );
    }
}

export default index;