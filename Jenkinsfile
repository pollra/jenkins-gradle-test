node('master'){
    slackSend message: "Build start[simple-blog] : <${env.BUILD_URL} | ${env.JOB_NAME}>"
    def result = 0
    def currentPath = null
    catchError {
        stage('StopSpring'){
            sh "ps -aux | grep jenkins-gradle-test-0.0.1-SNAPSHOT.jar"

        }
        stage('Source'){
            git 'https://github.com/pollra/jenkins-gradle-test.git'
            result = result + 1
            slackSend message: "status::${result}/${env.BUILD_NUMBER}/${env.BUILD_TAG}/${env.EXECUTOR_NUMBER} <${env.BUILD_URL} | ${env.JOB_NAME}>"
        }
        stage('Compile'){
            sh "sudo gradle clean build -x test"
            currentPath = pwd
            result = result + 1
            slackSend message: "status::${result}/${env.BUILD_NUMBER}/${env.BUILD_TAG}/${env.EXECUTOR_NUMBER} <${env.BUILD_URL} | ${env.JOB_NAME}>"
        }
        stage('Distribute'){
            echo "빌드 단계 멈춤"
        }
    }
    slackSend message: "배포 상태::${result}/${env.BUILD_NUMBER}/${env.BUILD_TAG}/${env.EXECUTOR_NUMBER} <${env.BUILD_URL} | ${env.JOB_NAME}>"
}