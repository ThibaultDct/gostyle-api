pipeline {
    agent any

    stages {

        stage('Check environment') {
            steps {
                sh "mvn -v"
                sh "java -version"
            }
        }

        stage('Maven test') {
            steps {
                sh "mvn test"
            }
        }

    }

}