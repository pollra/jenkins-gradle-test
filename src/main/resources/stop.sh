#!/bin/bash
unset g_pid
target_port=12345
pid_return(){
 echo "target_port 에 대한 PID 를 찾습니다."
 g_test=`sudo netstat -tnlp|grep $target_port|gawk {print$7}|grep -o '$
 if [ g_test=='' ]; then
    echo "작업이 감지되지 않습니다."
    exit 1
 fi
 unset result_num
  for obj in `echo $g_test`
 do
  result_num=$obj
 done
  g_pid=$result_num
}
kill_jar(){
  if [ unset!=$g_pid ]; then
    echo "Kill [start] :  PID[$g_pid] 에서 작업이 감지되었습니다."
    sudo kill -9 $g_pid
    pid_return
    if [ $g_pid!= '' ]; then
      echo "Kill [status] : 프로세스에 $target_port포트가 활성화 되어>$
      exit 2
    fi
  fi
  echo "Kill [stop] : $terget_port 포트에서 작업을 감지할 수 없습니다."
  exit 0
}
pid_return
kill_jar
