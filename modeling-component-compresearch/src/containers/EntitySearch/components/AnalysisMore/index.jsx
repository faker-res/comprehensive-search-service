import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import { translate } from "react-i18next";
import ask from "../../../../lib/ask";
import { Layout, Row, Col } from "antd";
import AnalystsSelectFilter from '../../../../components/ABCComponents/CompreSearch/AnalystsSelectFilter'
import ThreeLevelSearchBox from '../../../../components/ABCComponents/CompreSearch/ThreeLevelSearchBox'
import AlphabetFilter from "../../../../components/ABCComponents/CompreSearch/AlphabetFilter";
import AnalystsAutoSelectFilter from "../../../../components/ABCComponents/CompreSearch/AnalystsAutoSelectFilter";
import AllAnalystList from "../../../../components/ABCComponents/CompreSearch/AllAnalystList";
import queryString from "query-string";
import Includes from "lodash/includes";
import "./style.scss";

const preFixCls = "abcft-entity-search-AnalysisMore";

@withRouter
// @translate()
export default class AnalysisMore extends Component {
  constructor(props) {
    super(props);
    this.state = {
      ListData: {},
      searchInputValue: "",
      AlphabetListValue: "ALL",
      IndustrySelectedValue: "",
      OrganizationSelectedValue: "全部",
      AnalysisMoreState: "done",
      limit: 10,
      prior: "ranking",
      offset: 0,
      inputSuggestList: [],
      isIncludesInduName: true
    };
    this.selectListData = [];
  }

  componentWillMount() {
    let { indu_name, org_sname } = queryString.parse(
      this.props.location.search
    );
    let info = {
      indu_name: indu_name || null,
      org_sname: org_sname || null
    };
    let induName = info.indu_name;
    this.loadRecommendGetautocompleteList();
    console.log(this.selectListData);
    let isIncludesInduName = Includes(this.selectListData, induName);
    this.setState(
      {
        isIncludesInduName,
        IndustrySelectedValue: induName,
        OrganizationSelectedValue: info.org_sname || "全部"
      },
      () => {
        this.loadList();
      }
    );
  }

  // componentDidMount = async () => {
  //     this.loadList()
  // }

  loadList = () => {
    this.setState({ AnalysisMoreState: "pending" });
    let param = {};
    param.limit = this.state.limit;
    param.name = this.state.searchInputValue;
    param.indu_name = this.state.IndustrySelectedValue;
    param.offset = this.state.offset;
    param.org_sname =
      this.state.OrganizationSelectedValue === "全部"
        ? ""
        : this.state.OrganizationSelectedValue;
    param.prior = this.state.prior;
    param.spellPrefix =
      this.state.AlphabetListValue === "ALL"
        ? ""
        : this.state.AlphabetListValue;
    ask("AnalystList", { params: param })
      .then(resp => {
        const { code, data, message } = resp;
        if (code !== 200) {
          throw new Error(`Response Exception:${message},code:${code}`);
        }
        if (data.items.length > 0) {
          this.setState({ ListData: data, AnalysisMoreState: "done" });
        } else {
          this.setState({ ListData: data, AnalysisMoreState: "error" });
        }
      })
      .catch(error => {
        console.error(error);
        this.setState({ AnalysisMoreState: "error" });
      });
  };

  getSearchInput = searchInputValue => {
    this.setState(
      {
        searchInputValue: searchInputValue.keyword,
        offset: 0
      },
      () => {
        this.loadList();
      }
    );
  };

  getAlphabetListValue = AlphabetListValue => {
    this.setState(
      {
        AlphabetListValue,
        offset: 0
      },
      () => {
        this.loadList();
      }
    );
  };

  getIndustrySelectValue = SelectedValue => {
    this.setState(
      {
        IndustrySelectedValue: SelectedValue,
        offset: 0
      },
      () => {
        this.loadList();
      }
    );
  };

  getOrganizationSelectValue = SelectedValue => {
    this.setState(
      {
        OrganizationSelectedValue: SelectedValue,
        offset: 0
      },
      () => {
        this.loadList();
      }
    );
  };

