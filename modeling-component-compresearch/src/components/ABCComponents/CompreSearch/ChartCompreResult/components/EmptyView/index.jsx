import React from 'react';

// style
import './index.scss';
import '../../../../../../assets/image/icons/emptyState.png'
// images
// const iconEmptyNormal = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHwAAACLCAMAAACUVw0tAAABhlBMVEUAAAAAAAAAAAAAAADlubEAAAAAAAAEBAQAAAAEBAQAAADT4vZVW2CbprWWn64AAADhxMXQ3vLBzuO4xNe0v8+xvc7fx8m51v3d09vX1uPF0/HW5vvbv7/T4fbZwcPR4PTVwsbhtavJ1+rG1OfFxtWsuMqapbZBRUt5gI291fnYxsu+0vXay9Lhw8LW5vvV5frT4/jT4vfDz+zfx8jFzenU0uDbzNXfxMPH1unSw8/U1eO/ze7kt63O3fDYvsHQzdjL1+jJxdngtq3JuLzOwse2w9W1wNOxpavrrZa/0/fA0vaPj49yfYjsqo653P////+31f/W5frV5PnT4vfM2+/Q3/S41f7Y5vvZ5/u51v/d6fy71//l7/zb6fvR4vvO4fu92P7o8f3f6/3G3f3K3/z8/v/4+/++2f/z+P7w9v7B2v3U5PvS5PrD3P7h7P36/P/P4//J4P/r8v261fvO4PjT4fbX4PH1+f7V5v7t9P2+2v3L3/rR4PXa3Ovb1eDbztTfys3Vvb7Y84RrAAAAT3RSTlMAAgoFugcMEAQSDsoaNS4X/LFuU1FL/v39/Pv079q+vZ2ZhXtxPjwnJv399vb29fT08+7u7OjZ2NPLuLSxqqSYkoV9dm1iXVVOQDk5LxsW4mHQwwAABFZJREFUeNrs2MlP21AQx3Ev9fLsOCQkQVRAoeyquqrqokpVK3U7tNLjl0Ug1EsOHLjAiXBi6X/esVvzELbjFHmcSvh74fjJDHKUsXa79BtprCXp8nlFu6ZpWhT9cVn5JE2wQdm2QdEH4OYV7dLI5Dq1jVZro+bQJ6AFuKy8mtqikZ36qo8ofzVwbBqfefq/Cw/p9jKgWm6FPN/wauNEd3zcyO8Qr3bPYkd0vYmUmvWIZ9HjsT1nbQGpLawJLx6eYeXR2D4y84NweNKp4lfuidlnGNPTWeGp1Re5chq79h45Nemxv93q9dTUyoOlbm5Lm7R60rP7B1s9X+Lri94EPf8i1FOXWiYumRuHm5I5Mxt3Lcmc5WbipiGZM8wKr/AKr3BVhVf4XcF3Jq4Q3ObGbcKzfsh43Lhn6Wm6Hm5dSOaEYSZ1PcyyZyRzM7YVSknbNBx+3KHRwxL3kCe2JHNbwlPX1HXbFj/eSuYWv4v4mrpmW2Rvvvolmes/+had0bF+df62H9/vS+b6g4tPQunq9J4bDUrAe+crV0e89sd2xMdGt1cK3j17EOtaaNPZ/+4SZeEYvYleIRAevXLYfn2OJL5TREmcpMXt6BWCFtrByxFKxKknQahrhi06DaBkHI22sA3NE+tzUDhvCsfluvA0sTKPaeC4+CC0h2eYDo75zxowLRz3KrzCC/+SqfD/43+OVBxl4CfIwHHCjg+QwJUueRtiDI6jA8nXwRHG4tg/llwd7yMHx96p5Ol0D7k4dg8lR4e7yMepoSy+ITAZjp+Ff8P9LqZudpuGgjAMn7tAdIHEErFDbBAICVaABMxYauxjO4lrmzqyY9SkXfTv4hmkSlabtvY539h+b+DZzHwpD8bl7FRxOTUHnJenivjpkp1wDje/tdpE7IaLvley9yG74lKqYqfMPrhsrcaiOuPd2WHJqXnjHG3QUwNw2VpsURFcOkEWFcV5bcknu2YYl1aJh52sWAXnpnK2q4YBHDs7OTUY79q7LSozgGNbm7IyzvmfoYuaszouWwssKorL1gKLiuIcXvXaVyGzLt51AiwqjHMKnDmKR2f0XGcRd2njyx093647dW08bqmvNmYeBf9bUX9VPgqeJzSkJNfHw9zSsGweKuNhTMOLQ1U8uiSXLiMY73/v/ofH8XhHru1iJfz2nNw7v1XBj8X20Y8V8IsF+bW4APDOBnQIvxHbX7+B8JKwCgAvCK3wxmvCqz3xjDTKAg+8SUinrHHGt5a0sltHfLUgvRYrJ3wttqa+dsBL0q4cjLekXzsQr2mM6kF4ReNU9ePy3mOVNAc48N7gwwsOvDf48IID7w08/CFe0viVT+AFTVHxKF7TNLWHeHBNU3UdPMCDjKYrC+7h24SmLNl2OPDeyMMLDrw39PB3eOlh43rJ/Mm8LGieCn5t3tNc1d/MR5qrI2N+vaB5sl+NMd/n0e0r878fb2n6ss/mri/v3liaLpsdffhppH/mnv9OjdpyvAAAAABJRU5ErkJggg==';

class EmptyView extends React.Component {
    constructor(props) {
        super(props);

        this.state = {};
    }

    render() {
        let {language} = this.props;
        return (
            <div className="empty-mode-box">
                <img src={require('../../../../../../assets/image/icons/emptyState.png')} style={{
                    display: 'block',
                    margin: '0px auto 8px'
                }}/>
                <p className={'default-text'}>暂无内容</p>
            </div>
        );
    }
}

export default EmptyView;