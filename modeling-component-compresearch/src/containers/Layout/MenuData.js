import React from 'react';
import { ROLE_RESEARCHER, ROLE_FUNDMANAGER, ROLE_DIRECTOR, ROLE_RISKOFFICER } from '../../lib/constants';
export default {
    'default': {
        home: {
            path: 'home',
            title: '首页'
        },
        report: {
            path: 'report',
            title: '研究报告',
            subMenu: [
                { path: 'secret', title: '内部研报' },
                // { path: 'stock', title: '个股首页' },
                // { path: 'analysts', title: '分析师' },
                // { path: 'statistics', title: '报告统计分析' },
                { path: 'manage', title: '研报管理' },
                // { path: 'upload', title: '上传研报' }
            ]
        },
        research: {
            path: 'research',
            title: '股票研究',
            subMenu: [
                { path: 'mystock', title: '自选' },
                { path: 'simulation-group', title: '模拟组合' },
                { path: 'stock-pool', title: '股票池' },
                { path: 'product-model', title: '产业链模型' }
            ]
        },
        viewpoint: {
            path: 'viewpoint/report-research',
            title: '卖方观点',
            // subMenu: [
                // { path: 'report-research', title: '研究报告' },
                // { path: 'report-statistics', title: '报告统计分析' },
                // { path: 'report-quality', title: '报告质量分析' },
            // ]
        },
        activity: {
            path: 'activity',
            title: '活动日历',
        },
        task: {
            path: 'task',
            title: '任务管理',
            // subMenu: [
                // { path: 'task-index', title: '首页' },
                // { path: 'stock-position-apply-list', title: '股票池调仓审批' },
                // { path: 'invest-report-apply-list', title: '内部投研报告审批' },
                // { path: 'stock-price-abnormal-comment-list', title: '股价异动点评' },
                // { path: 'company-comment-list', title: '股价异动点评' },
                // { path: 'risk-list', title: '风控稽查' },
            // ]
        },
        performance: {
            path: 'performance',
            title: '绩效考核',
        },
        // system: {
        //     path: 'system',
        //     title: '系统管理',
        //     subMenu: [
        //         { path: 'setting', title: '系统设置' },
        //         { path: 'user', title: '用户管理' },
        //         { path: 'role', title: '角色管理' },
        //         { path: 'wechat', title: '微信设置' },
        //         { path: 'email', title: '邮箱设置' },
        //     ]
        // },
    },
    'researcher': {
        home: {
            path: 'home',
            title: '首页'
        },
        report: {
            path: 'report',
            title: '研究报告',
            subMenu: [
                { path: 'secret', title: '内部研报' },
                // { path: 'stock', title: '个股首页' },
                // { path: 'analysts', title: '分析师' },
                // { path: 'statistics', title: '报告统计分析' },
                { path: 'manage', title: '研报管理' },
                // { path: 'upload', title: '上传研报' }
            ]
        },
        research: {
            path: 'research',
            title: '股票研究',
            subMenu: [
                { path: 'mystock', title: '自选' },
                { path: 'simulation-group', title: '模拟组合' },
                { path: 'stock-pool', title: '股票池' },
                { path: 'product-model', title: '产业链模型' }
            ]
        },
        viewpoint: {
            path: 'viewpoint/report-research',
            title: '卖方观点',
            // subMenu: [
                // { path: 'report-research', title: '研究报告' },
                // { path: 'report-statistics', title: '报告统计分析' },
                // { path: 'report-quality', title: '报告质量分析' },
            // ]
        },
        activity: {
            path: 'activity',
            title: '活动日历',
        },
        task: {
            path: 'task',
            title: '任务管理',
            // subMenu: [
                // { path: 'task-index', title: '首页' },
                // { path: 'stock-position-apply-list', title: '股票池调仓审批' },
                // { path: 'invest-report-apply-list', title: '内部投研报告审批' },
                // { path: 'stock-price-abnormal-comment-list', title: '股价异动点评' },
                // { path: 'company-comment-list', title: '公司异动点评' },
                // { path: 'risk-list', title: '风控稽查' },
            // ]
        },
        performance: {
            path: 'performance',
            title: '绩效考核',
        },
        // system: {
        //     path: 'system',
        //     title: '系统管理',
        //     subMenu: [
        //         { path: 'setting', title: '系统设置' },
        //         { path: 'user', title: '用户管理' },
        //         { path: 'role', title: '角色管理' },
        //         { path: 'wechat', title: '微信设置' },
        //         { path: 'email', title: '邮箱设置' },
        //     ]
        // },
    },
    'director': {
        home: {
            path: 'home',
            title: '首页'
        },
        report: {
            path: 'report',
            title: '研究报告',
            subMenu: [
                { path: 'secret', title: '内部研报' },
                // { path: 'stock', title: '个股首页' },
                // { path: 'analysts', title: '分析师' },
                { path: 'statistics', title: '报告统计分析' },
                { path: 'manage', title: '研报管理' },
                // { path: 'upload', title: '上传研报' }
            ]
        },
        research: {
            path: 'research',
            title: '股票研究',
            subMenu: [
                { path: 'mystock', title: '自选' },
                { path: 'simulation-group', title: '模拟组合' },
                { path: 'stock-pool', title: '股票池' },
            ]
        },
        viewpoint: {
            path: 'viewpoint/report-research',
            title: '卖方观点',
            // subMenu: [
                // { path: 'report-research', title: '研究报告' },
                // { path: 'report-statistics', title: '报告统计分析' },
                // { path: 'report-quality', title: '报告质量分析' },
            // ]
        },
        activity: {
            path: 'activity',
            title: '活动日历',
        },
        task: {
            path: 'task',
            title: '任务管理',
            subMenu: [
                { path: 'task-index', title: '首页' },
                { path: 'stock-position-apply-list', title: '股票池调仓审批' },
                { path: 'invest-report-apply-list', title: '内部投研报告审批' },
            ]
        },
        performance: {
            path: 'performance',
            title: '绩效考核',
        },
        // system: {
        //     path: 'system',
        //     title: '系统管理',
        //     subMenu: [
        //         { path: 'setting', title: '系统设置' },
        //         { path: 'user', title: '用户管理' },
        //         { path: 'role', title: '角色管理' },
        //         { path: 'wechat', title: '微信设置' },
        //         { path: 'email', title: '邮箱设置' },
        //     ]
        // },
    },
    'fundmanager': {
        home: {
            path: 'home',
            title: '首页'
        },
        report: {
            path: 'report',
            title: '研究报告',
            subMenu: [
                { path: 'secret', title: '内部研报' },
                // { path: 'stock', title: '个股首页' },
                // { path: 'analysts', title: '分析师' },
                // { path: 'statistics', title: '报告统计分析' },
                { path: 'manage', title: '研报管理' },
                // { path: 'upload', title: '上传研报' }
            ]
        },
        research: {
            path: 'research',
            title: '股票研究',
            subMenu: [
                { path: 'mystock', title: '自选' },
                { path: 'simulation-group', title: '模拟组合' },
                { path: 'stock-pool', title: '股票池' },
                { path: 'product-model', title: '产业链模型' }
            ]
        },
        viewpoint: {
            path: 'viewpoint/report-research',
            title: '卖方观点',
            // subMenu: [
                // { path: 'report-research', title: '研究报告' },
                // { path: 'report-statistics', title: '报告统计分析' },
                // { path: 'report-quality', title: '报告质量分析' },
            // ]
        },
        activity: {
            path: 'activity',
            title: '活动日历',
        },
        task: {
            path: 'task',
            title: '任务管理',
            // subMenu: [
                // { path: 'task-index', title: '首页' },
                // { path: 'stock-position-apply-list', title: '股票池调仓审批' },
                // { path: 'invest-report-apply-list', title: '内部投研报告审批' },
                // { path: 'stock-price-abnormal-comment-list', title: '股价异动点评' },
                // { path: 'company-comment-list', title: '股价异动点评' },
                // { path: 'risk-list', title: '风控稽查' },
            // ]
        },
        performance: {
            path: 'performance',
            title: '绩效考核',
        },
        // system: {
        //     path: 'system',
        //     title: '系统管理',
        //     subMenu: [
        //         { path: 'setting', title: '系统设置' },
        //         { path: 'user', title: '用户管理' },
        //         { path: 'role', title: '角色管理' },
        //         { path: 'wechat', title: '微信设置' },
        //         { path: 'email', title: '邮箱设置' },
        //     ]
        // },
    },
    'riskofficer': {
        home: {
            path: 'home',
            title: '首页'
        },
        report: {
            path: 'report',
            title: '研究报告',
            subMenu: [
                { path: 'secret', title: '内部研报' },
                // { path: 'stock', title: '个股首页' },
                // { path: 'analysts', title: '分析师' },
                // { path: 'statistics', title: '报告统计分析' },
                { path: 'manage', title: '研报管理' },
                // { path: 'upload', title: '上传研报' }
            ]
        },
        research: {
            path: 'research',
            title: '股票研究',
            subMenu: [
                { path: 'mystock', title: '自选' },
                { path: 'simulation-group', title: '模拟组合' },
                { path: 'stock-pool', title: '股票池' },
                { path: 'product-model', title: '产业链模型' }
            ]
        },
        viewpoint: {
            path: 'viewpoint/report-research',
            title: '卖方观点',
            // subMenu: [
                // { path: 'report-research', title: '研究报告' },
                // { path: 'report-statistics', title: '报告统计分析' },
                // { path: 'report-quality', title: '报告质量分析' },
            // ]
        },
        activity: {
            path: 'activity',
            title: '活动日历',
        },
        task: {
            path: 'task',
            title: '任务管理',
            subMenu: [
                { path: 'task-index', title: '首页' },
                // { path: 'stock-position-apply-list', title: '股票池调仓审批' },
                // { path: 'invest-report-apply-list', title: '内部投研报告审批' },
                // { path: 'stock-price-abnormal-comment-list', title: '股价异动点评' },
                // { path: 'company-comment-list', title: '股价异动点评' },
                { path: 'risk-list', title: '风控稽查' },
            ]
        },
        performance: {
            path: 'performance',
            title: '绩效考核',
        },
        // system: {
        //     path: 'system',
        //     title: '系统管理',
        //     subMenu: [
        //         { path: 'setting', title: '系统设置' },
        //         { path: 'user', title: '用户管理' },
        //         { path: 'role', title: '角色管理' },
        //         { path: 'wechat', title: '微信设置' },
        //         { path: 'email', title: '邮箱设置' },
        //     ]
        // },
    }
}