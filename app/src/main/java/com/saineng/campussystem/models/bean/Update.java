package com.saineng.campussystem.models.bean;

import java.util.List;

/**
 * Created by ${charles}     on 2017/6/8.
 *
 * @desc ${TODO}
 */

public class Update
{


    /**
     * result : 0
     * server_time : 201608310123456789
     * list : [{"order_id":"201608310123456790"},{"order_id":"201608310123456790"}]
     */

    private int result;
    private String server_time;
    /**
     * order_id : 201608310123456790
     */

    private List<ListBean> list;

    public int getResult()
    {
        return result;
    }

    public void setResult(int result)
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

        public String getOrder_id()
        {
            return order_id;
        }

        public void setOrder_id(String order_id)
        {
            this.order_id = order_id;
        }
    }
}
