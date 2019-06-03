node ('slaves'){
    slackSend message: "Build start[simple-blog] : <${env.BUILD_URL} | ${env.JOB_NAME}>"
    def result = 0
    def currentPath = null
    def testPath = null
    catchError {
        stage('StopJar'){
            sh '~/server_status/stop.sh'
            slackSend message: "${env.BUILD_NUMBER}:${result}:JAR_KILL::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
            sleep 5
        }
        stage('Source'){
            sh '~/server_status/git_pull_test01.sh'
            result = result + 1
            slackSend message: "${env.BUILD_NUMBER}:${result}:깃 커밋::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
            sleep 5
        }
        stage('Compile'){
            sh '~/server_status/gradle_build_test01.sh'
            currentPath = pwd
            result = result + 1
            slackSend message: "${env.BUILD_NUMBER}:${result}:빌드완료::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
            sleep 5
        }
        stage('Distribute'){
            sleep 5
            sh 'java -jar /home/jenkins/workspace/multi-github_master/build/libs/jenkins-gradle-test-0.0.1-SNAPSHOT.jar'
            result = result + 1
            slackSend message: "${env.BUILD_NUMBER}:${result}:배포완료::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
        }
    }
    slackSend message: "${env.BUILD_NUMBER}:${result}:결과::${env.BUILD_TAG}:: <${env.BUILD_URL} | ${env.JOB_NAME}>"
}