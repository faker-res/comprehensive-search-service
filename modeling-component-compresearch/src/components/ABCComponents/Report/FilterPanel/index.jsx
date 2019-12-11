/**
 * @description 筛选面板组件
 * @author jhqu
 * date: 2018-07-10
 */

import React, { Component } from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';
import memoize from 'memoize-one';
import moment from 'moment';
import _ from 'lodash';
import PinyinEngine from 'pinyin-engine';
import { Tag, Icon, Spin, Button, Select, DatePicker, Input } from 'antd';
import { translate } from 'react-i18next';
import { withRouter } from 'react-router-dom';
import './style.scss';

const { Option } = Select;
const { CheckableTag } = Tag;
const { RangePicker } = DatePicker;
const InputGroup = Input.Group;

// 组件BEM基础类（Block）
const blockClass = 'filter-panel';

// 获取元素的外部高度（包含margin）
function getOuterHeight (elem) {
  elem = (typeof elem === 'string') ? document.querySelector(elem) : elem; 
  var styles = window.getComputedStyle(elem);
  var margin = parseFloat(styles['marginTop']) + parseFloat(styles['marginBottom']);
  return Math.ceil(elem.offsetHeight + margin);
}

// 筛选面板组件
@withRouter
export default class FilterPanel extends Component {
  // 默认属性
  static defaultProps = {
    // 分类标签列表
    categorys: [],

    // 当前已选标签
    selectedTags: [],

    // 分类列表项最大显示数量，超出将被折叠隐藏
    categoryMaxShowCount: 2,

    // 分类标签最大显示数量
    categoryMaxShowTagsCount: 30,

    // 筛选项发生改变时的事件回调
    onFilterChange: (tags) => {},

    // 筛选面板额外头部（已选标签栏区域尾部）渲染函数
    renderExtraHeader: (categories, selectedTags) => {},

    // 是否显示自定义的关键词分类
    showKeywordsCategory: true,
  }

  // 属性类型
  static propTypes = {
    // 分类标签列表
    categorys: PropTypes.arrayOf(PropTypes.object),

    // 当前已选标签列表
    selectedTags: PropTypes.arrayOf(PropTypes.object),

    // 分类列表项最大显示数量，超出将被折叠隐藏
    categoryMaxShowCount: PropTypes.number,

    // 分类标签最大显示数量
    categoryMaxShowTagsCount: PropTypes.number,

    // 筛选项发生改变时的事件回调
    onFilterChange: PropTypes.func,
    
    // 筛选面板额外头部（已选标签栏区域尾部）渲染函数
    renderExtraHeader: PropTypes.func,

    // 是否显示自定义的关键词分类
    showKeywordsCategory: PropTypes.bool,
  }

  // 相关状态
  state = {
    // 是否在加载数据中
    isLoading: false,

    // 分类标签列表
    categorys: [],

    // 当前已选标签列表
    selectedTags: [],

    // 是否展开筛选面板的高级搜索选项
    isExpandPanel: false,

    // 需要显示展开/折叠按钮的分类项
    showCollapseCategories: [],

    // 当前激活的分类标签列表项
    currentActiveCategories: [],
  }

  // 分类列表引用
  categoryList = React.createRef();

  componentDidMount () {
    const { categorys, selectedTags } = this.props;

    // 如果分类标签列表不为空时将其更新到内部状态
    if (!_.isEmpty(categorys) && _.isArray(selectedTags)) {
      this.updateStateFromProps();
    }
  }

  componentDidUpdate (prevProps) {
    const { categorys, selectedTags } = this.props;
    // 属性发生改变时将属性更新到状态
    if (
      !_.isEqual(prevProps.selectedTags, selectedTags)
      || !_.isEqual(prevProps.categorys, categorys)
    ) {
      this.updateStateFromProps();
    }
  }

  // 更新属性数据到内部状态
  updateStateFromProps = () => {
    const { categorys, selectedTags } = this.props;

    this.setState({
      ...this.getNormalizedState(categorys, selectedTags),
      isLoading: false,

    // 更新并渲染完成后根据标签列表行数设置是否需要显示展开/折叠按钮
    }, this.setShowCollapseCategories);
  }

