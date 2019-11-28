#!/bin/bash

set -eo pipefail


echo '
                           / ()\
                         _|_____|_
                        | | === | |
                        |_|  O  |_|
                         ||  O  ||
                         ||__*__||
                        |~ \___/ ~|
                        /=\ /=\ /=\
________________________[_]_[_]_[_]_________________________'

webHeader="http://"

projectName=project_vmc_management

appPath=/Users/lixiaodaoaaa/project/want_project/${projectName}/app

apkFile=/Users/lixiaodaoaaa/project/want_project/${projectName}/apkFile

currentTag=$( git  symbolic-ref --short --q HEAD | sed  's/\//_/' )


cd ${apkFile}

if [ "`ls -A `" = "" ]; then
    echo "apkFile directory is empty  not need to delete"
else
    echo " apkFile directory not empty need to delete then  execute delete it!!!!"
    rm *.*
fi



cd ${appPath}

versionName=$(gradle printVersionName --q)

webServerFileApkName=/Library/WebServer/Documents/apk/${projectName}/${currentTag}/`date +%Y%m%d`

cd ${appPath}
gradle  aR

echo ${webServerFileApkName}

if [ ! -d ${webServerFileApkName} ]; then
  mkdir -p ${webServerFileApkName}
fi

cd ${webServerFileApkName}

if [ "`ls -A `" = "" ]; then
    echo "directory is empty  not need to delete"
else
    echo "sever apk directory not empty need to delete then  execute delete it!!!!"
    rm *.*
fi

cd ${apkFile}
cp  *.apk   ${webServerFileApkName}

ipAddress=$(ifconfig -a|grep inet|grep -v 127.0.0.1|grep -v inet6|awk '{print $2}'|tr -d "addr:"  | grep  10 | cut -d: -f2)

fullWebAddress=${webHeader}${ipAddress}/apk/${projectName}/${currentTag}/`date +%Y%m%d`

open ${fullWebAddress}

echo "success move apk file To Server apk Folder"
echo  '

      '
echo "add ip address is  "${fullWebAddress}