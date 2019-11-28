#!/usr/bin/env bash


#copy anr log
adb pull /data/anr/ ~/vmc/log/

#copy all log
adb pull /sdcard/svm_log/ ~/vmc/log/

# copy cacheLog
adb pull /data/data/com.want.vmc/cache/log/ ~/vmc/log/