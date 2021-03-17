pipeline {
    agent any

    stages {

        stage('Check environment') {
            steps {
                sh "mvn -v"
                sh "java -version"
            }
        }

        stage('Maven clean install') {
            steps {
                sh "mvn clean install"
            }
        }

        stage('Maven test') {
            steps {
                sh "mvn test"
            }
        }

    }

}