node ('slaves'){
    slackSend message: "Build start[simple-blog] : <${env.BUILD_URL} | ${env.JOB_NAME}>"
    def result = 0
    def currentPath = null
    def testPath = null
    catchError {
        stage('StopSpring'){
            sh label: 'slave', script: 'sudo kill -9 `netstat -tnlp|grep 12345|gawk \'{ print $7 }\'|grep -o \'[0-9]*\'`'
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