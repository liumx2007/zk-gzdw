package com.zzqx.mvc.dto;

import com.zzqx.mvc.entity.CmdList;

public class CmdListDto extends CmdList {
     private int limit0;

     private int limit1;

     private int page;

     private  int rows;

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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
