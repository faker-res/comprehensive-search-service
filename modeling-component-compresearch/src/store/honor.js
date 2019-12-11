import { observable, action, runInAction } from 'mobx';
import ask from '../lib/ask';
import moment from 'moment';
import { cloneJSON, getArrayIndex } from '../lib/utils';

class HonorStore {
  @observable honorState = {
    honorDetail: {},
  }

  @action
  fetchHonorDetail = async (name, payload = {}) => {
    try {
      const response = await ask('getHonorDetail', { params: { peo_uni_code: payload.id } })
      runInAction(() => {
        self.honorState = {
          ...self.honorState,
          honorDetail: response.data,
        };
      })
    } catch (error) {

    }
  }
};
const self = new HonorStore();
export default self;