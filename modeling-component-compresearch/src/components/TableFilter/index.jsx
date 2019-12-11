import React, { Component } from 'react';
import { Menu, Dropdown, Icon, Button, Checkbox, Input} from 'antd';
import './style.scss';
import PropTypes from 'prop-types'
import Filter from 'lodash/filter'

import FilterTag from '../FilterTag';
import Loading from '../Loading';

// import {TableFilterData} from './data.js'
const Search = Input.Search;





 class TableFilter extends Component {
    constructor(props) {
        super();
        this.state = {
        	visable: false,
            checkedList: [],
            indeterminate: false,
            checkAll: false,
            Filterlist:[],
            plainOptions:[],
            menulist: props.Filterdata || [],
        }
    }

    componentWillMount() {
    	let plainOptions = this.props.Filterdata.map((item)=> {
			return item.type;
		});
		this.setState({
			plainOptions
		})
	}
	
	componentWillReceiveProps(nextProps) {
		
		if(nextProps.Filterdata !== this.props.Filterdata){

			let plainOptions = nextProps.Filterdata.map((item)=> {
				return item.type;
			});
			this.setState({
				plainOptions,
				menulist: nextProps.Filterdata
			})

		}
		if(nextProps.clear !== this.props.clear) {

			this.clickFilterResultItemAll()

		}

	}
      
	onChangeMenuItem = (e) => {
        let value = e.target["data-value"];
        let list = this.state.checkedList.slice();

        this[value + '_checked'] = e.target.checked;
        if(e.target.checked) {
            list.push(value);
            this.setState({ 
                checkedList: list,
                indeterminate: list.length !== this.state.plainOptions.length,
                checkAll: list.length === this.state.plainOptions.length
            });

        }else{
            list.splice(list.indexOf(value), 1);
            this.setState({
                checkedList: list,
                indeterminate: list.length,
                checkAll: false
            });
        }
	}

 	onBtnClick = (e) => {

		const checkedList = this.state.checkAll ? [] : this.state.checkedList;
		
		this.handleVisibleChange(false);
		this.props.getFilters(this.props.dataKey, checkedList);
	
	}

    handleVisibleChange = (flag) => { 
        this.setState({ visible: flag });
    }

    onCheckAllChange = (e) => {
    	for(let k in this.state.plainOptions) {

            this[this.state.plainOptions[k] + '_checked'] = e.target.checked;

        }
	    this.setState({
	      checkedList: e.target.checked ? this.state.plainOptions : [],
	      indeterminate: false,
	      checkAll: e.target.checked,
	    });
	}

	searchInput= (value) => {
      let filter = value
      let Filterlist = Filter(this.props.Filterdata, (item) => {
      	return item.type.indexOf(filter) !== -1;
      })
       this.setState({menulist: Filterlist})
	}
	PressEntersearchInput= (e) => {
      let filter = e.target.value
      let Filterlist = Filter(this.props.Filterdata, (item) => {
      	return item.type.indexOf(filter) !== -1;
      })
       this.setState({menulist: Filterlist})
	}

	clickFilterResultItem=(value) => {
      let checkedList = this.state.checkedList;
      checkedList.splice(checkedList.indexOf(value), 1);
      this.setState({
      	indeterminate: checkedList.length,
      	checkedList
      });
      this[value + '_checked'] = false;
	}

	clickFilterResultItemAll= () => {
      let checkedList = this.state.checkedList
      checkedList.map((item)=> {
      	this[item + '_checked'] = false;
      })
      checkedList = []
      this.setState({
      	indeterminate: false,
      	checkedList
      })
	}



	render () {
		const {menulist} = this.state

		
        
		const menuList = <Menu selectable={true}>
		            {
		                menulist.map((item, idx) => {
		                	return <Menu.Item key={item.type}>
		                	    <Checkbox  onChange={this.onChangeMenuItem.bind(this)} data-value={item.type} checked={this[item.type + '_checked']}>
		                	       {item.type}
		                	    </Checkbox>
		                	    <span className='item-right'>{item.value}</span>
		                	</Menu.Item>
		                })
		            }
				</Menu>



		let filterResult = this.state.checkedList
		filterResult = filterResult.map((val, idex) => {
           return <span key={idex} className='filterResult-item' onClick={() => this.clickFilterResultItem(val)}>{val}</span>
		})

		const menu = (
		    <div className='menu-container' selectable="true" focusable="true">
			    <div className='filterResult' selectable="true">
			       {filterResult.length <= 0 ? <div className='filterResult-tip'>已选条件</div> : <div>
			            {filterResult}
			       		{filterResult.length > 1 ? <span className='filterResult-clear' onClick={()=> this.clickFilterResultItemAll()}>清除全部</span> : null}
			       </div>}
			    </div>
				<Search
				className='search-input'
			    placeholder="搜索"
			    onSearch={this.searchInput}
			    onPressEnter={this.PressEntersearchInput}
			    style={{ width: 270 }}/>
				{menuList}
				<p>
					<Checkbox
					    className='checkAll'
						indeterminate={this.state.indeterminate}
	                    checked={this.state.checkAll} 
						onChange={this.onCheckAllChange.bind(this)}>全选</Checkbox>
					<Button type="primary" onClick={this.onBtnClick}>确定</Button>
				</p>
		    </div>
		);
		const loadingContainer = (<div selectable="true" focusable="true" className="filter-loading"><Loading title=""/></div>);
	    return (
	    	<div className='table-filter-container ' style={{display: 'inline-block'}}>
	        	<Dropdown
	        	 trigger={['click']}
	        	 visible={this.state.visible}
	        	 onVisibleChange={this.handleVisibleChange}
	        	 overlay={this.props.loading ? loadingContainer : menu}>
					<a 
						className="ant-dropdown-link" 
						data-key={this.props.dataKey} 
						href="#" 
						onClick={(e)=> {this.props.onClickFun(e.target)}}
						style={{display: 'block'}}
					>
				        {this.props.title} 
						<Icon
							onClick={(e)=> {this.props.onClickFun(e.target.parentNode)}} 
							type="down" 
						/>
				    </a>
				</Dropdown>
				<FilterTag
                    data={this.state.checkedList}
					baseShow={this.baseShow}
					checkedAll={this.state.checkAll}
                    clear={()=> {
						this.clickFilterResultItemAll()
						this.props.getFilters(this.props.dataKey, []);
					}}
                ></FilterTag>
			</div>
	    )
	 }
}


export default TableFilter

// 默认props值
// TableFilter.defaultProps = {
// 	title: '来源',
//     Filterdata: TableFilterData.data.filter.csource
// }
// props 类型
TableFilter.propTypes = {
	title: PropTypes.string,
    Filterdata: PropTypes.array
}


