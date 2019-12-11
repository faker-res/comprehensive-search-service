import { observable, flow, action, runInAction, computed } from 'mobx';
import axios from 'axios';
import Cookies from 'js-cookie';
import { utils } from '../lib/until';
import uuidv4 from 'uuid/v4';
import moment from 'moment';
import ask from '../lib/ask';
import md5 from 'md5';
import isEmpty from 'lodash/isEmpty';
// import service from './../service/index';
import { ROLE_RESEARCHER, ROLE_FUNDMANAGER, ROLE_DIRECTOR, ROLE_RISKOFFICER } from '../lib/constants';

class AuthStore {
    constructor(rootStore) {
        this.rootStore = rootStore
    }

    @observable userInfo = {};
    @observable userState = 'none';
    @observable companyInfo = {};
    @observable companyState = 'pending';
 

    @computed get roleNameZP() {
        return this.userInfo.roleName === ROLE_RESEARCHER ? '研究员' :
            this.userInfo.roleName === ROLE_FUNDMANAGER ? '基金经理' :
            this.userInfo.roleName === ROLE_DIRECTOR ? '投研总监' :
            this.userInfo.roleName === ROLE_RISKOFFICER ? '风控人员' : '研究员';
    }

    @computed get roleName() {
        return this.userInfo.roleName || ROLE_RESEARCHER;
    }

    @action
    async getUserInfo() {
        this.userState = 'pending';
        this.userInfo = {};

        try {
            let userId = Cookies.get("userId") || "";
            let _code = md5(`userId=${userId}&time=${moment().format('YYYY-MM-DD HH:mm')}`);

            const { code, message, data } = await ask('getUserInfo', { params: { code: _code, userId }});

            if (code !== 200 || isEmpty(data)) {
                throw new Error(`Response Exception:${message},code:${code}`);
            }

            runInAction(() => {
                this.userInfo = Object.assign({}, data);
                this.userState = 'done';
            });
        } catch (error) {
            console.error(error);
            runInAction(() => {
                this.userState = 'error';
            });
        }
    }

    @action
    async getCompanyInfo() {
        this.companyState = 'pending';
        this.companyInfo = {};

        try {
            let domain = window.location.hostname;
            let _code = md5(`domain=${domain}&time=${moment().format('YYYY-MM-DD HH:mm')}`);

            const { code, message, data } = await ask('getCompanyInfo', { params: { code: _code, domain }});

            if (code !== 0 || isEmpty(data)) {
                throw new Error(`Response Exception:${message},code:${code}`);
            }

            runInAction(() => {
                this.companyInfo = Object.assign({}, data);
                this.companyState = 'done';
            })
        } catch (error) {
            console.error(error);
            runInAction(() => {
                this.companyState = 'error';
            });
        }
    }


}

export default AuthStore