  // 获取标准化的状态——category.item
  getNormalizedState = memoize((categorys = [], selectedTags = []) => {
    // 标准化的分类标签列表
    const normalizedCategorys = categorys.map(c => {
      const category = {
        ...c,

        // 过滤掉name重复和为空的无效标签数据
        item: _.uniqBy(c.item, 'name').filter(tag => !_.isEmpty(tag.name)).map(tag => ({
          ...tag,

          // 设置每个标签的type属性
          type: c.type,

          // 设置每个标签的selected属性
          selected: selectedTags.some(st => st.type === c.type && st.name === tag.name)
        }))
      };

      // 类型为时间范围时，添加自定义时间范围标签
      const selectedDateRange = selectedTags.find(st => st.type === c.type && st.name === '自定义');
      c.type === 'time_area' && category.item.push({
        id: 'DateRangePicker',
        name: '自定义',
        type: c.type,
        min: selectedDateRange ? selectedDateRange.min : undefined,
        max: selectedDateRange ? selectedDateRange.max : undefined,
        selected: !!selectedDateRange,
      });

      return category;
    });

    // 标准化的已选标签列表
    const normalizedSelectedTags = selectedTags.map(tag => ({...tag, selected: true}));

    // 添加自定义的关键词分类
    this.props.showKeywordsCategory && normalizedCategorys.unshift({
      type: 'keywords',
      showname: '关键词',
      multiValued: false,
      item: [],
      scopeOptions: [
        {value: 'title', text: '标题'},
        {value: 'body', text: '正文'},
        {value: 'all', text: '标题或正文'},
      ],
    });

    return {
      categorys: normalizedCategorys,
      selectedTags: normalizedSelectedTags,
    };
  });

  // 根据标签列表行数设置需要显示展开/折叠按钮的分类标签列表项
  setShowCollapseCategories () {
    const { categorys } = this.state;
    const categoryList = this.categoryList.current;

    if (!categoryList || _.isEmpty(categorys)) {
      return;
    }

    let items = categoryList.querySelectorAll(`.${blockClass}__category-item`);
    const tagHeight = getOuterHeight(categoryList.querySelector(`.${blockClass}__category-tags-wrapper .ant-tag`));
    const showCollapseCategories = [];
    if (categorys[0].type === 'keywords') {
        items = _.concat(_.last(items), _.initial(items))
    }
    this.setState({items})
    // 遍历分类标签列表项，找标签列表行数超过一行的所有列表项
    _.forEach(items, (item, index) => {
      if (item.querySelector(`.${blockClass}__category-tags-wrapper`).offsetHeight > tagHeight) {
        showCollapseCategories.push(categorys[index].type);
      }
    });

    this.setState({showCollapseCategories});
  }

  // 切换筛选面板的高级搜索选项展开/折叠状态
  togglePanelCollapse = (e) => {
    this.setState(prevState => ({isExpandPanel: !prevState.isExpandPanel}));
  }

  // 当前激活的分类项发生改变处理
  handleActiveCategoryChange = (key) => {
    this.setState({currentActiveCategories: [key]});
  }

  // 清除已选标签
  clearSelectedTag = (tag) => {
    this.toggleTagsState([tag]);
  }

  // 清除全部已选标签
  clearAllSelectedTag = () => {
    this.toggleTagsState(this.state.selectedTags);
  }

  // 切换标签选中/取消选中状态
  toggleTagsState = (tags) => {
    this.setState(prevState => {
      let { categorys, selectedTags } = prevState;
      categorys = [...categorys];
      selectedTags = [...selectedTags];

      tags.forEach(tag => {
        // 切换分类下标签的选中状态
        const category = categorys[categorys.findIndex(c => c.type === tag.type)];
        const selected = !tag.selected;
        if (category) {
          const tagIndex = category.item.findIndex(t => t.type === tag.type && t.name === tag.name)
          tagIndex >= 0 && (category.item[tagIndex].selected = selected);
        }

        // 移除重复的标签
        _.remove(selectedTags, t => t.type === tag.type && t.name === tag.name);

        // 将标签添加到已选标签中，或从已选标签中删除
        selected
          // 添加
          ? selectedTags.push(tag)

          // 删除
          : _.remove(selectedTags, t => t.type === tag.type && t.name === tag.name);
      });

      return {categorys, selectedTags, isLoading: true};
    }, this.handleSelectedTagsChange);
  }

  // 筛选项发生改变处理
  handleSelectedTagsChange = () => {
    const { selectedTags } = this.state;

    // 调用筛选项发生改变事件回调
    this.props.onFilterChange(selectedTags);
  }

