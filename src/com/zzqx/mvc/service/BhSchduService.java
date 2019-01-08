package com.zzqx.mvc.service;


import com.zzqx.mvc.entity.BhSchdu;


import java.util.List;

public interface BhSchduService {

    List<BhSchdu> selectByExample();

    BhSchdu selectByEmIdAndDate(BhSchdu bhSchdu);
}
