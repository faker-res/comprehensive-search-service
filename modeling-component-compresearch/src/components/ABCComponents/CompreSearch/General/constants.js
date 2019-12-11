/**
 * @description 项目相关常量与配合模块
 * @author jhqu
 * date: 2018-05-28
 */

// 站点顶级域名
export const siteDomain = 'modeling.ai';

const site_env_map = {
  dev : {
    newsOrigin : `http://tour-dev.${siteDomain}`,
  },
  pre : {
    newsOrigin : `https://tour-pre.${siteDomain}`,
  },
  prod : {
    newsOrigin : `https://abcfund.${siteDomain}`,
  }
};

// 子站点-资讯地址
export const newsOrigin = site_env_map['pre'].newsOrigin;

// 老站点起始地址
export const legacySiteOrigin = `https://v1.${siteDomain}`;

// 研报秀子站地址
export const reportShowOrigin = `https://show.${siteDomain}`;

// 公告详情页地址
export const origin_notice = `https://filing.${siteDomain}`;

// 研报详情页地址
export const origin_report = `https://report.${siteDomain}`;