  render () {
    const {
      renderExtraHeader,
      categoryMaxShowCount,
      categoryMaxShowTagsCount,
    } = this.props;
    let {
      isLoading,
      categorys,
      selectedTags,
      isExpandPanel,
      showCollapseCategories,
      currentActiveCategories,
    } = this.state;

    // 筛选面板样式类
    const panelClass = classNames(blockClass, this.props.className, {
      'is-expand': isExpandPanel,
    });

    // 是否显示高级搜索选项展开/折叠栏
    const isShowCollapseBar = categorys.length > categoryMaxShowCount;

    // 如果存在“关键词”分类，且筛选面板处于折叠状态时，将其移至列表尾部并折叠隐藏
    // 仅在展开筛选面板的高级搜索选项时显示自定义的“关键词”分类，且显示在列表头部
    if (
      isShowCollapseBar
      && !isExpandPanel
      && !_.isEmpty(categorys)
      && categorys[0].type === 'keywords'
    ) {
      categorys = _.concat(_.tail(categorys), _.head(categorys));
    }

    // 如果categorys 只包含 时间和关键字类型 则不显示订阅按钮
    const showSubscribe = !!_.find(categorys, each => !_.isEmpty(each.item) && each.type !== 'time_area');
    
    return (
      <div
        className={panelClass}
        style={this.props.style}
      >
        <Spin
          size="large"
          tip="加载中..."
          spinning={isLoading}
        >
          {/* 已选标签列表栏 */}
          {!_.isEmpty(selectedTags) &&
            <div className={`${blockClass}__selected-tags-bar`}>
              {selectedTags.map(tag =>
                <Tag
                  key={`${tag.type}-${tag.name}`}
                  closable
                  onClose={() => this.clearSelectedTag(tag)}
                >
                  {tag.type === 'time_area' && tag.name === '自定义'
                    ? `时间：${tag.min} ~ ${tag.max}`
                    : tag.name
                  }
                </Tag>
              )}
              <Button
                size="small"
                onClick={this.clearAllSelectedTag}
              >
                <Icon type="delete" />清除全部
              </Button>
              
              {/* 渲染额外头部 */}
              {showSubscribe &&
                _.isFunction(renderExtraHeader) && renderExtraHeader(categorys, selectedTags)
              }
            </div>
          }

          {/* 分类列表 */}
          <ul ref={this.categoryList} className={`${blockClass}__category-list`}>
            {categorys.map((category, index) =>
              <CategoryItem
                key={category.type}
                category={category}
                isDefaultCollapse={index >= categoryMaxShowCount}
                selectedTags={selectedTags.filter(c => c.type === category.type)}
                active={currentActiveCategories.includes(category.type)}
                showCollapse={showCollapseCategories.includes(category.type)}
                maxShowTagsCount={categoryMaxShowTagsCount}
                onActiveStateChange={this.handleActiveCategoryChange}
                onSelectedTagsChange={this.toggleTagsState}
              />
            )}
          </ul>

          {/* 高级搜索选项展开/折叠栏 */}
          {isShowCollapseBar &&
            <div className={`${blockClass}__collapse-bar`}>
              <span
                className={`${blockClass}__collapse-toggle-button`}
                onClick={this.togglePanelCollapse}
              >
                高级搜索选项
                <Icon type="down" />
              </span>
            </div>
          }
        </Spin>
      </div>
    );
  }
}

// 分类标签列表项组件
class CategoryItem extends Component {
  // 相关状态
  state = {
    // 分类项是否展开
    isExpand: false,

    // 分类项是否激活多选模式
    isMultiSelectActive: false,

    // 多选模式下的标签列表
    multiSelectModeTags: [],
  }

  static getDerivedStateFromProps (nextProps) {
    // 当前分类项为非激活状态时，始终关闭其展开或多选状态
    if (!nextProps.active) {
      return {
        isExpand: false,
        isMultiSelectActive: false,
      };
    }

    return null;
  }

  // 切换分类项的展开状态
  toggleExpandState = () => {
    this.setState(prevState => ({
      isExpand: !prevState.isExpand,
      isMultiSelectActive: false,
    }));
    this.props.onActiveStateChange(this.props.category.type);
  }

  // 切换分类项的多选状态
  toggleMultiSelectState = () => {
    this.setState((prevState, props) => ({
      isExpand: false,
      isMultiSelectActive: !prevState.isMultiSelectActive,
      multiSelectModeTags: !prevState.isMultiSelectActive
        ? props.category.item.slice(0, props.maxShowTagsCount)
        : [],
    }));
    this.props.onActiveStateChange(this.props.category.type);
  }

