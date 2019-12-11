import React, { Component } from 'react'
import './style.scss'

class AlphabetFilter extends Component {
  constructor(props) {
    super(props)
    this.state = {
      AlphabetList:['ALL', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'],
      oldIndex: 0,
    }
  }

  componentDidMount() {
  }
  
  clickAlphabetListItem = (idx) => { 
    this.setState({
      oldIndex:idx
    }, () => {
      this.props.getAlphabetListValue(this.state.AlphabetList[this.state.oldIndex])
    })
  }

  render() {
    const {AlphabetList} = this.state
    const alphabetList = AlphabetList.map((item, idx) => {
      let active = idx === this.state.oldIndex ? 'active' : ''
       return <span className={`item ${active}`} onClick={() => this.clickAlphabetListItem(idx)} key={idx}>{item}</span>
    })
    return (
      <div className='AlphabetFilter-container'>
        <span className='filter-lable'>拼音前缀 </span>
        {alphabetList}
      </div>
    );
  }
}

export default AlphabetFilter