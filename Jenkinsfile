node('master'){
    slackSend message: "Build start[simple-blog] : <${env.BUILD_URL} | ${env.JOB_NAME}>"
    def result = 0
    catchError {
        stage('Source'){
            git 'https://github.com/pollra/jenkins-gradle-test.git'
            result = result + 1
        }
        stage('Compile'){
            sh "gradle clean build -x test"

            result = result + 1
        }
        stage('Distribute'){
            sh "ls -al"
        }
    }
    slackSend message: "배포 상태::${result} <${env.BUILD_URL} | ${env.JOB_NAME}>"
}