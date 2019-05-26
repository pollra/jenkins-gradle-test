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
            sh "java -jar build/libs/jenkins-gradle-test-0.0.1-SNAPSHOT.jar"
            result = result + 1
        }
    }
    slackSend message: "배포 상태::${result}/${env.BUILD_NUMBER}/${env.BUILD_TAG}/${env.EXECUTOR_NUMBER} <${env.BUILD_URL} | ${env.JOB_NAME}>"
}