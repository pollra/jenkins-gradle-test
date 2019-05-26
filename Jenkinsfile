node('master'){
    slackSend message: "Build start[simple-blog] : <${env.BUILD_URL} | ${env.JOB_NAME}>"
    def result = 0
    def currentPath = null
    catchError {
        stage('Source'){
            git 'https://github.com/pollra/jenkins-gradle-test.git'
            result = result + 1
        }
        stage('Compile'){
            sh "sudo gradle clean build -x test"
            currentPath = pwd
            result = result + 1
        }
        stage('Distribute'){
            sh "java -jar build/lib/*.jar"
            result = result + 1
        }
    }
    slackSend message: "배포 상태::${result} / ${currentPath} <${env.BUILD_URL} | ${env.JOB_NAME}>"
}