/**
 * @description 附带搜索建议的搜索框组件
 * @author jhqu
 * date: 2018-05-18
 */

import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { inject } from 'mobx-react';
import classNames from 'classnames';
import queryString from 'query-string';
import Autosuggest from 'react-autosuggest';
import _ from 'lodash';
import { Input, Icon } from 'antd';
// import { translate } from 'react-i18next';
import { withRouter } from 'react-router-dom';
import './style.scss';

const SearchBox = Input.Search;

// Autosuggest组件主题类名映射
const suggestedSearchBoxTheme = {
  container:                'suggested-search-box__container',
  containerOpen:            'suggested-search-box__container--open',
  input:                    'suggested-search-box__input',
  inputOpen:                'suggested-search-box__input--open',
  inputFocused:             'suggested-search-box__input--focused',
  suggestionsContainer:     'suggested-search-box__suggestions-container',
  suggestionsContainerOpen: 'suggested-search-box__suggestions-container--open',
  suggestionsList:          'suggested-search-box__suggestions-list',
  suggestion:               'suggested-search-box__suggestion',
  suggestionFirst:          'suggested-search-box__suggestion--first',
  suggestionHighlighted:    'suggested-search-box__suggestion--highlighted',
  sectionContainer:         'suggested-search-box__section-container',
  sectionContainerFirst:    'suggested-search-box__section-container--first',
  sectionTitle:             'suggested-search-box__section-title'
};

// 附带搜索建议的搜索框组件
@withRouter
// @inject('suggestStore')
// @translate()
export default class SuggestedSearchBox extends Component {
  // 默认属性
  static defaultProps = {
    // 搜索框占位文本
    placeholder: '',

    // 默认的搜索语句
    defaultQuery: '',

    // 建议数据对象
    suggestions: {
      // 默认建议数据
      default: [],

      // 搜索建议数据
      search: [],
    },

    // 是否显示搜索历史，默认显示
    showSearchHistory: true,

    // 搜索历史建议列表标题
    searchHistoryTitle: '',

    // 搜索历史建议列表分组标题渲染函数
    renderSearchHistoryTitle: null,

    // 搜索历史建议项渲染函数
    renderSearchHistorySuggestion: null,

    // 搜索历史最大显示条数
    searchHistoryDisplayCount: 5,
    
    // 搜索历史最大存储条数
    searchHistorySaveCount: 20,

    // 搜索历史的存储Key，用于客户端存储标识
    searchHistoryKey: 'SEARCH_HISTORY',

    // 搜索历史的命名空间，用于区别搜索历史的作用域，为空则使用全局搜索历史
    searchHistoryNS: '',

    // 请求搜索建议事件回调
    onRequestSearchSuggest: () => {},

    // 搜索事件回调
    onSearch: null,
  }

  // 属性类型
  static propTypes = {
    // 搜索框占位文本
    placeholder: PropTypes.string,

    // 默认的搜索语句
    defaultQuery: PropTypes.string,

    // 建议数据对象
    suggestions: PropTypes.shape({
      // 默认建议数据
      default: PropTypes.arrayOf(PropTypes.object),

      // 搜索建议数据
      search: PropTypes.arrayOf(PropTypes.object),
    }),

    // 是否显示搜索历史
    showSearchHistory: PropTypes.bool,

    // 搜索历史建议列表标题
    searchHistoryTitle: PropTypes.string,

    // 搜索历史建议列表分组标题渲染函数
    renderSearchHistoryTitle: PropTypes.func,

    // 搜索历史建议项渲染函数
    renderSearchHistorySuggestion: PropTypes.func,

    // 搜索历史最大显示条数
    searchHistoryDisplayCount: PropTypes.number,
    
    // 搜索历史最大存储条数
    searchHistorySaveCount: PropTypes.number,

    // 搜索历史的存储Key，用于客户端存储标识
    searchHistoryKey: PropTypes.string,

    // 搜索历史的命名空间，用于区别搜索历史的作用域，为空则使用全局搜索历史
    searchHistoryNS: PropTypes.string,

    // 请求搜索建议事件回调
    onRequestSearchSuggest: PropTypes.func,

    // 搜索事件回调
    onSearch: PropTypes.func,
  }

  constructor (props) {
    super(props);

    const query = this.getQuery();
    const keyword = (query.keyword || '').trim();
    
    // 搜索框引用
    this.searchInput = null;

    // 用于重置搜索框禁用状态的定时器ID，详见#1
    this.resetDisableTimerId = null;

    this.state = {
      // 搜索关键字
      keyword,

      // 搜索框聚焦状态
      isFocused: false,

      // 搜索历史
      searchHistory: this.loadLocalSearchHistory(),

      // 建议列表
      suggestList: [],
    };
  }

