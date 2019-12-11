export const mock_data = {
	code: 200,
	message: "",
	data: {
		card: [
			{
				name: "平安银行",
				id: "000001.SZ",
				type: "stock",
				desc: "平安银行"
			},
			{
				name: "胡又文",
				id: "19905144",
				type: "invernal_analyst",
				desc: "安信证券"
			},
			{
				name: "胡又文",
				id: "19905144",
				type: "vender_analyst",
				desc: "中信证券"
			}
		],
		internal_report: {// 内部研报列表
			total: 999,// 搜索结果数量
			items: [
				{
        			summary: "",// 摘要
        			stockname: "平安银行",// 个股名称
        			stockcode: "000001.SZ",// 个股代码
        			rating: 0,// 评级,0:中性、1:买入、2:增持
        			title: "天风证券-【银行】<font color='red'>平安银行</font>：业绩弹性增强，市场利率大降显著利好-180817",
        			file_size: 111779,//文件大小，单位kb
        			url: "http://abc-crawler.oss-cn-hangzhou.aliyuncs.com/wechat/43d656a54d8b4bec857f277cdf958d5d.html",
        			file_type: "HTML",// 文件类型 PDF、HTML、DOC(X)、ELS(X)、PPT(X)、UNKNOWN
        			id: 16266036,
        			time: 1534461401000,
        			file_pages: 1,
        			source: "天风证券",// 来源
        			category: "公司点评", // 分类
        			industry: "银行", // 行业
        			analyst_honor: [ // 作者
          				{
            				id: "77755",
            				honor: [ // 作者获奖列表
              					"金牛奖"
            				],
            				name: "廖志明",
            				avatar: "https://abc-crawler.oss-cn-hangzhou.aliyuncs.com/9a0696ac-86d4-3a77-bc19-94ed5b6f1070.jpg"
          				}
        			]
				}
			]
		},
		vender_report: {// 外部研报列表
			total: 999,// 搜索结果数量
			items: [
				{
        			summary: "",// 摘要
        			stockname: "平安银行",// 个股名称
        			stockcode: "000001.SZ",// 个股代码
        			rating: 0,// 评级,0:中性、1:买入、2:增持
        			title: "天风证券-【银行】<font color='red'>平安银行</font>：业绩弹性增强，市场利率大降显著利好-180817",
        			file_size: 111779,//文件大小，单位kb
        			url: "http://abc-crawler.oss-cn-hangzhou.aliyuncs.com/wechat/43d656a54d8b4bec857f277cdf958d5d.html",
        			file_type: "HTML",// 文件类型 PDF、HTML、DOC(X)、ELS(X)、PPT(X)、UNKNOWN
        			id: 16266036,
        			time: 1534461401000,
        			file_pages: 1,
        			source: "天风证券",// 来源
        			category: "公司点评", // 分类
        			industry: "银行", // 行业
        			analyst_honor: [ // 作者
          				{
            				id: "77755",
            				honor: [ // 作者获奖列表
              					"金牛奖"
            				],
            				name: "廖志明",
            				avatar: "https://abc-crawler.oss-cn-hangzhou.aliyuncs.com/9a0696ac-86d4-3a77-bc19-94ed5b6f1070.jpg"
          				}
        			]
				}
			]
        },
		activity: {// 参考微信透视活动日历数据
			total: 999,// 搜索结果数量
			items: [
				{
          			"sponsor": "安信计算机",
          			"city": "",
          			"remind_ahead": "",
          			"title": "【安信计算机】深信服中报交流电话会议邀请",// 活动名称
          			"type": "other",
          			"content": "",
          			"allDay": false,
          			"city_spelling_inkfile": "",
          			"companies": [],
          			"speakers": [
            			"副总经理、董秘 蒋总"
          			],
          			"place": "",
          			"attend_password": "785567712",
          			"attend_way": "phone",
          			"group_name": "IT黄金时代—安信计算机",
          			"contract": "吕伟:18612109231",
         			"receive_time": 1534677132000,
          			"end_time": 0,
          			"city_spelling": "",
          			"start_time": 1534730400000,
          			"record_id": "5b79509a1a138f41ed59a025",
          			"reminded": false,
          			"event_id": "5b79509a1a138f41ed59a033",
          			"group_id": "5acd77f7184e2237cbc34a1f",
          			"conference_call": "95040-263-263;4000-228-263;+86-572-276026;",
          			"send_user": "wells",// 活动发布人
          			"category": "unknown"
        		}
			]
		},
		chart: {
			total: 999,// 搜索结果数量
			items: [
				{
					// 来源研报名称
        			"source_title": "中原证券-<font color='red'>平安银行</font>（<font color='red'>000001</font>）收入与净息差改善，资产质量包袱减轻-180816",
        			// 来源研报id
					"source_id": 16214789,
					// 来源研报地址
        			"source_url": "http://abc-crawler.oss-cn-hangzhou-internal.aliyuncs.com/3686d5b3de1847f0a04d1dc428c2b641.pdf",
        			"company": "<font color='red'>平安银行</font>", // 公司
        			"id": "ee17e115fb940e4845bc9fca98ba1673!16214789_2_3_1",
					// 数据图数据地址
        			"data_file": "http://abc-crawler.oss-cn-hangzhou.aliyuncs.com/charts/ea0c9ad3f5f3fadc9759b1469c25d2e8ea1b3ec8c991b27e9df750b8dbde8795/16214789_2_3.json",
        			"industry": "银行",// 行业
        			"image_url": "http://abc-crawler.oss-cn-hangzhou.aliyuncs.com/charts/ea0c9ad3f5f3fadc9759b1469c25d2e8ea1b3ec8c991b27e9df750b8dbde8795/2_3.png",
        			"author": "刘冉,王鸿行",// 作者列表
        			"confidence": 1, // 算法可信度阈值
        			"title": "2013-18年公司手续费及佣金净收入及增长率",
        			"pageIndex": "2", // 所在研报页码
        			"publish": "中原证券",//研报发布来源
        			"time": 1534461401000
      			}
			]
		}
	}
};