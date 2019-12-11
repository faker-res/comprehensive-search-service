/**
 * 图表配置
 * 
 * @author lzhang
 * @date 2018-6-22
 */
export default {
    // 竞争力分析
    chart: {
        type: 'scatter',
        spacingTop: 25,
        spacingLeft: 0,
        spacingBottom: 0,
    },
    title: {
        text: null
    },
    subtitle: {
        text: null
    },
    xAxis: {
        lineColor: "#eeeeee",
        lineWidth: 1,
        gridLineWidth: 0,
        tickColor: "#eeeeee",
        tickWidth: 1,
        crosshair: {
            dashStyle: "ShortDot"
        },
        title: {
            text: '收益率',
            align: "low",
            style: {
                color: "#999999"
            },
            x: -5,
            y: 10
        },
        labels: {
            style: {
                color: "#999999"
            },
            format: "{value:.2f}%"
        },
        startOnTick: true,
        endOnTick: true,
        showFirstLabel: true,
        showLastLabel: true,
    },
    yAxis: {
        lineColor: "#eeeeee",
        lineWidth: 1,
        gridLineWidth: 0,
        tickColor: "#eeeeee",
        tickWidth: 1,
        crosshair: {
            dashStyle: "ShortDot"
        },
        offset: 0,
        title: {
            align: "high",
            text: '推荐次数',
            rotation: 0,
            style: {
                color: "#999999"
            },
            y: -15,
            x: 50
        },
        labels: {
            style: {
                color: "#999999"
            }
        },
        showLastLabel: true,
        showFirstLabel: false,
        min: 0
    },
    legend: {
        align: "right",
        itemMarginTop: -20
    },
    tooltip: {
        split: false,
        shared: false,
        useHTML: true,
        backgroundColor: 'rgba(0,0,0,0.6)',
        borderWidth: 0,
        shadow: false,
        style: {
            color: '#fff',
            lineHeight: '18px'
        },
        headerFormat: null,
        pointFormat: '<b>{point.name} ({point.code})</b><br>收益率: {point.x:.2f}%<br/>推荐: {point.y} 次'
    },
    series: [{
        name: '胡又军',
        color: 'rgba(246, 160, 158, 0.8)',
        marker: {
            symbol: 'circle',
            radius: 9
        },
        data: [[-120.2, 51.6], [107.5, 59.0], [129.5, 49.2]]
    }, {
        name: '其他分析师',
        color: 'rgba(185, 216, 255, 0.7)',
        marker: {
            symbol: 'circle',
            radius: 9
        },
        data: [[-20.2, 51.6], [137.5, 59.0], [120.5, 49.2]]
    }]
}