  componentDidMount () {
    // 初始化建议列表
    this.setState({suggestList: this.getCurrentSuggestList()});

    // 处理移动端页面滚动后搜索提示不消失问题
    // 在页面非搜索框组件区域touchstart时隐藏搜索提示
    document.body.addEventListener('touchstart', this.handlePageTouchStart, false);
    document.querySelector('.suggested-search-box')
      .addEventListener('touchstart', this.handleSearchBoxTouchStart, false);
  }

  componentWillUnmount () {
    // 清除重置搜索框禁用状态的定时器，防止组件卸载后调用出错，详见#1
    this.resetDisableTimerId && clearTimeout(this.resetDisableTimerId);

    // 移除touchstart事件的监听
    document.body.removeEventListener('touchstart', this.handlePageTouchStart, false);
    document.querySelector('.suggested-search-box')
      .removeEventListener('touchstart', this.handleSearchBoxTouchStart, false);
  }

  // 处理状态更新
  componentDidUpdate (prevProps, prevState) {
    // 如果路由中的搜索关键字发生改变，将其同步到搜索框状态
    const keyword = (this.getQuery().keyword || '').trim();
    const prevKeyword = (this.getQuery(prevProps.location.search).keyword || '').trim();
    keyword !== prevKeyword && this.setState({keyword});
    // 如果传递给组件的属性发送变化，或搜索关键字发送变化
    if (prevProps !== this.props || prevState.searchHistory !== this.state.searchHistory) {
      // 更新建议列表
      this.setState({suggestList: this.getCurrentSuggestList()});
    }
  }

  // 获取查询参数
  getQuery = (search) => {
    return queryString.parse(
      _.isUndefined(search)
        ? this.props.location.search
        : search
    );
  }

  // 搜索关键字改变
  hanleChangeKeyword = (e, {newValue, method}) => {
    this.setState({keyword: newValue});
  }

  // 输入框获得焦点
  handleInputFocus = (e) => {
    this.setState({isFocused: true, suggestList: this.getCurrentSuggestList()});
  }

  // 输入框失去焦点
  handleInputBlur = (e) => {
    // 如果是清空按钮点击触发的失焦则暂不切换搜索框的焦点状态，清空按钮将重新聚焦搜索框
		// 同时也为了避免失焦后清空按钮提前隐藏而导致无法触发其点击事件
    if (e.relatedTarget && e.relatedTarget.classList.contains('suggested-search-box__btn-empty')) {
      return false;
    }

    this.setState({isFocused: false});
  }

  // 页面touchstart处理
  handlePageTouchStart = (e) => {
    // 在页面非搜索框组件区域touchstart时隐藏搜索提示
    this.searchInput.blur();
  }

  // 搜索框组件区域touchstart处理
  handleSearchBoxTouchStart = (e) => {
    // 阻止搜索框组件区域的touchstart事件冒泡
    e.stopPropagation();
  }

  // 清空搜索关键字
  emptyKeyword = () => {
    this.searchInput.focus();
    this.setState({keyword: ''}, () => {
      // 更新建议列表
      this.setState({suggestList: this.getCurrentSuggestList()});
    });
  }

  //触发搜索的来源
  searchSourceFrom = ({suggestion,suggestionValue, location, method})=>{
    // let input_from, page = 'comprehensive_search';
    // if(!suggestion){
    //   input_from = 'direct';
    // }else{
    //   if(suggestion.id.indexOf('searchHistory') > -1){
    //     input_from = 'history';
    //   }else if(suggestion.id.indexOf('hotSearch') > -1){
    //     input_from = 'hot';
    //   }else if(suggestion.id.indexOf('searchSuggest') > -1){
    //     input_from = 'suggest';
    //   }else{
    //     input_from = 'direct';
    //   };
    // };
    // if(location.pathname === '/') page = 'homepage';
    // this.props.suggestStore.page = page;
    // this.props.suggestStore.input_from = input_from;
    this.handleSearch(suggestionValue);
  }

