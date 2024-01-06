pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Checkout source code from version control
                    checkout scm
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    // Build the Maven project
                    sh 'mvn clean install'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Run tests
                    sh 'mvn test'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Deploy or run the Spring Boot application
                    sh 'java -jar target/your-application.jar'
                }
            }
        }
    }
}
