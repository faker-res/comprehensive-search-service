import React, { Component } from 'react';
import './index.scss';
import ThemeCard from '../ThemeCard'
class Theme extends Component {
    constructor(props){
        super(props)
        this.state={
            themeIntro:{}
        }
    }
    componentDidMount = async () => {
        // const { code } = this.props.match.params;
        // const { name } = queryString.parse(this.props.location.search);
    }
    render() {
        let {themeBaseInfo} = this.props
        console.log('数据props',this.props.themeBaseInfo);
        return (
            <div>
                <ThemeCard data={this.props.themeBaseInfo} hideMore='false'/>
            </div>
        );
    }
}

export default Theme;