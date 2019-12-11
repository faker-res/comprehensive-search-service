package la.niub.abcapi.servicecompre.model.bo.card;

import la.niub.abcapi.servicecompre.model.SecBasicInfoModel;

import java.util.Map;

public class CardStockBO {

    private SecBasicInfoModel stock_info;

    private CardStockBaiduBO baidu;

    private String stock_category;

    private Map<String,Object> suspend_info;

    private String abc_bigdata;

    private Map<String,Object> transac_info;

    private String unit;

    private CardStockXueqiuBO xueqiu;

    private CardStockNewssetBO stock_newsset;

    private CardStockSharePriceChangeBO stock_share_price_change;

    private CardStockWeiboBO weibo;

    public SecBasicInfoModel getStock_info() {
        return stock_info;
    }

    public void setStock_info(SecBasicInfoModel stock_info) {
        this.stock_info = stock_info;
    }

    public CardStockBaiduBO getBaidu() {
        return baidu;
    }

    public void setBaidu(CardStockBaiduBO baidu) {
        this.baidu = baidu;
    }

    public String getStock_category() {
        return stock_category;
    }

    public void setStock_category(String stock_category) {
        this.stock_category = stock_category;
    }

    public Map<String, Object> getSuspend_info() {
        return suspend_info;
    }

    public void setSuspend_info(Map<String, Object> suspend_info) {
        this.suspend_info = suspend_info;
    }

    public String getAbc_bigdata() {
        return abc_bigdata;
    }

    public void setAbc_bigdata(String abc_bigdata) {
        this.abc_bigdata = abc_bigdata;
    }

    public Map<String, Object> getTransac_info() {
        return transac_info;
    }

    public void setTransac_info(Map<String, Object> transac_info) {
        this.transac_info = transac_info;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public CardStockXueqiuBO getXueqiu() {
        return xueqiu;
    }

    public void setXueqiu(CardStockXueqiuBO xueqiu) {
        this.xueqiu = xueqiu;
    }

    public CardStockNewssetBO getStock_newsset() {
        return stock_newsset;
    }

    public void setStock_newsset(CardStockNewssetBO stock_newsset) {
        this.stock_newsset = stock_newsset;
    }

    public CardStockSharePriceChangeBO getStock_share_price_change() {
        return stock_share_price_change;
    }

    public void setStock_share_price_change(CardStockSharePriceChangeBO stock_share_price_change) {
        this.stock_share_price_change = stock_share_price_change;
    }

    public CardStockWeiboBO getWeibo() {
        return weibo;
    }

    public void setWeibo(CardStockWeiboBO weibo) {
        this.weibo = weibo;
    }

    @Override
    public String toString() {
        return "CardStockBO{" +
                "stock_info=" + stock_info +
                ", baidu='" + baidu + '\'' +
                ", stock_category='" + stock_category + '\'' +
                ", suspend_info=" + suspend_info +
                ", abc_bigdata='" + abc_bigdata + '\'' +
                ", transac_info=" + transac_info +
                ", unit='" + unit + '\'' +
                ", xueqiu=" + xueqiu +
                ", stock_newsset=" + stock_newsset +
                ", stock_share_price_change=" + stock_share_price_change +
                ", weibo=" + weibo +
                '}';
    }
}
