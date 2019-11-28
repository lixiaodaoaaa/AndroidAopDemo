package com.ailianlian.base.utils;
/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2017/11/30
       Time     :  09:43
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
    
    结合 SimpleDateUtils 使用，将某个文件夹下 某几天的文件全部清理：
    删除原理。枚举7天内的文件，将不是7天内的文件删除。
    
 */


import android.util.Log;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class DeleteLogUtils{
    
    /**
     * @param logPath    删除哪个目录下的日志文件
     * @param beforeDays 保留几天内的日志文件
     * 举例 传入 3 ，就会保留3天内的日志文件 ，
     * 删除 从今天算昨天、 前天3天内的日志文件
     * 举例2017-1-5 所有包含2017-1-4 2017-1-4保留 剩余的删除。
     */
    
    private static String TAG = "DeleteLogUtils";
    
    public static void deleteLogFilesByBeforeSomeDays( String logPath, int beforeDays ){
        File logDirectoryFile = new File(logPath);
        if (!logDirectoryFile.exists( )) {
            //文件不存在不用删除
            return;
        }
        long current = System.currentTimeMillis( );
        List<File> allFiles = getListFileByLogPath(logPath);
        for (File file : allFiles) {
            Log.i(TAG, "fileName:" + file.getName( ));
            long betweenDays = (current - file.lastModified( )) / (60 * 1000  * 60 * 24);
            
            Log.i(TAG, "betweenDays:" + betweenDays);
            if (betweenDays > beforeDays && !"config.ini".equals(file.getName( ))) {
                boolean delete = file.delete( );
                if (!delete) {
                    Log.i(TAG, "删除文件失败 此文件的名称为 " + file.getName( ));
                }
            }
        }
    }
    
    private static List<File> getListFileByLogPath( String logPath ){
        File logDirectoryFile = new File(logPath);
        if (logDirectoryFile.exists( ) && logDirectoryFile.isDirectory( )) {
            return Arrays.asList(logDirectoryFile.listFiles( ));
        } else {
            return null;
        }
    }
}