  // 搜索处理
  handleSearch = (value) => {
    // 更新搜索关键字到路由查询参数
    const { history } = this.props;
    const query = this.getQuery();
    const keyword = value.trim();
    query.keyword = query.keyword || '';
    const method = query.keyword.trim() !== keyword ? 'push' : 'replace';
    query.keyword = keyword || this.props.defaultQuery;
    const search = queryString.stringify(query);
    const search_keyword = queryString.stringify({keyword : query.keyword});

    // 搜索时移除搜索框的焦点，使搜索提示列表隐藏
    this.searchInput.blur();

    // 如果存在搜索事件回调函数，调用搜索回调
    if (_.isFunction(this.props.onSearch)) {
      this.props.onSearch({keyword: query.keyword||'新能源汽车销量'});
    } else {
      // 更新路由
      history[method]({search : search_keyword});

      // 更新搜索框
      query.keyword !== keyword && this.setState({keyword: query.keyword});
    }

    // 添加到搜索历史
    if (query.keyword) {
      this.addSearchHistory({keyword: query.keyword});
    }

    // #1:
    // Hack: 屏蔽antd的Search组件点击搜索按钮后自动激活搜索框的问题
    this.searchInput && (this.searchInput.input.input.disabled = true);
    this.resetDisableTimerId = setTimeout(() => {
      this.searchInput
        && this.searchInput.input
        && this.searchInput.input.input
        && (this.searchInput.input.input.disabled = false);
    }, 0);
  }

  // 搜索历史的存储Key
  get searchHistoryDataKey () {
    const {searchHistoryKey, searchHistoryNS} = this.props;

    return searchHistoryNS
      ? [searchHistoryKey, searchHistoryNS].join('.')
      : searchHistoryKey;
  }

  // 加载本地存储的搜索历史
  loadLocalSearchHistory () {
    try {
      const data = localStorage.getItem(this.searchHistoryDataKey);
      if (data !== null) {
        return JSON.parse(data);
      }

      return [];
    } catch (err) {
      // 忽略读取错误
      return [];
    }
  }

  // 更新搜索历史状态并保存到本地存储
  updateSearchHistory = (data) => {
    this.setState({searchHistory: data});
    try {
      localStorage.setItem(this.searchHistoryDataKey, JSON.stringify(data));
      // add by kygeng update searchhistory of state
      this.setState({ searchHistory: this.loadLocalSearchHistory() });
    } catch (err) {
      // 忽略写入错误
    }
  }

  // 添加搜索历史
  addSearchHistory (item) {
    // 将搜索记录添加到搜索历史记录的最前面，创建成一个新的历史记录
    let newHistory = [item, ...this.state.searchHistory];

    // 去除旧的重复的历史记录
    newHistory = _.uniqWith(newHistory, _.isEqual);
    
    // 限制历史记录的最大存储条数
    newHistory = newHistory.slice(0, this.props.searchHistorySaveCount);

    // 更新搜索历史状态并保存到本地存储
    this.updateSearchHistory(newHistory);
  }

  // 删除搜索历史
  removeSearchHistory (item) {
    // 删除对应的历史记录项，创建成一个新的历史记录
    let newHistory = this.state.searchHistory.filter(i => !_.isEqual(i, item));

    // 更新搜索历史状态并保存到本地存储
    this.updateSearchHistory(newHistory);
  }

  // 清空搜索历史
  emptySearchHistory () {
    // 清空搜索历史状态并保存到本地存储
    this.updateSearchHistory([]);
  }

  // 防止搜索历史项删除按钮点击时搜索框失焦导致建议列表无法失焦隐藏
  prevntSearchInputBlur = (e) => {
    e.preventDefault();
    e.stopPropagation();
  }

  // 删除搜索历史记录项处理
  handleRemoveHistroyItem = (e, item) => {
    e.preventDefault();
    e.stopPropagation();
    this.removeSearchHistory(item);
  }

  // 清空搜索历史处理
  handleEmptySearchHistory = (e) => {
    e.preventDefault();
    e.stopPropagation();
    this.emptySearchHistory();
  }

  // 判断是否需要渲染建议列表
  shouldRenderSuggestList = (value) => {
    // 在搜索框聚焦时始终显示建议列表
    return true;
  }

  // 请求搜索建议处理
  handleRequestSearchSuggest = ({value}) => {
    value = value.trim();

    // 默认调用请求搜索建议事件回调
    this.props.onRequestSearchSuggest(value);
  }

  // 请求清除建议列表处理
  handleRequestClearSuggestList = () => {
    this.setState({suggestList: []});
  }

  // 获取建议列表分组的建议字段数据
  getSectionSuggestions = (section) => {
    return section.suggestions;
  }

  // 获取建议列表项的建议值字段数据
  getSuggestionValue = (suggestion) => {
    return suggestion.query;
  }