  // 切换标签选中状态
  toggleMultiSelectTagState = (tag) => {
    this.setState(prevState => {
      let { multiSelectModeTags } = prevState;
      multiSelectModeTags = _.cloneDeep(multiSelectModeTags);

      // 切换分类下标签的选中状态
      const index = multiSelectModeTags.findIndex(t => t.type === tag.type && t.name === tag.name)
      index >= 0 && (multiSelectModeTags[index].selected = !tag.selected);

      return {multiSelectModeTags};
    });
  }

  // 多选模式确认处理
  handleMultiSelectConfirm = () => {
    const { category, maxShowTagsCount } = this.props;
    const { multiSelectModeTags } = this.state;
    const prevTags = category.item.slice(0, maxShowTagsCount);
    
    // 选中状态发生改变的标签
    const changedTags = prevTags.filter((tag, index) =>
      tag.selected !== multiSelectModeTags[index].selected
    );

    // 关闭多选模式并触发选中标签改变事件
    this.toggleMultiSelectState();
    !_.isEmpty(changedTags) && this.props.onSelectedTagsChange(changedTags);
  }

  // 标签改变事件处理
  handleTagChange = (tag) => {
    const { category, selectedTags, onSelectedTagsChange } = this.props;

    // 标签为自定义日期范围选择控件时不做处理
    if (tag.id === 'DateRangePicker') {
      return;
    }

    // 调用选中标签发生改变事件回调
    onSelectedTagsChange(category.multiValued
      // 支持多选
      ? [tag]

      // 不支持多选是将上一次选中的标签回传以取消选中
      : _.uniqBy(selectedTags.concat(tag), 'name')
    );
  }

  // 日期范围选择控件值改变事件处理
  handleDateRangePickerChange = (tag, dateString) => {
    const { category, selectedTags, onSelectedTagsChange } = this.props;

    // 设置自定义日期范围选择控件标签的起始时间与结束时间
    tag.min = dateString[0];
    tag.max = dateString[1];

    // 调用选中标签发生改变事件回调
    onSelectedTagsChange(category.multiValued
      // 支持多选
      ? [tag]

      // 不支持多选是将上一次选中的标签回传以取消选中
      : selectedTags.concat(tag)
    );
  }

  render () {
    const {
      category,
      selectedTags,
      showCollapse,
      maxShowTagsCount,
      isDefaultCollapse,
    } = this.props;
    const {
      isExpand,
      isMultiSelectActive,
      multiSelectModeTags,
    } = this.state;
    const { item, multiValued } = category;

    // 根据是否为多选模式显示不同的标签数据
    const tags = isMultiSelectActive
      ? multiSelectModeTags
      : item.slice(0, maxShowTagsCount);

    // 是否为关键字分类
    const isKeywordsType = category.type === 'keywords';
    
    // 根据展开与多选模式状态添加不同的类名
    const className = classNames(`${blockClass}__category-item`, {
      'is-expand': isExpand || isKeywordsType,
      'is-default-collapse': isDefaultCollapse,
      'is-multi-select-active': isMultiSelectActive,
    });

    return (
      <li className={className}>
        <div className={`${blockClass}__category-title`}>
          {category.showname}
        </div>
        <div className={`${blockClass}__category-tags`}>
          {multiValued && item.length > maxShowTagsCount &&
            <TagSearchBox
              tags={item}
              placeholder={`请输入${category.showname}名称`}
              selectedTags={selectedTags}
              maxShowTagsCount={maxShowTagsCount}
              onChange={(tags) => this.props.onSelectedTagsChange(tags)}
            />
          }

          {/* 关键词分类 */}
          {isKeywordsType &&
            <KeywordsFilter
              category={category}
              onChange={(tags) => this.props.onSelectedTagsChange(tags)}
            />
          }

          {/* 标签列表项 */}
          <div className={`${blockClass}__category-tags-wrapper`}>
            {tags.map(tag =>
              parseFloat(tag.count) !== 0 &&
              <CheckableTag
                key={`${tag.type}-${tag.name}`}
                checked={tag.selected}
                onChange={() => isMultiSelectActive
                  ? this.toggleMultiSelectTagState(tag)
                  : this.handleTagChange(tag)
                }
              >
                {tag.name}
              {!_.isUndefined(tag.count) &&
                <span className={`${blockClass}__tag-total`}>{tag.count}</span>
              }

              {/* 自定义日期范围选择控件标签 */}
              {tag.id === 'DateRangePicker' &&
                <RangePicker
                  className={`${blockClass}__date-range-picker`}
                  dropdownClassName={`${blockClass}__date-range-picker-dropdown`}
                  allowClear={false}
                  value={[moment(tag.min), moment(tag.max)]}
                  onChange={(date, dateString) => this.handleDateRangePickerChange(tag, dateString)}
                />
              }
              </CheckableTag>
            )}
          </div>

          {/* 多选模式下才显示的确认与取消按钮 */}
          <div className={`${blockClass}__category-multi-select-actions`}>
            <Button
              type="primary"
              size="small"
              onClick={this.handleMultiSelectConfirm}
            >
              确认
            </Button>
            <Button
              size="small"
              onClick={this.toggleMultiSelectState}
            >
              取消
            </Button>
          </div>
        </div>

        {/* 分类操作按钮 */}
        <div className={`${blockClass}__category-actions`}>
          {showCollapse && !isKeywordsType &&
            <Button
              size="small"
              className={`${blockClass}__category-toggle-collapse`}
              onClick={this.toggleExpandState}
            >
              更多<Icon type="down" />
            </Button>
          }

          {multiValued && tags.length > 1 &&
            <Button
              size="small"
              className={`${blockClass}__category-toggle-multi-select`}
              onClick={this.toggleMultiSelectState}
            >
              <Icon type="plus" />多选
            </Button>
          }
        </div>
      </li>
    );
  }
}

