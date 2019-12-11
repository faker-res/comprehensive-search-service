export function matchDomain(env) {
    let ucDomain = '';
    if (env === 'dev') {//dev环境
        ucDomain = 'https://api-invest-dev.modeling.ai';
    } else if (env === 'pre') {//pre环境
        ucDomain = 'https://api-invest-pre.modeling.ai';
    } else if (env === 'prod') {//prod环境
        ucDomain = 'https://api-invest.modeling.ai';
    }
    return ucDomain;
}