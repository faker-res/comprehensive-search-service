/**
 * @description 项目中常量定义
 * @author kygeng
 * date: 2018-09-06
 */

 // 研究员
export const ROLE_RESEARCHER = 'researcher';
// 基金经理
export const ROLE_FUNDMANAGER = 'fundmanager';
// 投研总监
export const ROLE_DIRECTOR = 'director';
// 风控人员
export const ROLE_RISKOFFICER = 'riskofficer';

// 任务管理类型
// 股票池调仓任务
export const TASK_TYPE_STOCK = 'stock_swap';
// 研报审批任务
export const TASK_TYPE_REPORT = 'internal_report';

// 任务状态
// 待审核
export const TASK_STATUS_WAIT_CONFIRM = 1;
// 总监通过
export const TASK_STATUS_DIRECTOR_PASS = 2;
// 总监驳回
export const TASK_STATUS_DIRECTOR_REJECT = 3;
// 待稽核
export const TASK_STATUS_WAIT_CHECK = 4;
// 风控驳回
export const TASK_STATUS_RISK_REJECT = 6;
// 风控通过
export const TASK_STATUS_RISK_PASS = 5;

// 站点顶级域名
// export const siteDomain = 'modeling.ai';
// 权限配置，默认配置采用最小化设置
export const RIGHT_RULES = [
    {
        id: 'home', // 业务页面（组件）id
        name: '首页', // 业务页面（组件）名称
        path: 'home', // 业务页面（组件）路径
        type: 'nav', // 业务页面（组件）类型：nav（一级导航）、sub_nav（二级导航）等
        view_access: true, // 业务页面（组件）读权限
        operate_access: true // 业务页面（组件）操作权限
    },
    {
        id: 'report',
        name: '研究报告',
        path: 'report',
        type: 'nav',
        view_access: true,
        operate_access: true,
        children: [
            {
                id: 'report_secret',
                name: '内部研报',
                path: 'secret',
                type: 'sub_nav',
                view_access: true,
                operate_access: true,
            },
            {
                id: 'report_manage',
                name: '研报管理',
                path: 'manage',
                type: 'sub_nav',
                view_access: true,
                operate_access: true,
            }
        ]
    },
    {
        id: 'research',
        name: '股票研究',
        path: 'research',
        type: 'nav',
        view_access: true,
        operate_access: true,
        children: [
            {
                id: 'research_mystock',
                name: '自选',
                path: 'mystock',
                type: 'sub_nav',
                view_access: true,
                operate_access: true,
            },
            {
                id: 'research_simulation_group',
                name: '模拟组合',
                path: 'simulation-group',
                type: 'sub_nav',
                view_access: true,
                operate_access: true,
            },
            {
                id: 'research_stock_pool',
                name: '股票池',
                path: 'stock-pool',
                type: 'sub_nav',
                view_access: true,
                operate_access: true,
            },
            {
                id: 'research_product_model',
                name: '产业链模型',
                path: 'product-model',
                type: 'sub_nav',
                view_access: true,
                operate_access: true,
            }
        ]
    },
    {
        id: 'viewpoint',
        name: '卖方观点',
        path: 'viewpoint/report-research',
        type: 'nav',
        view_access: true,
        operate_access: true
    },
    {
        id: 'activity',
        name: '活动日历',
        path: 'activity',
        type: 'nav',
        view_access: true,
        operate_access: true
    },
    {
        id: 'task',
        name: '任务管理',
        path: 'task',
        type: 'nav',
        view_access: true,
        operate_access: true,
        children: [
            {
                id: 'task_task_index',
                name: '首页',
                path: 'task-index',
                type: 'sub_nav',
                view_access: false,
                operate_access: false,
            },
            {
                // 投研总监有此权限
                id: 'task_stock_position_apply_list',
                name: '股票池调仓审批',
                path: 'stock-position-apply-list',
                type: 'sub_nav',
                view_access: false,
                operate_access: false,
            },
            {
                // 投研总监有此权限
                id: 'task_invest_report_apply_list',
                name: '内部投研报告审批',
                path: 'invest-report-apply-list',
                type: 'sub_nav',
                view_access: false,
                operate_access: false,
            },
            {
                // 风控人员有此权限
                id: 'task_risk_list',
                name: '风控稽查',
                path: 'risk-list',
                type: 'sub_nav',
                view_access: false,
                operate_access: false,
            }
        ]
    },
    {
        id: 'compre_search',
        name: '综合搜索',
        path: 'compre-search',
        type: 'compre',
        view_access: true,
        operate_access: true,
        children: [
            {
                id: 'compre_search_results',
                name: '综合搜索',
                path: 'compre-search',
                type: 'sub_compre',
                view_access: true,
                operate_access: true,
            },
            {
                id: 'compre_search_interna_report',
                name: '内部研报',
                path: 'internal-report',
                type: 'sub_compre',
                view_access: true,
                operate_access: true,
            },
            {
                id: 'compre_search_vender_report',
                name: '外部研报',
                path: 'vender-report',
                type: 'sub_compre',
                view_access: true,
                operate_access: true,
            },
            {
                id: 'compre_search_activity',
                name: '投研活动',
                path: 'activity',
                type: 'sub_compre',
                view_access: true,
                operate_access: true,
            },
            {
                id: 'compre_search_chart',
                name: '数据图',
                path: 'chart',
                type: 'sub_compre',
                view_access: true,
                operate_access: true,
            }
        ]
    },
    {
        id: 'report_detail',
        name: '研报详情',
        path: 'report-detail',
        type: 'detail',
        view_access: true,
        operate_access: true,
    }
]
//移动端检测
export const isMobile = () => {
    let isMobile = false;
    const mobileAgent = ["iphone", "ipod", "android", "mobile", "blackberry", "webos", "incognito", "webmate", "bada", "nokia", "lg", "ucweb", "skyfire"];
    const browser = navigator.userAgent.toLowerCase();
    for(var i = 0; i < mobileAgent.length; i++) {
        if(browser.indexOf(mobileAgent[i]) !== -1) {
        isMobile = true;
        break;
        }
    };
    return isMobile;
  }
  
  // 分享页
  export const Share = {
    isSharePage : !window.__ABC_SSO_IS_AUTHORIZED__,
    AppDownloadUrl : 'https://m.analyst.ai/portal/app-download',
    mobileLoginUrl : 'https://m.analyst.ai/portal/account',
    sso_link : `${window.sso_baseUrl}/sso-login.html`,
    get sso_link_back () {
      return `${this.sso_link}?back=${encodeURIComponent(window.location.href)}`;
    },
    get loginUrl () {
  
      return this.sso_link_back;
      // return isMobile() ? this.mobileLoginUrl : this.sso_link_back;
    }
  };

// 子站点-资讯地址
export const newsOrigin = `https://news.modeling.ai`;

// 公告详情页地址
export const origin_notice = `https://notice.modeling.ai`;

// 研报详情页地址
export const origin_report = `https://abcdfund.modeling.ai`;