import React, { Component } from "react";
import { observer, inject } from "mobx-react";
import { Icon, Button, Input, AutoComplete, Tooltip } from "antd";
// import BatchAddMenu from "../BatchAddMenu";
import "./index.scss";

const Option = AutoComplete.Option;

@inject(Stores => ({
  clearSuggest: Stores.searchAddSingleInput.clearSuggest,
}))
@observer
class SearchAddSingleInput extends Component {
  static defaultProps = {
    showBatchAddMenu: true,
  };
  constructor(props) {
    super(props);
    this.state = {
      focused: false,
      hasValue: false
    };
  }
  handleSelect = (value, option) => {
    this.props.handleAddStockSelect(value, option);
  };
  handleSearch = value => {
    console.log(value);
    if (value.trim() == "") {
      this.setState({
        hasValue: false
      });
    } else {
      this.setState({
        hasValue: true
      });
    }
    this.props.onSearch(value);
  };
  handleBlur = evt => {
    this.setState({
      focused: false
    });
  };
  handleFocus = evt => {
    let value = evt.target.value.trim();
    if (value != "") {
      this.setState({
        hasValue: true
      });
      this.handleSearch(value);
    } else {
      this.setState({
        hasValue: false
      });
    }
    this.setState({
      focused: true
    });
  };
  renderItems = () => {
    if (this.state.hasValue) {
      if (this.props.dataSource.length > 0) {
        return this.props.dataSource.map(this.renderOption);
      } else {
        return [
          <Option disabled key={0}>
            对不起，没有找到相关内容
          </Option>
        ];
      }
    }
  };
  handleDisabledNew = (item, page) => {
    let disabled = true;
    let { pageValue } = this.props;
    let { status, substatus, holdstatus } = item;
    if (page == 'myStock') {
      if (substatus == 0 && (status == null || status == 0 || status == -1 || status == -2)) disabled = false;
    } else if (pageValue != undefined && page == 'myStockOwned' && (status == null || status == 0 || status == -1)) {
      if (holdstatus == 0) {
        disabled = false;
      } else {
        if (pageValue == 0 && holdstatus == 1) disabled = false;
        if (pageValue == 1 && holdstatus == 2) disabled = false;
      }
    }
    return disabled;
  }
  renderOption = (item, index) => {
    let { status, substatus, holdstatus, sec_uni_code } = item;
    // 判断是否能选中
    let statusObj = {
      '-1': '停牌',
      '-2': '退市',
      '-3': '未上市'
    };
    let { page } = this.props;
    let disabled = this.handleDisabledNew(item, page);
    let dom = <span>
      <span className="stock-code">{item.abc_code}</span>
      <Tooltip title={item.sec_name}>
        <span className="stock-name">{item.sec_name}</span>
      </Tooltip>
      {this.renderItem(item, page)}
    </span>
    let option = dom;
    if (disabled) {
      if ((page == 'myStock' && substatus != 1) || (page == 'myStockOwned' && status < -1)) {
        option = (
          <Tooltip title={`暂不支持${statusObj[status + '']}股票添加`} placement="top">
            {dom}
          </Tooltip>
        )
      }
    }
    return (<Option
      disabled={disabled}
      className="ms-stocksearch-item"
      key={index}
      text={item.sec_name}
      code={item.abc_code}
      marketcode={item.sec_type}
      secUniCode={sec_uni_code}
    >
      {option}
    </Option>);

    return option;
  };
  renderItem = (item, page) => {
    let { status, substatus, holdstatus } = item;
    let { pageValue } = this.props;
    if (page == 'myStock') {
      if (substatus == 0 && (status == 0 || status == null || status == -1 || status == -2)) {
        return <Icon type="plus" className="icon-add" />;
      } else if (substatus == 1) {
        return <Icon type="check" style={{ float: "right", marginTop: "5px" }} />;
      }
    } else if (pageValue && page == 'myStockOwned') {
      if (status == 0 || status == null || status == -1) {
        if (holdstatus == 2 && pageValue == 0) {
          return <span style={{ float: "right" }}>(已模拟持仓)</span>;
        }
        if (holdstatus == 1 && pageValue == 1) {
          return <span style={{ float: "right" }}>(已实际持仓)</span>;
        }
      }
      return '';
    }
  };
  render() {
    return (
      <div className="search-batch-add">
        <div
          className={
            this.state.focused == true
              ? "ms-stocksearch-wrap focused"
              : "ms-stocksearch-wrap"
          }
        >
          <Icon type="search" />
          <AutoComplete
            className={`global-search ${
              this.props.showBatchAddMenu ? "showBatchAddMenu" : ""
              }`}
            allowClear={true}
            size="large"
            // style={{ width: "100%" }}
            dataSource={this.renderItems()}
            onSelect={this.handleSelect}
            onSearch={this.handleSearch}
            optionLabelProp="text"
            children={
              <input
                type="text"
                className="searchInput"
                placeholder={
                  this.props.placeholder ? this.props.placeholder : "代码/简拼/名称"
                }
                onFocus={this.handleFocus}
                onBlur={this.handleBlur}
              />
            }
          />
        </div>
        {/* {this.props.showBatchAddMenu ? <BatchAddMenu /> : ""} */}
      </div>
    );
  }
}
export default SearchAddSingleInput;
