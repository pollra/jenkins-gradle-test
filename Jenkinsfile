node ('slaves'){
    slackSend message: "Build start[simple-blog] : <${env.BUILD_URL} | ${env.JOB_NAME}>"
    def result = 0
    def blue_green = 0
    def currentPath = null
    def testPath = null
    catchError {
    // 작업가능 slave 확인
        stages('slave_check'){
            catchError {
                stage('parallel_check'){
                    parallel(
                        blue: { node('slave_01'){
                            sh label: 'blue', script: '~/server_status/server_test.sh'
                            // blue_green=0
                            slackSend message: "${env.BUILD_NUMBER}:${result}:BLUE::${env.BUILD_TAG}::블루 루틴 실행가능"
                        }},
                        green: { node('slave_02'){
                            sh label: 'green', script: '~/server_status/server_test.sh'
                            // blue_green=1
                            slackSend message: "${env.BUILD_NUMBER}:${result}:GREEN::${env.BUILD_TAG}::그린 루틴 실행가능"
                        }}
                    )
                }
            }
        }

        if(blue_green == 0){
            stages('blue'){
                stage('Source'){
                    sh label: 'blue', script: '~/server_status/git_pull_test01.sh'
                    result = result + 1
                    slackSend message: "${env.BUILD_NUMBER}:${result}:깃 커밋::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
                    sleep 5
                }
                stage('Compile'){
                    sh label: 'blue', script: '~/server_status/gradle_build_test01.sh'
                    currentPath = pwd
                    result = result + 1
                    slackSend message: "${env.BUILD_NUMBER}:${result}:빌드완료::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
                    sleep 5
                }
                stage('Distribute'){
                    sleep 5
                    sh label: 'blue', script: '~/server_status/server_start.sh'
                    result = result + 1
                    slackSend message: "${env.BUILD_NUMBER}:${result}:배포완료::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
                }
                stage('StopJar'){
                    sh label: 'green', script: '~/server_status/stop.sh'
                    slackSend message: "${env.BUILD_NUMBER}:${result}:JAR_KILL::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
                    sleep 5
                }
            }
        }
        if(blue_green == 0){
            stages('green'){
                stage('Source'){
                    sh label: 'green', script: '~/server_status/git_pull_test01.sh'
                    result = result + 1
                    slackSend message: "${env.BUILD_NUMBER}:${result}:깃 커밋::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
                    sleep 5
                }
                stage('Compile'){
                    sh label: 'green', script: '~/server_status/gradle_build_test01.sh'
                    currentPath = pwd
                    result = result + 1
                    slackSend message: "${env.BUILD_NUMBER}:${result}:빌드완료::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
                    sleep 5
                }
                stage('Distribute'){
                    sleep 5
                    sh label: 'green', script: '~/server_status/server_start.sh'
                    result = result + 1
                    slackSend message: "${env.BUILD_NUMBER}:${result}:배포완료::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
                }
                stage('StopJar'){
                    sh label: 'blue', script: '~/server_status/stop.sh'
                    slackSend message: "${env.BUILD_NUMBER}:${result}:JAR_KILL::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
                    sleep 5
                }
            }
        }
    }
    slackSend message: "${env.BUILD_NUMBER}:${result}:결과::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
}