pipeline {
    agent any
    tools {
        maven 'maven-3.9.1'
    }

    stages {
        stage('Clone Git Project') {
            steps {
                echo 'Clone Git Project...'
                git(branch: 'master', changelog: true, poll: true, url: 'https://github.com/Radec24/postly.git')
            }
        }

        stage('Compile Project') {
            steps {
                withCredentials([string(credentialsId: 'JASYPT_ENCRYPTOR_PASSWORD', variable: 'JASYPT_ENCRYPTOR_PASSWORD_VALUE')]) {
                    echo 'Compile Project...'
                    sh 'mvn clean compile -DargLine="' $ { JASYPT_ENCRYPTOR_PASSWORD_VALUE } '"'
                }
            }
        }

        stage('Project Tests') {
            parallel {
                stage('Unit Tests') {
                    steps {
                        echo 'Unit Tests...'
                        sh 'mvn clean test'
                    }
                }

                stage('Integration Tests') {
                    steps {
                        echo 'Integration Tests...'
                        sh 'mvn clean verify'
                    }
                }

            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'SonarQube Analysis...'
                withSonarQubeEnv('SonarQube-localhost-9000') {
                    sh 'mvn clean sonar:sonar'
                }

                waitForQualityGate true
            }
        }
    }
}