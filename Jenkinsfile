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
                    bat 'mvn clean install'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Run tests
                    bat 'mvn test'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Deploy or run the Spring Boot application
                    bat 'java -jar target/AirQualityBackend-0.0.1-SNAPSHOT.jar'
                }
            }
        }
    }
}
