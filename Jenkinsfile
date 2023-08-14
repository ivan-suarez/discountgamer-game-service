pipeline {
    agent any
    stages {
        stage ("build"){
            steps {
                echo 'building the application'
                sh 'mvn clean install -DskipTests'
            }
        }
        stage ("test"){
            steps {
                 echo 'testing the application'
            }
        }
        stage ("deploy"){
            steps {
                 echo 'deploying the application'
            }
        }
    }
} 