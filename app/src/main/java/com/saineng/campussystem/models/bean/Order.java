package com.saineng.campussystem.models.bean;

import java.util.List;

/**
 * Created by ${charles}     on 2017/6/5.
 *
 * @desc ${TODO}
 */

public class Order
{


    /**
     * result : 1
     * server_time : 20170608092216
     * list : [{"order_id":"201705228335201833","goods_name":"鸡腿饭套餐","goods_num":"1","user_name":"学生1","user_id":"750c4185","user_phone":"15622724419","valid_start":"10:00","valid_end":"15:00"},{"order_id":"201705221694148374","goods_name":"鸡腿饭套餐","goods_num":"1","user_name":"学生1","user_id":"750c4185","user_phone":"15622724419","valid_start":"10:00","valid_end":"15:00"},{"order_id":"201705228148068729","goods_name":"冬菇鸡腿饭汤套餐","goods_num":"1","user_name":"学生1","user_id":"750c4185","user_phone":"15622724419","valid_start":"10:00","valid_end":"15:00"},{"order_id":"201706073406841350","goods_name":"榨菜牛肉汤套餐","goods_num":"9","user_name":"学生1","user_id":"750c4185","user_phone":"15622724419","valid_start":"10:00","valid_end":"15:00"},{"order_id":"201706093132131566","goods_name":"鸡腿饭套餐","goods_num":"1","user_name":"学生1","user_id":"750c4185","user_phone":"15622724419","valid_start":"10:00","valid_end":"15:00"},{"order_id":"201706095753261577","goods_name":"冬菇鸡腿饭汤套餐","goods_num":"1","user_name":"学生1","user_id":"750c4185","user_phone":"15622724419","valid_start":"10:00","valid_end":"15:00"},{"order_id":"201706095187016441","goods_name":"榨菜牛肉汤套餐","goods_num":"1","user_name":"学生1","user_id":"750c4185","user_phone":"15622724419","valid_start":"10:00","valid_end":"15:00"},{"order_id":"201706072932214276","goods_name":"榨菜牛肉汤套餐","goods_num":"10","user_name":"学生1","user_id":"750c4185","user_phone":"15622724419","valid_start":"10:00","valid_end":"15:00"},{"order_id":"201706071025074381","goods_name":"榨菜牛肉汤套餐","goods_num":"1","user_name":"学生1","user_id":"750c4185","user_phone":"15622724419","valid_start":"10:00","valid_end":"15:00"},{"order_id":"201706070705944654","goods_name":"鲜辣排骨饭汤套餐","goods_num":"1","user_name":"学生1","user_id":"750c4185","user_phone":"15622724419","valid_start":"10:00","valid_end":"15:00"}]
     */

    private String result;
    private String server_time;
    /**
     * order_id : 201705228335201833
     * goods_name : 鸡腿饭套餐
     * goods_num : 1
     * user_name : 学生1
     * user_id : 750c4185
     * user_phone : 15622724419
     * valid_start : 10:00
     * valid_end : 15:00
     */

    private List<ListBean> list;

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public String getServer_time()
    {
        return server_time;
    }

    public void setServer_time(String server_time)
    {
        this.server_time = server_time;
    }

    public List<ListBean> getList()
    {
        return list;
    }

    public void setList(List<ListBean> list)
    {
        this.list = list;
    }

    public static class ListBean
    {
        private String order_id;
        private String goods_name;
        private String goods_num;
        private String user_name;
        private String user_id;
        private String user_phone;
        private String valid_start;
        private String valid_end;

        public String getOrder_id()
        {
            return order_id;
        }

        public void setOrder_id(String order_id)
        {
            this.order_id = order_id;
        }

        public String getGoods_name()
        {
            return goods_name;
        }

        public void setGoods_name(String goods_name)
        {
            this.goods_name = goods_name;
        }

        public String getGoods_num()
        {
            return goods_num;
        }

        public void setGoods_num(String goods_num)
        {
            this.goods_num = goods_num;
        }

        public String getUser_name()
        {
            return user_name;
        }

        public void setUser_name(String user_name)
        {
            this.user_name = user_name;
        }

        public String getUser_id()
        {
            return user_id;
        }

        public void setUser_id(String user_id)
        {
            this.user_id = user_id;
        }

        public String getUser_phone()
        {
            return user_phone;
        }

        public void setUser_phone(String user_phone)
        {
            this.user_phone = user_phone;
        }

        public String getValid_start()
        {
            return valid_start;
        }

        public void setValid_start(String valid_start)
        {
            this.valid_start = valid_start;
        }

        public String getValid_end()
        {
            return valid_end;
        }

        public void setValid_end(String valid_end)
        {
            this.valid_end = valid_end;
        }
    }
}