// 标签多更多搜索框组件
class TagSearchBox extends Component {
  constructor (props) {
    super(props);

    // 相关状态
    this.state = {
      // 是否禁用确认按钮
      disableConfirmButton: true,

      // 已选中的标签列表
      selectedTags: _.cloneDeep(props.selectedTags),

      // 搜索框要展示的标签列表
      tagList: _.cloneDeep(props.tags.slice(0, props.maxShowTagsCount)),
    }

    // 建立标签数据拼音搜索引擎索引
    this.pinyinEngine = new PinyinEngine(
      props.tags.map(tag => ({...tag, queryText: tag.name.toLowerCase()})),
      ['queryText'],
    );
  }

  componentDidUpdate (prevProps) {
    const { tags, selectedTags } = this.props;

    // 如果当前选中标签发生变化，重置搜索框中的标签为更新后的选中标签
    if (!_.isEqual(prevProps.selectedTags, selectedTags)) {
      this.setState({
        disableConfirmButton: true,
        selectedTags: _.cloneDeep(selectedTags),
      });
    }

    // 如果标签列表数据发生变化，重新建立标签数据拼音搜索引擎索引
    if (prevProps.tags !== tags) {
      this.pinyinEngine = new PinyinEngine(
        tags.map(tag => ({...tag, queryText: tag.name.toLowerCase()})),
        ['queryText'],
      );
    }
  }

  // 每次聚焦时展示默认标签列表
  handleFocus = () => {
    const { tags, maxShowTagsCount } = this.props;
    this.setState({
      tagList: _.cloneDeep(tags.slice(0, maxShowTagsCount)),
    });
  }

  // 搜索标签处理（节流搜索）
  handleSearchTag = _.throttle((value) => {
    const { tags, maxShowTagsCount } = this.props;
    value = value.trim().toLowerCase();

    // 获取包含搜索关键字的标签列表
    const tagList = value === '' ? tags : this.pinyinEngine.query(value);

    this.setState({
      tagList: _.cloneDeep(tagList.slice(0, maxShowTagsCount)),
    });
  }, 200);

  // 选中的标签发生改变的处理
  handleSelectedTagChange = (value, options) => {
    const prevSelectedTags = this.props.selectedTags;
    const selectedTags = options.map(option => option.props.tag);

    // 移除的标签
    const removeTags = _.differenceBy(prevSelectedTags, selectedTags, 'name');

    // 新增的标签
    const addTags = _.differenceBy(selectedTags, prevSelectedTags, 'name');

    // 合并发生变化的标签
    const changedTags = removeTags.concat(addTags);

    this.setState({
      disableConfirmButton: _.isEmpty(changedTags),
      selectedTags,
    });
  }

