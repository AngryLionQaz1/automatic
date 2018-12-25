APP_PATH=ssh://admin@47.92.213.93:29418/~admin/automatic.git
APP_NAME=automatic
git clone $APP_PATH
if [ $? -eq "0" ];then
   echo $(cd `dirname $0`; pwd)
else
    cd $APP_NAME
    echo $(cd `dirname $0`; pwd)
    git pull
fi
