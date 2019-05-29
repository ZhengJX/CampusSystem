package com.saineng.campussystem.models.bean;

/**
 * Created by mop on 15/12/26.
 */
public class BaseResult
{


    /**
     * status : 1
     * msg : 返回成功
     */

    private String status;
    private String msg;

    public String getStauts()
    {
        return status;
    }

    public void setStauts(String stauts)
    {
        this.status = stauts;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
}
