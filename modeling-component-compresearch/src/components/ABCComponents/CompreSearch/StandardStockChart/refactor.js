export default {
    datasToSeries: function(datas, seriesMapping){
        let seriesArr = [];
        if (datas && seriesMapping){
            seriesMapping.forEach((item,index)=>{
                seriesArr.push(this.dataRowToSeries(
                    datas, item.title, item.chartType, item.xKey, item.yKey, item.color, item.enableMaker ));
            });
        }
        return seriesArr;
    },
    dataRowToSeries: (datas, title, chartType, xKey, yKey, color, enableMaker) => {
        let dataArr = [];
        datas.forEach((item,index)=>{
            if (item){
                let datai = {};
                datai.name = item[xKey];
                datai.y = item[yKey];
                if (color){
                    datai.color = color;
                }
                if (enableMaker || chartType === 'scatter'){
                    datai.marker = {
                        enabled: true
                    }
                }
                dataArr.push(datai);
            }
        });
        const retObj = {
            name: title,
            type: chartType,
            data: dataArr,
            marker: {enabled: false},
            turboThreshold: 0
        }
        return retObj;
    },
    baseConfig: {
        chart: {
            plotBorderWidth: 0,
            height: 300
        },
        title: {text: ''},
        credits: {
            enabled: false
        },
        className: 'highchart-default',
        colors: ['#1890FF', '#2FC25B', '#FACC14', '#223273', '#8543E0', '#13C2C2', '#3436C7', '#F04864'],
        plotOptions: {
            series: {
                dataLabels: {color: '#999'}
            }
        },
        legend: {
            enabled: true,
            itemStyle: {
                color: "#999", cursor: "pointer", fontSize: "12px", fontWeight: "bold" }
        },
        tooltip: {shared: true},
        xAxis: {
            tickLength: 0,
            type: 'category'
        },
        yAxis: {
            title: {
                text: null
            }
        }
    },
    deepAssign: function(target) {
        function isObj(x){
            let type = typeof x;
            return x !== null && (type === 'object' || type === 'function');
        }

        let hasOwnProperty = Object.prototype.hasOwnProperty;
        let propIsEnumerable = Object.prototype.propertyIsEnumerable;

        function toObject(val) {
            if (val === null || val === undefined) {
                throw new TypeError('Cannot convert undefined or null to object');
            }

            return Object(val);
        }

        function assignKey(to, from, key) {
            let val = from[key];

            if (val === undefined || val === null) {
                return;
            }

            if (hasOwnProperty.call(to, key)) {
                if (to[key] === undefined || to[key] === null) {
                    throw new TypeError('Cannot convert undefined or null to object (' + key + ')');
                }
            }

            if (!hasOwnProperty.call(to, key) || !isObj(val)) {
                to[key] = val;
            } else {
                to[key] = assign(Object(to[key]), from[key]);
            }
        }

        function assign(to, from) {
            if (to === from) {
                return to;
            }

            from = Object(from);

            for (let key in from) {
                if (hasOwnProperty.call(from, key)) {
                    assignKey(to, from, key);
                }
            }

            if (Object.getOwnPropertySymbols) {
                let symbols = Object.getOwnPropertySymbols(from);

                for (let i = 0; i < symbols.length; i++) {
                    if (propIsEnumerable.call(from, symbols[i])) {
                        assignKey(to, from, symbols[i]);
                    }
                }
            }

            return to;
        }
        target = toObject(target);
        for (let s = 1; s < arguments.length; s++) {
            assign(target, arguments[s]);
        }

        return target;
    }
}