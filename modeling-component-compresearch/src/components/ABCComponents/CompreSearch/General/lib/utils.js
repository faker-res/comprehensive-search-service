/**
 * @description 工具封装
 * @author kygeng
 * date: 2018-05-29
 */
import axios from 'axios';
import _ from 'lodash';
const utils = {
    //切页
    changePages(pageNumber) {
        let page = document.querySelector("[data-page-number='" + pageNumber + "']");
        if (page) {
            page.scrollIntoView();
        }
    },
    //下载文件
    downloadfile(url, name) {
        axios.get(url, {
            responseType: 'blob', //重要
            params: {_cache: Date.now()}
        }).then((response) => {
            const url = window.URL.createObjectURL(new Blob([response.data]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', `${name}.pdf`);
            document.body.appendChild(link);
            link.click();
        });
    },
    //打印
    print(url) {
    },
    // 获取公告文件类型
    getFileType(url, file_type) {
        if (!_.isEmpty(url)) {
            return /\.(pdf|PDF)$/.test(url) ? 'pdf'
                : /\.(doc|docx|DOC|DOCX)$/.test(url) ? 'doc'
                : /\.(xls|xlsx|XLS|XLSX)$/.test(url) ? 'xls'
                : /\.(htm|html|shtml|HTM|HTML|SHTML)$/.test(url) ? 'html' : 'unknown';
        }
        let _fileType = file_type ? file_type.toLocaleLowerCase() : '';
        return _fileType.indexOf('pdf') > -1 ? 'pdf'
            : _fileType.indexOf('doc') > -1 ? 'doc'
                : _fileType.indexOf('xls') > -1 ? 'xls'
                    : _fileType.indexOf('html') > -1 ? 'html' : 'unknown';
    },

    //去掉html标签
    delHtmlTag(str = "") {
        str = str || "";
      return str.replace(/<[^>]+>/g,"");
    }
}

export default utils;