  getListParams = ListParams => {
    this.setState(
      {
        offset: ListParams.offset,
        prior: ListParams.prior
      },
      () => {
        this.loadList();
      }
    );
  };

  // 获取搜索建议
  handleRequestSearchSuggest = keyword => {
    this.setState({
      searchInputValue: keyword
    });
    ask("AnalystRecommendGetAutocompleteList", {
      params: { type: 50002, limit: 100, keyword: keyword }
    })
      .then(resp => {
        const { code, data, message } = resp;
        if (code !== 200) {
          throw new Error(`Response Exception:${message},code:${code}`);
        }
        this.setState({ inputSuggestList: data });
      })
      .catch(error => {
        console.error(error);
      });
  };
  // 获取标准化的建议数据
  getNormalizedSuggestions() {
    const { t, suggestStore } = this.props;
    const suggestions = {
      default: [],
      search: []
    };

    // 搜索建议映射
    this.state.inputSuggestList.length > 0 &&
      suggestions.search.push(
        ThreeLevelSearchBox.getNormalizedSuggestionData({
          sectionType: "searchSuggest",
          // sectionTitle: t("DefaultSearchBar.searchSuggestTitle", {defaultValue: "相关搜索"}),
          sectionTitle: "相关搜索",
          suggestions: this.state.inputSuggestList,
          suggestionId: suggestion => `searchSuggest-${suggestion.id}`,
          suggestionText: suggestion => suggestion.content,
          suggestionQuery: suggestion => suggestion.content
        })
      );

    return suggestions;
  }

  clearInput = () => {
    this.setState({
      searchInputValue: null
    });
  };

  loadRecommendGetautocompleteList = () => {
    const api = "BrokerIndustryList";
    var result = [];
    ask(api)
      .then(resp => {
        const { code, data, message } = resp;
        if (code !== 200) {
          throw new Error(`Response Exception:${message},code:${code}`);
        }
        this.selectListData = data;
        console.log(this.selectListData);
      })
      .catch(error => {
        console.error(error);
      });
  };
  render() {
    const {
      AnalysisMoreState,
      ListData,
      limit,
      offset,
      isIncludesInduName,
      IndustrySelectedValue,
      selectListData
    } = this.state;
    let { indu_name, org_sname } = queryString.parse(
      this.props.location.search
    );
    let Item_Indu_name = "";
    console.log(selectListData);
    this.selectListData &&
      this.selectListData.forEach((item, idx) => {
        if (item.indu_name === indu_name) {
          Item_Indu_name = item.indu_name;
        }
      });

    let current;
    if (offset === 0) {
      current = 1;
    } else {
      current = (offset + 10) / limit;
    }
    console.log(isIncludesInduName);
    return (
      <Layout className={`${preFixCls}`}>
        <Layout.Content>
          <Row>
            <Col span={5}>
              <AnalystsSelectFilter
                label="行业"
                defaultValue={Item_Indu_name}
                askApi="BrokerIndustryList"
                type={"AnalysisMore"}
                getSelectValue={this.getIndustrySelectValue}
              />
            </Col>
            <Col span={6}>
              <AnalystsAutoSelectFilter
                label="机构"
                defaultValue={this.state.OrganizationSelectedValue}
                askApi="AnalystorgSnameList"
                getSelectValue={this.getOrganizationSelectValue}
              />
            </Col>
            <Col style={{float:'right'}}>
              <ThreeLevelSearchBox
                style={{ width: 317, marginLeft: "-40px" }}
                placeholder="请输入分析师名称"
                suggestions={this.getNormalizedSuggestions()}
                searchHistoryTitle="最近搜过"
                onRequestSearchSuggest={this.handleRequestSearchSuggest}
                clickClearBtnCallBack={() => this.clearInput()}
                onSearch={this.getSearchInput}
              />
            </Col>
          </Row>
          <Row className="AlphabetFilter">
            <AlphabetFilter getAlphabetListValue={this.getAlphabetListValue} />
          </Row>
          <Row style={{ marginTop: "20px" }}>
            <AllAnalystList
              getListParams={this.getListParams}
              current={current}
              dataState={this.state.AnalysisMoreState}
              data={ListData}
            />
          </Row>
        </Layout.Content>
      </Layout>
    );
  }
}
