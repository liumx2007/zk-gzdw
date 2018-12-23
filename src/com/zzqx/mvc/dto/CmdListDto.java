package com.zzqx.mvc.dto;

import com.zzqx.mvc.entity.CmdList;

public class CmdListDto extends CmdList {
     private int limit0;

     private int limit1;

    public int getLimit0() {
        return limit0;
    }

    public void setLimit0(int limit0) {
        this.limit0 = limit0;
    }

    public int getLimit1() {
        return limit1;
    }

    public void setLimit1(int limit1) {
        this.limit1 = limit1;
    }
}