  // 确认选择处理
  handleConfirm = () => {
    const { selectedTags: prevSelectedTags, onChange } = this.props;
    const { selectedTags } = this.state;

    // 移除的标签
    const removeTags = _.differenceBy(prevSelectedTags, selectedTags, 'name');

    // 新增的标签
    const addTags = _.differenceBy(selectedTags, prevSelectedTags, 'name');

    // 合并发生变化的标签
    const changedTags = removeTags.concat(addTags);

    // 触发标签
    !_.isEmpty(changedTags) && onChange(changedTags);
  }

  render () {
    const { placeholder } = this.props;
    const { tagList, selectedTags, disableConfirmButton } = this.state;
    const selectedValue = selectedTags.map(tag => tag.name);

    return (
      <div className={`${blockClass}__category-search-wrapper`}>
        <Icon type="search" />
        <Select
          className={`${blockClass}__category-search`}
          dropdownClassName={`${blockClass}__category-search-dropdown`}
          mode="multiple"
          placeholder={placeholder}
          value={selectedValue}
          filterOption={false}
          optionLabelProp="value"
          optionFilterProp="value"
          onFocus={this.handleFocus}
          onSearch={this.handleSearchTag}
          onChange={this.handleSelectedTagChange}
          getPopupContainer={triggerNode => triggerNode.parentNode}
        >
          {tagList.map(tag =>
            <Option
              key={tag.name}
              value={tag.name}
              tag={tag}
            >
              <span className={`${blockClass}__option-text`} title={tag.name}>
                {tag.name}
              </span>
              <span className={`${blockClass}__tag-total`} title={tag.count}>
                {tag.count}
              </span>
            </Option>
          )}
        </Select>
        <Button
          disabled={disableConfirmButton}
          onClick={this.handleConfirm}
        >
          确认
        </Button>
      </div>
    );
  }
}

// 关键字筛选组件
class KeywordsFilter extends Component {
  // 相关状态
  state = {
    scope: this.props.category.scopeOptions[0],
    keywords: '',
  }

  // 范围选择框值发生变化处理
  handleScopeChange = (value, option) => {
    const { category: { scopeOptions } } = this.props;

    this.setState({
      scope: scopeOptions[option.props.index]
    });
  }

  // 输入框文本发生变化处理
  handleInputChange = (e) => {
    this.setState({
      keywords: e.target.value
    });
  }

  // 输入框失焦处理
  handleInputBlur = (e) => {
    this.setState(prevState => ({
      keywords: prevState.keywords.trim()
    }));
  }

  // 添加按钮点击处理
  handleAddButtonClick = (e) => {
    const { category, onChange } = this.props;
    let { scope, keywords } = this.state;

    // 使用空格（非引号内）分隔多个关键词，引号内的关键词将作为独立关键词，并移除重复的关键词
    keywords = _.uniq(keywords.trim().match(/[^\s"]+|"([^"]*)"/g));

    // 关键词范围前缀文本，默认范围时使用关键词分类的显示名称
    const label = scope.value === 'all' ? category.showname : scope.text;

    // 将关键词映射为标签对象数组
    const tags = keywords.map(keyword => ({
      // 生成标签名，并移除引号关键词的首尾引号字符
      name: `${label}：${keyword.replace(/^"|"$/g, '')}`,
      type: category.type,
      selected: false,
    }));

    !_.isEmpty(tags) && onChange(tags);
  }

  render () {
    const { category: { scopeOptions } } = this.props;
    const { scope, keywords } = this.state;

    return (
      <div className={`${blockClass}__keywords-filter`}>
        <InputGroup compact>
          <Select
            value={scope.value}
            onChange={this.handleScopeChange}
          >
            {scopeOptions.map((item, index) =>
              <Option
                key={item.value}
                value={item.value}
                index={index}
              >
                {item.text}
              </Option>
            )}
          </Select>
          <Input
            placeholder="匹配全部关键词"
            value={keywords}
            onChange={this.handleInputChange}
            onBlur={this.handleInputBlur}
            onPressEnter={this.handleAddButtonClick}
          />
          <Button
            className={`${blockClass}__button`}
            disabled={!keywords.trim()}
            onClick={this.handleAddButtonClick}
          >
            添加
          </Button>
          <span className={`${blockClass}__keywords-filter-tips`}>
            *多个关键词请用空格分隔
          </span>
        </InputGroup>
      </div>
    );
  }
}
