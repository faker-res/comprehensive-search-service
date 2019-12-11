import { observable,action } from 'mobx';
import moment from 'moment';

class SearchStore {
  constructor (rootStore) {
    this.rootStore = rootStore;
  }
  @observable filter = {
    involves:[],
    page_num: 1,
    prior: 'score',
    page_size: 10,
    // order:'desc',
    end_time: Number(moment().format('X')) * 1000,
    search_words:{}
  }
  
  @observable search = {
    end_time: Number(moment().format('X')) * 1000,
    search_words: {},
    involves:[],
    page_num: 1,
    page_size: 10,
    prior: 'score',
    // order:'desc'
  }

  @observable isSearched = false
  // 需要传给搜索组件 供搜索组件显示搜索条件
  @observable searchValueFromUrl = '';
  @action changeSearchValueFromUrl = (value) => {
    this.searchValueFromUrl = value;
  }
}

export default SearchStore;
