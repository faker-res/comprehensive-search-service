/**
 * @description 研报搜索条件标签
 * @author kygeng
 * date: 2018-08-10
 */
import React, { Component } from 'react';
import styled from 'styled-components';
import { Tag } from 'antd';
import { withRouter } from 'react-router-dom';
import queryString from 'query-string';

const FilterTagsWrap = styled.div`
    display: inline-flex;
    justify-content: center;
    align-items: center;
    height: 100%;
`;

@withRouter
export default class FilterTags extends Component {
    constructor(props) {
        super(props);
    }

    handleClose = (type, value) => {
        let queryParams = queryString.parse(this.props.location.search);
        delete queryParams[type];
        this.props.history.push({
            pathname: this.props.location.pathname,
            search: `?${queryString.stringify(queryParams)}`
        });
    }

    render() {
        const queryParams = queryString.parse(this.props.location.search);
        const { industry, report, company } = queryParams;
        return (
            <FilterTagsWrap>
                <Tag hidden={!industry} closable onClose={(e) => this.handleClose('industry', industry)}>行业：{industry}</Tag>
                <Tag hidden={!report} closable onClose={(e) => this.handleClose('report', report)}>研报类型：{report}</Tag>
                <Tag hidden={!company} closable onClose={(e) => this.handleClose('company', company)}>公司：{company}</Tag>
            </FilterTagsWrap>
        )
    }
}