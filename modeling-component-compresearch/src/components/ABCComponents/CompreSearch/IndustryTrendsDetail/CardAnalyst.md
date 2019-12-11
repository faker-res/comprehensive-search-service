#### 适用范围

适用于综合搜索`卡片分析师`

> [设计稿地址](https://lanhuapp.com/web/#/item/board/detail?pid=6e513101-942f-474d-a617-349f4051f752&project_id=6e513101-942f-474d-a617-349f4051f752&image_id=d3952f7c-6b73-4802-98cc-d0ebfd15d78c)

#### 组件路径

`/components/CardAnalyst/`

#### 使用说明

```
import CardAnalyst from '/components/CardAnalyst/'

let originData = { //根据后台接口返回的原始数据样本
    "dynamic": [
        {
            "title": "\u7814\u62a5\u6807\u9898",
            "source": "\u7814\u62a5|\u5fae\u4fe1|\u5fae\u535a|\u516c\u4f17\u53f7",
            "time": "1524326400",
            "category": "\u5b8f\u89c2\u7ecf\u6d4e",
            "id": "\u52a8\u6001ID"
        },
        {
            "title": "\u7814\u62a5\u6807\u9898",
            "source": "\u7814\u62a5|\u5fae\u4fe1|\u5fae\u535a|\u516c\u4f17\u53f7",
            "time": "1524326400",
            "category": "\u5b8f\u89c2\u7ecf\u6d4e",
            "id": "\u52a8\u6001ID"
        }
    ],
    "report_stocks": [
        {
            "zdf": "0.000754716981132",
            "total": "5",
            "abc_code": "000001.SZ",
            "current_price": "13.26",
            "sec_name": "\u5e73\u5b89\u94f6\u884c",
            "last_time": "1524326400"
        },
        {
            "zdf": "0.000754716981132",
            "total": "5",
            "abc_code": "000001.SZ",
            "current_price": "13.26",
            "sec_name": "\u5e73\u5b89\u94f6\u884c",
            "last_time": "1524326400"
        },
        {
            "zdf": "0.000754716981132",
            "total": "5",
            "abc_code": "000001.SZ",
            "current_price": "13.26",
            "sec_name": "\u5e73\u5b89\u94f6\u884c",
            "last_time": "1524326400"
        }
    ],
    "basic_info": {
        "name": "\u5b89\u9e4f",
        "id": "59931989",
        "direction": "\u7164\u70ad\u5f00\u91c7、\u91c7\u6398、\u73af\u4fdd",
        "email": "",
        "time": "2017",
        "organ": "\u5e7f\u53d1\u8bc1\u5238",
        "rank": "1",
        "report_num": "395",
        "report_num_rate": "0.80",
        "organ_logo": "logo/ca8858ff525e4bdf9810a64c8547fb43.png",
        "tel": "",
        "image": "http://abc-crawler.oss-cn-hangzhou.aliyuncs.com/sac.net_845f7ba0-ef21-35b7-b71b-7c73b1cd453b.jpg",
        "honor_num": "3"
    }
}


...
render() {
    return (
        <CardAnalyst {...originData} />
    )
}
...
```

#### 参数

|参数|说明|类型|默认值|是否必须|
|:---:|:---:|:---:|:---:|:---:|
|basic_info|基本信息|object||true|
|report_stocks|股票列表|array||true|
|dynamic|最新动态列表|array||true|

##### `basic_info`中各项参数（按当前接口字段对应）说明

|参数|说明|类型|默认值|是否必须|
|:---:|:---:|:---:|:---:|:---:|
|image|头像图片|string||true|
|name|名称|string||true|
|direction|行业|string||true|
|organ_logo|机构logo|string||true|
|report_num|文章篇数|string `|` number||true|
|rate|排名百分比|string `|` number||true|
|rank|名次|string `|` number||true|
|time|年份|string `|` number||true|
|honor_num|荣誉次数|string||true|











