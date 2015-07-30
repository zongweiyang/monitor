#!/bin/bash
PRG="$0"
currentpath=`dirname $(readlink -f $0)`
echo $currentpath
echo $(dirname $currentpath)
frontdir=$(dirname $currentpath)
echo $frontdir
javapid=$(ps -ef|grep java|grep monitor|awk '{print $2}')
echo $javapid
if [ -n "$javapid" ]
then
kill -9 $javapid
sleep 5s
fi