  // 获取标准化的建议数据对象
  // 标准建议数据对象格式：
  // {
  //   type: '建议数据分组类型',
  //   title: '建议数据分组标题',
  //   // 自定义的数据对象
  //   data: {...},
  //   // 自定义的分组标题渲染函数，传入参数为当前的建议数据对象
  //   renderSectionTitle: (suggestionData) => {...},
  //   // 自定义的建议项渲染函数，传入的第一个参数为当前的建议项数据对象
  //   // 第二个参数中query为原始查询字符串，isHighlighted表示当建议项是否被高亮
  //   renderSuggestion: (suggestion, {query, isHighlighted}) => {...},
  //   // 建议数据数组
  //   suggestions: [
  //     id: '建议数据项的唯一标识符',
  //     text: '建议数据项的显示文本',
  //     query: '建议数据项的搜索文本',
  //     // 原始建议数据项对象
  //     originSuggestion: {...},
  //   ]
  // }
  static getNormalizedSuggestionData (fieldMap) {
    // 如果字段映射参数不是一个对象，抛出异常
    if (!_.isObject(fieldMap)) {
      throw new Error('fieldMap param must an object.');
    }

    // 如果字段映射对象中的建议数据数组属性不是一个数组，抛出异常
    if (!_.isArray(fieldMap.suggestions)) {
      throw new Error('fieldMap.suggestions property must an array.');
    }

    // 如果字段映射对象中的分组标题渲染函数不是一个函数，抛出异常
    if (!_.isUndefined(fieldMap.renderSectionTitle) && !_.isFunction(fieldMap.renderSectionTitle)) {
      throw new Error('fieldMap.renderSectionTitle property must an function.');
    }

    // 如果字段映射对象中的分组建议项渲染函数不是一个函数，抛出异常
    if (!_.isUndefined(fieldMap.renderSuggestion) && !_.isFunction(fieldMap.renderSuggestion)) {
      throw new Error('fieldMap.renderSuggestion property must an function.');
    }

    // 标准建议数据对象
    const data = {
      type: fieldMap.sectionType,
      title: fieldMap.sectionTitle,
      data: fieldMap.data || {},
      renderSectionTitle: fieldMap.renderSectionTitle,
      renderSuggestion: fieldMap.renderSuggestion,
      suggestions: [],
    }

    // 映射建议数据数组
    data.suggestions = fieldMap.suggestions.map((suggestion, index) => ({
      id: _.isFunction(fieldMap.suggestionId) ? fieldMap.suggestionId(suggestion, index) : suggestion.id,
      text: _.isFunction(fieldMap.suggestionText) ? fieldMap.suggestionText(suggestion, index) : suggestion.text,
      query: _.isFunction(fieldMap.suggestionQuery) ? fieldMap.suggestionQuery(suggestion, index) : suggestion.query,
      renderSuggestion: data.renderSuggestion,
      originSuggestion: suggestion,
    }));

    return data;
  }

  // 获取标准化的搜索历史建议数据对象
  getNormalizedSearchHistory (keyword = this.state.keyword) {
    const {
      searchHistoryTitle,
      searchHistoryDisplayCount,
      renderSearchHistoryTitle,
      renderSearchHistorySuggestion
    } = this.props;
    const { searchHistory } = this.state;

    // 如果搜索关键字不为空，返回包含关键字的搜索历史
    let history = keyword === '' ? searchHistory : searchHistory.filter(item => {
      return item.keyword.toLowerCase().indexOf(keyword.toLowerCase()) >= 0;
    });

    // 限制显示条数
    history = history.slice(0, searchHistoryDisplayCount);

    // 分组标题渲染函数
    const renderTitle = renderSearchHistoryTitle || ((section) => (
      this.renderSectionTitle({
        ...section,

        // 移除自定义渲染函数避免循环调用
        renderSectionTitle: null,
      }, {isSearchHistory: true})
    ));

    // 建议项渲染函数
    const renderSuggestion = renderSearchHistorySuggestion || ((suggestion, args) => (
      this.renderSuggestion({
        ...suggestion,

        // 移除自定义渲染函数避免循环调用
        renderSuggestion: null,
      }, {...args, isSearchHistory: true})
    ));

    // 如果搜索历史不为空时返回标准化后的历史记录建议数据对象，否则返回null
    return history.length > 0 ? SuggestedSearchBox.getNormalizedSuggestionData({
      sectionType: 'searchHistory',
      sectionTitle: searchHistoryTitle,
      suggestions: history,
      suggestionId: (suggestion, index) => `searchHistory-${index}`,
      suggestionText: (suggestion) => suggestion.keyword,
      suggestionQuery: (suggestion) => suggestion.keyword,
      renderSectionTitle: renderTitle,
      renderSuggestion: renderSuggestion,
    }) : null;
  }

