package com.altale.service.impl;

import com.altale.util.Constant;
import com.altale.util.DateUtil;

import java.io.File;
import java.util.Date;

public class CreateTestFileUtil {
    public static void create() {
        Date now = new Date();
        Date lim = DateUtil.toDayBefore(now, 15);
        File dir=new File(Constant.jspath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        while (lim.before(now)) {
            File file = new File(Constant.jspath + DateUtil.dateToString(lim, 1) + ".json");
            if (!file.exists()) {
                try {
                    file.createNewFile();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println(file.getAbsolutePath()+" "+now.toString());
            lim=DateUtil.toDayBefore(lim,-1);
        }
    }
}
