import React, { Component } from 'react';

class ChartDetail extends Component {
    constructor(props){
        super(props)
    }
    formatTime=(time)=>{
        var date = new Date(time)
      let year = date.getFullYear(); 
      let month =date.getMonth()+1;
       month =month>9?month:('0'+month.toString)
      let day  =date.getDate()
        return (year+'/'+month+'/'+day)
    }
    render() {
        let {type,DataList} = this.props
        let time =this.formatTime(DataList.index_time)
        return (
            <div>
                <h2>{DataList.image_title}</h2>
                <h5>{time||''}</h5>
                <div>
                    {type}
                </div>
            </div>
        );
    }
}

export default ChartDetail;