node ('slaves'){
    slackSend message: "Build start[simple-blog] : <${env.BUILD_URL} | ${env.JOB_NAME}>"
    def result = 0
    def currentPath = null
    def testPath = null
    def stopScript = null
    catchError {
        stage('StopApp'){
            sh '''
            #!/bin/bash
            unset g_pid
            target_port=12345
            pid_return(){
             echo "target_port 에 대한 PID 를 찾습니다."
             g_test=`sudo netstat -tnlp|grep $target_port|gawk {print$7}|grep -o '[0-9]*'`
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
                  echo "Kill [status] : 프로세스에 $target_port포트가 활성화 되어있습니다."
                  exit 2
                fi
              fi
              echo "Kill [stop] : $terget_port 포트에서 작업을 감지할 수 없습니다."
              exit 0
            }
            pid_return
            kill_jar
            '''
        }
        stage('StopSpring'){
            sh stopScript
            slackSend message: "${env.BUILD_NUMBER}:${result}:JAR_KILL::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
        }
        stage('Source'){
            git 'https://github.com/pollra/jenkins-gradle-test.git'
            result = result + 1
            slackSend message: "${env.BUILD_NUMBER}:${result}:깃 커밋::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
        }
        stage('Compile'){
            sh "sudo gradle clean build -x test"
            currentPath = pwd
            result = result + 1
            slackSend message: "${env.BUILD_NUMBER}:${result}:빌드완료::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
        }
        stage('Distribute'){
            sh "java -jar /home/jenkins/workspace/multi-github_master/build/libs/jenkins-gradle-test-0.0.1-SNAPSHOT.jar &"
            result = result + 1
            slackSend message: "${env.BUILD_NUMBER}:${result}:배포완료::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
        }
    }
    slackSend message: "${env.BUILD_NUMBER}:${result}:결과::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
}