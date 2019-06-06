node ('slaves'){
    slackSend message: "Build start[simple-blog] : <${env.BUILD_URL} | ${env.JOB_NAME}>"
    def result = 0
    def blue_green = 0
    def currentPath = null
    def testPath = null

    // 작업가능 slave 확인
    stage('parallel_check'){
        parallel(
            blue: { node('slave_01'){
                sh label: 'blue', script: '~/server_status/server_test.sh'
                blue_green=blue_green+1
                slackSend message: "${env.BUILD_NUMBER}:${result}:BLUE::${env.BUILD_TAG}::블루 루틴 실행가능"
                try{
                    stage('Source'){
                        sh label: 'blue', script: '~/server_status/server_git_pull.sh'
                        result = result + 1
                        sleep 5
                    }
                }catch(err){
                    echo "Caught: ${err}"
                    currentBuild.result = 'FAILURE'
                    slackSend message: "Error: 깃 커밋 ${env.BUILD_NUMBER}:${result}::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
                }
                try{
                    stage('Compile'){
                        sh label: 'blue', script: '~/server_status/server_gradle_build.sh'
                        currentPath = pwd
                        result = result + 1
                        sleep 5
                    }
                }catch(err){
                    echo "Caught: ${err}"
                    currentBuild.result = 'FAILURE'
                    slackSend message: "Error: 빌드 ${env.BUILD_NUMBER}:${result}::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
                }
                try{
                    stage('Distribute'){
                        sh label: 'blue', script: '~/server_status/server_start.sh'
                        sleep 5
                        result = result + 1
                    }
                }catch(err){
                    echo "Caught: ${err}"
                    currentBuild.result = 'FAILURE'
                    slackSend message: "Error: 배포 ${env.BUILD_NUMBER}:${result}:배포완료::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
                }
                try{
                    stage('StopJar'){
                        sleep 5
                        node('green'){
                            sh label: 'green', script: '~/server_status/server_stop.sh'
                        }
                    }
                }catch(err){
                    echo "Caught: ${err}"
                    currentBuild.result = 'FAILURE'
                    slackSend message: "Error: JAR_KILL ${env.BUILD_NUMBER}:${result}::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
                }
            }},
            green: { node('slave_02'){
                sh label: 'green', script: '~/server_status/server_test.sh'
                blue_green=blue_green+1
                slackSend message: "${env.BUILD_NUMBER}:${result}:GREEN::${env.BUILD_TAG}::그린 루틴 실행가능"

                try{
                    stage('Source'){
                        sh label: 'green', script: '~/server_status/server_git_pull.sh'
                        result = result + 1
                        sleep 5
                    }
                }catch(err){
                    echo "Caught: ${err}"
                    currentBuild.result = 'FAILURE'
                    slackSend message: "Error: 깃 커밋 ${env.BUILD_NUMBER}:${result}::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
                }
                try{
                    stage('Compile'){
                        sh label: 'green', script: '~/server_status/server_gradle_build.sh'
                        currentPath = pwd
                        result = result + 1
                        sleep 5
                    }
                }catch(err){
                    echo "Caught: ${err}"
                    currentBuild.result = 'FAILURE'
                    slackSend message: "Error: 빌드 ${env.BUILD_NUMBER}:${result}::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
                }
                try{
                    stage('Distribute'){
                        sh label: 'green', script: '~/server_status/server_start.sh'
                        sleep 5
                        result = result + 1
                    }
                }catch(err){
                    echo "Caught: ${err}"
                    currentBuild.result = 'FAILURE'
                    slackSend message: "Error: 배포 ${env.BUILD_NUMBER}:${result}:배포완료::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
                }
                try{
                    stage('StopJar'){
                        sleep 5
                        node('blue'){
                            sh label: 'blue', script: '~/server_status/server_stop.sh'
                        }
                    }
                }catch(err){
                    echo "Caught: ${err}"
                    currentBuild.result = 'FAILURE'
                    slackSend message: "Error: JAR_KILL ${env.BUILD_NUMBER}:${result}::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
                }
            }},
            failFast:false
        )
        echo "${blue_green}"
    }
    slackSend message: "${env.BUILD_NUMBER}:${result}:결과::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
}