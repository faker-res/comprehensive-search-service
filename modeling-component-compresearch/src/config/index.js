import BaseApi from './baseApi';
import NewNotice from './newNotice'; 
import NewReport from './newReport'; 
import CompreSearchApi from './compresearch';
import ReportResultsApi from './reportresult';
import AnalystCard from './AnalystCard'
import NewsModuleAPI from './news'
import DataSearch from './dataSearch'
// import ResearchReportAPI from './researchReportAPI';
// import ReportDetailApi from './reportdetail';
// import report from './report'
// import EventsCalendarApi from './eventscalendar';
// import ReportListApi from './reportList';
// import myStockApi from './myStockApi'
// import cardAnalyst from './cardAnalyst'
// // import EmailSettingApi from './emailsetting'
// import StockPoolApi from './stockpool'
// import TaskApi from './task'
// import PerformanceAppraisal from './performanceAppraisal'
// import announcementDetail from './announcementDetail'
// import AddEmailApi from './addEmailApi';
// import GetEmailList from './getEmailList';
// import DeleteEmail from './deleteEmail';
// import UpdateConfig from './updateConfig'
// import weichat from './weichat'

export default Object.assign({},
    NewNotice,
    NewReport,
    BaseApi,
    DataSearch,
    NewsModuleAPI,
    ReportResultsApi,
    CompreSearchApi,
    AnalystCard,
    // ResearchReportAPI,
    // ReportDetailApi,
    // report,
    // EventsCalendarApi,
    // myStockApi,
    // ReportListApi,
    // cardAnalyst,
    // // EmailSettingApi,
    // StockPoolApi,
    // TaskApi,
    // PerformanceAppraisal,
    // announcementDetail,
    // AddEmailApi,
    // GetEmailList,
    // DeleteEmail,
    // UpdateConfig,
    // weichat,
);