  // 获取当前建议列表数据
  getCurrentSuggestList () {
    const {suggestions, showSearchHistory} = this.props;
    const keyword = this.state.keyword.trim();
    const suggestList = [];

    // 如果需要显示搜索历史，且搜索历史不为空时将搜索历史添加到建议列表数组
    if (showSearchHistory) {
      let searchHistory = this.getNormalizedSearchHistory();
      searchHistory !== null && suggestList.push(searchHistory);
    }
    
    return keyword
      ? suggestList.concat(suggestions.search)
      : suggestList.concat(suggestions.default);
  }

  // 渲染搜索框组件
  renderSearchBox = (inputProps) => {
    return (
      <SearchBox {...inputProps} />
    );
  }

  // 渲染建议列表分组标题
  renderSectionTitle = (section, {isSearchHistory} = {}) => {
    const { t } = this.props;

    // 如果存在自定义的分组标题渲染函数，返回其渲染结果
    if (_.isFunction(section.renderSectionTitle)) {
      return section.renderSectionTitle(section);
    }

    return (
      <div key={section.type}>
        {section.title}
        {isSearchHistory &&
          <Icon
            type="delete"
            key="btn-empty-history"
            className="suggested-search-box__action-btn suggested-search-box__btn-empty-history"
            onMouseDown={this.prevntSearchInputBlur}
            onClick={this.handleEmptySearchHistory}
            title="删除全部"
          />
        }
      </div>
    );
  }

  // 渲染建议列表建议项
  renderSuggestion = (suggestion, {query, isHighlighted, isSearchHistory}) => {
    const { t } = this.props;


    // 如果存在自定义的建议项渲染函数，返回其渲染结果
    if (_.isFunction(suggestion.renderSuggestion)) {
      return suggestion.renderSuggestion(suggestion, {query, isHighlighted});
    }

    return (
      <div key={suggestion.id}>
        {suggestion.text}
        {isSearchHistory &&
          <Icon
            type="close-circle"
            key="btn-remove-history-item"
            className="suggested-search-box__action-btn suggested-search-box__btn-remove-history-item"
            onMouseDown={this.prevntSearchInputBlur}
            onClick={e => this.handleRemoveHistroyItem(e, suggestion.originSuggestion)}
            title="删除"
          />
        }
      </div>
    );
  }

  render () {
    const { t, style, className, placeholder, location } = this.props;
    const { keyword } = this.state;
    const searchBoxClass = classNames(
      'suggested-search-box',
      className,
      {
        'is-show-btn-empty': this.state.isFocused,
      }
    );

    // 后缀按钮（清空按钮）渲染逻辑
    const suffix = keyword
      ? <Icon
          type="close-circle"
          key="btn-empty"
          tabIndex="0"
          className="suggested-search-box__icon-suffix suggested-search-box__btn-empty"
          onClick={this.emptyKeyword}
          onMouseDown={this.prevntSearchInputBlur}
        />
      : null;
    
    // 搜索框属性
    const inputProps = {
      suffix,
      maxLength:10,
      placeholder,
      value: keyword,
      prefix : <Icon type="search" className="suggested-search-box__icon-prefix" />,
      enterButton: '搜索',
      onFocus: this.handleInputFocus,
      onBlur: this.handleInputBlur,
      onChange: this.hanleChangeKeyword,
      onSearch: (newValue) =>{
        this.searchSourceFrom({suggestionValue : newValue, location});
      },
    };
    return (
      <div style={style} className={searchBoxClass}>
        <Autosuggest
          theme={suggestedSearchBoxTheme}
          multiSection={true}
          suggestions={this.state.suggestList}
          getSectionSuggestions={this.getSectionSuggestions}
          getSuggestionValue={this.getSuggestionValue}
          renderSectionTitle={this.renderSectionTitle}
          renderSuggestion={this.renderSuggestion}
          renderInputComponent={this.renderSearchBox}
          inputProps={inputProps}
          ref={node => node !== null && (this.searchInput = node.input)}
          focusInputOnSuggestionClick={false}
          shouldRenderSuggestions={this.shouldRenderSuggestList}
          onSuggestionsFetchRequested={this.handleRequestSearchSuggest}
          onSuggestionsClearRequested={this.handleRequestClearSuggestList}
          onSuggestionSelected={(e, {suggestion,suggestionValue,method}) => this.searchSourceFrom({suggestion, suggestionValue, location, method})}
        />
      </div>
    );
  }
}
