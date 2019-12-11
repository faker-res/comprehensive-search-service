import Ajax from './../lib/ajax'
import qs from "querystringify"
// import ask from '../lib/ask';
import Cookies from 'js-cookie';
import uuidv4 from 'uuid/v4';

// Cookies.set('userId', '80114336991872214');
// Cookies.set('token', '$2a$10$ZKJhxYCxsHVOBpoSRsk8BewJSoRD6lq');

const userId = Cookies.get('userId');
const token = Cookies.get('token');
// const request_id = "6e728e0d-be6b-40ef-b451-a63f1b8c63ce";
const request_id = uuidv4();

export default {
    /**
     * 获取组合列表
     */
    getSimulationList(queryType, type, name, orderBy, orderSort) {
        if (name == ""){
            if (type == "") {
                const url = `/invest-research-api/stock/getPortfolio?queryType=${queryType}&userId=${userId}&token=${token}&orderBy=${orderBy}&orderSort=${orderSort}`;
                return Ajax.get(url);
            } else {
                const url = `/invest-research-api/stock/getPortfolio?queryType=${queryType}&userId=${userId}&token=${token}&type=${type}&orderBy=${orderBy}&orderSort=${orderSort}`;
                return Ajax.get(url);
            }
        } else {
            if (type == "") {
                const url = `/invest-research-api/stock/getPortfolio?queryType=${queryType}&userId=${userId}&token=${token}&orderBy=${orderBy}&orderSort=${orderSort}&name=${name}`;
                return Ajax.get(url);
            } else {
                const url = `/invest-research-api/stock/getPortfolio?queryType=${queryType}&userId=${userId}&token=${token}&type=${type}&orderBy=${orderBy}&orderSort=${orderSort}&name=${name}`;
                return Ajax.get(url);
            }
        }

    },
    /**
     * 获取报告数量
     */
    getReportNum({
        scope
    }) {
        const url = `/invest-research-api/mock/reportNum?scope=${scope}`;
        return Ajax.get(url);
    },

    getSimulationGroup() {
        const url = `/invest-research-api/mock/stock/simulationGroup`;
        return Ajax.get(url);
    },

    getTasks(pageSize, currentPage = 0) {
        const url = `/invest-research-api/mock/tasks?pageSize=${pageSize}&currentPag=${currentPage}`;
        return Ajax.get(url);
    },

    getReportGroup(roleName) {
        const url = `/invest-research-api/report/taskList?role=${encodeURIComponent(roleName)}`;
        return Ajax.get(url);
    },

    getDetailGroup(id) {
        const url = `/invest-research-api/stock/portlioList?orderBy=income&id=${encodeURIComponent(id)}&orderSort=desc`;
        return Ajax.get(url);
    },

    getActivities({
        size,
        page
    }) {
        const url = `/invest-research-api/mock/calendarList?size=${size}&page=${page}`;
        return Ajax.get(url);
    },

    /**
     * 添加研报
     * @param data
     * @returns {AxiosPromise<any>}
     */
    addReport(data = {}) {
        const url = `/invest-research-api/report/addReport`;
        return Ajax.post(url, data);
    },

    /**
     * 编辑研报
     * @param data
     * @returns {AxiosPromise<any>}
     */
    editReport(data = {}) {
        const url = `/invest-research-api/report/editReport`;
        return Ajax.post(url, data);
    },

    /**
     * 查询作者
     * @param name
     * @returns {AxiosPromise<any>}
     */
    getWriters(name) {
        const url = `/invest-research-api/report/writers?name=${encodeURIComponent(name)}`;
        return Ajax.get(url);
    },

    /**
     * 查询关联股票
     * @param name
     * @returns {AxiosPromise<any>}
     */
    getRelateStock(name) {
        const url = `/api/mystock/suggest/security?groupId=0&keyword=${encodeURIComponent(name)}&marketCodes=1004001&groupType=-1&userId=${userId}&token=${token}`;
        return Ajax.get(url);
    },

    /**
     * 查询关联行业
     * @param name
     * @returns {AxiosPromise<any>}
     */
    getIndustry(name) {
        const url = `/invest-dc-inner-api/report/industry?name=${encodeURIComponent(name)}`;
        return Ajax.get(url);
    },
    /**
     * 获取研报信息
     * @param reportId
     * @returns {AxiosPromise<any>}
     */
    getReportData(reportId) {
        const url = `/invest-research-api/report/queryReport?reportID=${encodeURIComponent(reportId)}`;
        return Ajax.get(url);
    },

    /**
     * 获取研报列表
     * @returns {AxiosPromise<any>}
     */
    getResearchReport() {
        const url = `/invest-research-api/mock/researchReport`;
        return Ajax.get(url);
    },


    /**
     * 人员研报类型
     * @returns {AxiosPromise<any>}
     */
    getPeopleReportType() {
        const url = `/invest-research-api/mock/report/peopleReportType`;
        return Ajax.get(url);
    },

    /**
     * 设置状态
     * @param data
     * @returns {AxiosPromise<any>}
     */
    setPocFlowStatus(data) {
        const url = `/invest-research-api/report/setPocFlowStatus`;
        return Ajax.post(url, data);
    },

    /**
     * 设置股票调仓任务状态
     * @param data
     * @returns {AxiosPromise<any>}
     */
    setPoolStatus(data) {
        const url = `/invest-research-api/stock/setPoolStatus`;
        return Ajax.post(url, data);
    },

    /**
     * 删除数据
     * @param data
     * @returns {AxiosPromise<any>}
     */
    deleteReportGroup(data) {
        const url = `/invest-research-api/report/delReport`;
        return Ajax.post(url, data);
    },

    /**
     * 获取研报数量
     */
    getReportNumData() {
        const url = '/invest-research-api/report/reportNum'
        return Ajax.get(url);
    },

    /**
     * @param data
     * @returns {AxiosPromise<any>}
     * 获取研报数量
     */
    getReportTypeData(data) {
        console.log(userId, token)
        const url = `/invest-research-api/report/reportType?scope=${encodeURIComponent(data)}`
        return Ajax.get(url);
    },

    /**
     * 添加审核建议
     * @param approval
     * @returns {AxiosPromise<any>}
     */
    addInvestApproval(approval) {
        const url = `/invest-research-api/report/addApprovalMsg`
        return Ajax.post(url, approval);
    },

    /**
     * 添加股票调仓审核建议
     * @param approval
     * @returns {AxiosPromise<any>}
     */
    addInvestPoolApproval(approval) {
        const url = `/invest-research-api/stock/addApprove`
        return Ajax.post(url, approval);
    },


    /**
     * 读取审核建议
     * @param id
     * @returns {AxiosPromise<any>}
     */
    getApprovalMsg(id) {
        const url = `/invest-research-api/report/getApprovalMsg?id=${encodeURIComponent(id)}`
        return Ajax.get(url);
    },

    /**
     * 获取审核建议
     * @param id
     * @returns {AxiosPromise<any>}
     */
    getArrpovalRecord(id) {
        const url = `/invest-research-api/report/getArrpovalRecord?id=${encodeURIComponent(id)}`
        return Ajax.get(url);
    },
    /**
     * 添加组合
     */
    addStockgroup(formData) {
        const url = `/invest-research-api/stock/stockPortfolio`;
        return Ajax.post(url, formData);
    },
    /**
     * 获取组合收益率变化
     */
    getInComeChange() {
        const url = `/invest-research-api/stock/allTotalIncomeChange`;
        return Ajax.get(url);
    },
    /**
     * 获取组合收益率变化(详情)
     */
    getInComeChangedetail(id) {
        const url = `/invest-research-api/stock/singleTotalIncomeChange?id=${encodeURIComponent(id)}`;
        return Ajax.get(url);
    },
    /**
     * 获取组合排行
     */
    getInComeRank() {
        const url = `/invest-research-api/stock/portlioRank`;
        return Ajax.get(url);
    },
    // 移除單個組合
    getRemoveIt(id) {
        const url = `/invest-research-api/stock/removeStockProtfolio?id=${encodeURIComponent(id)}`;
        return Ajax.get(url);
    },

    /**
     * 获取组合详情
     */
    getPortlioSummary(id) {
        const url = `/invest-research-api/stock/portlioSummary?id=${encodeURIComponent(id)}`;
        return Ajax.get(url);
    },

    /**
     * 获取组合排行
     */
    getHoldInfo(id,code,name) {
        const url = `/invest-research-api/stock/holdInfo?id=${encodeURIComponent(id)}&stockCode=${code}&stockName=${name}`;
        return Ajax.get(url);
    },
    addStockToPortlio(data) {
        const url = `/invest-research-api/stock/adjustStockToPortlio`;
        return Ajax.post(url, data);
    },

    /**
     * 获取股票池-股票行业分布图数据
     */

    industryStatisticsMap(stockPoolFlag,poolName) {
        const url = `/invest-research-api/stock/getIndustryStatisticsMap?stockPoolFlag=${stockPoolFlag}&poolName=${encodeURIComponent(poolName)}&userId=${userId}&token=${token}`;
        return Ajax.get(url);
    },

    /**
     * 获取组合收益折线图数据
     */
    getKline(code, time) {
        const url = `/invest-dc-inner-api/kline?userId=${userId}&token=${token}&request_id=6042c020-87b4-45c5-82d4-1c0f9cb60eee&stock_code=${code}&graph_type=d&start_time=${time}`;
        return Ajax.get(url);
    },

    /**
     * 获取股票实时价格
     */
    getCard(name, code) {
        const url = `/invest-dc-inner-api/industry/stockRealTimePrice?userId=${userId}&token=${token}&request_id=d015fdd2-5ecc-4e98-8bbc-b589a2575ad7&stockName=${name}&stockCode=${code}`;
        return Ajax.get(url);
    },

    getApprovalList(role) {
        const url = `/invest-research-api/stock/stockpoolTaskList?role=${encodeURIComponent(role)}`;
        return Ajax.get(url);
    },
    /**
     * 获取原股票池名称
     */
    whichPoll(stockName) {
        const url = `/invest-research-api/stock/whichPool?name=${encodeURIComponent(stockName)}`;
        return Ajax.get(url);
    },
    adjustStockToPool(data) {
        const url = `/invest-research-api/stock/adjustStockToPool`;
        return Ajax.post(url, data);
    },

    /**
     * 获取股票池股票列表
     */
    getStockPollList(poolName,name,num,keyword) {
        if (keyword){
            const url = `/invest-research-api/stock/getStockPoolList?pageSize=12&stockPoolFlag=${name}&pageNum=${num}&name=${keyword}&poolName=${poolName}`;
            return Ajax.get(url);
        } else {
            const url = `/invest-research-api/stock/getStockPoolList?pageSize=12&stockPoolFlag=${name}&pageNum=${num}&poolName=${poolName}`;
            return Ajax.get(url);
        }
    },
    /**
     * 图表类服务接口
     */
    charts: {

        /**
         * 获取研究报告总量
         * @param params
         * @returns {AxiosPromise<any>}
         */
        getReportTotal(params) {
            // const url = `/invest-research-api/report/reportTotal?${qs.stringify(params)}`
            const url = `/invest-research-api/mock/report/reportNum?${qs.stringify(params)}`
            return Ajax.get(url)
        },
        /**
         * 部门报告数量统计
         * @param params
         * @returns {AxiosPromise<any>}
         */
        getReportNum(params) {
            // const url = `/invest-research-api/report/reportNum?${qs.stringify(params)}`
            const url = `/invest-research-api/mock/report/departReportNum?${qs.stringify(params)}`
            return Ajax.get(url)
        },

        /**
         * 部门报告类型统计
         * @param params
         * @returns {AxiosPromise<any>}
         */
        getReportType(params) {
            const url = `/invest-research-api/mock/report/reportType?${qs.stringify(params)}`
            return Ajax.get(url)
        }
    },
    /**
     * 获取股票池总体概况
     * @returns {AxiosPromise<any>}
     */
    getPoolOverview() {
        const url = `/invest-research-api/stock/poolOverview`;
        return Ajax.get(url);
    },

    /**
     * 获取某只股票所在股票池
     * @param name  股票名称 type:String
     * @returns {AxiosPromise<any>}
     */
    getWhichPool(name) {
        const url = `/invest-research-api/stock/whichPool?${encodeURIComponent(name)}`;
        return Ajax.get(url);
    },
       /**
     * 获取首页股票池动态
     * @param name  
     * @returns {AxiosPromise<any>}
     */
    gethomestockpool() {
        const url = `/invest-research-api/stock/stockPoolDynamic`;
        return Ajax.get(url);
    },
    
       /**
     * 获取券商评分任务列表
     * @param name  
     * @returns {AxiosPromise<any>}
     */
    getTasksList() {
        const url = `/invest-trader-mark/task/list`;
        return Ajax.get(url);
    },

    /**
  * 获取券商评分详情列表数据
  * @param name  
  * @returns {AxiosPromise<any>}
  */
    getTaskScoreList(taskId) {
        // console.log(taskId)
        const url = `/invest-trader-mark/mark/list?task_id=${taskId}`;
        return Ajax.get(url);
    },

    
    /**
  * 获取券商评分详情模板下载
  * @param name  
  * @returns {AxiosPromise<any>}
  */
    downloadfile() {
        const url = `/invest-trader-mark/mark/template`;
        return Ajax.get(url);
    },
    
    /**
  * 获取券商评分列表查询季度任务名称
  * @param name  
  * @returns {AxiosPromise<any>}
  */
    getQuarter(taskId) {
        const url = `/invest-trader-mark/mark/quarter?task_id=${taskId}`;
        return Ajax.get(url);
    },

    
    /**
  * 获取券商评分统计信息
  * @param name  
  * @returns {AxiosPromise<any>}
  */
    getScoreResult(stockerName,industry,taskId) {
        const url = `/invest-trader-mark/evaluate/showEvaluate?brokerName=${stockerName}&taskId=${taskId}&industry=${industry}`;
        return Ajax.get(url);
    },

    /**
     * 获取券商评分统计曲线图
     * @param name  
     * @returns {AxiosPromise<any>}
     */
    getyyl(brokerName) {
        const url = `/invest-trader-mark/evaluate/trend?brokerName=${brokerName}`;
        return Ajax.get(url);
    },

    /**
     * 获取券商评分统计列表
     * @param name  
     * @returns {AxiosPromise<any>}
     */
    getStatisticsList() {
        const url = `/invest-trader-mark/count/query`;
        return Ajax.post(url);
    },

    /**
     * 获取券商评分统计列表详情
     * @param name  
     * @returns {AxiosPromise<any>}
     */
    getStatisticsListDetail(analystName,brokerName,industryName,taskId) {
        const url = `/invest-trader-mark/count/queryInfo?analystName=${analystName}&brokerName=${brokerName}&industryName=${industryName}&taskId=${taskId}`;
        return Ajax.post(url);
    },

    /**
     * 投研总监首页券商排名
     * @param name  
     * @returns {AxiosPromise<any>}
     */
    getBrokerRanking(){
        const url = `/invest-trader-mark/count/queryBrokerRankingList`;
        return Ajax.get(url);
    },

     /**
     * 投研总监首页研报类型统计
     * @param name  
     * @returns {AxiosPromise<any>}
     */
    getReportcount(){
        const url = `/invest-research-api/report/report-manage/type/count`;
        return Ajax.get(url);
    },
   
}