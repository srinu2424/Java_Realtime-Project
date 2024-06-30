pipeline {
    agent any

    environment {
        ECR_REPOSITORY = 'your-account-id.dkr.ecr.your-region.amazonaws.com/hello-world-app'
        ECR_LOGIN = 'aws ecr get-login-password --region your-region | docker login --username AWS --password-stdin your-account-id.dkr.ecr.your-region.amazonaws.com'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Nexus Deployment') {
            steps {
                sh 'mvn clean deploy'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t jsbimg .'
                }
            }
        }

        stage('Push Docker Image to ECR') {
            steps {
                script {
                    sh ECR_LOGIN
                    sh "docker tag hello-world-app:latest ${ECR_REPOSITORY}:latest"
                    sh "docker push ${ECR_REPOSITORY}:latest"
                }
            }
        }

        stage('Deploy to EKS') {
            steps {
                script {
                    sh 'helm upgrade --install hello-world-app ./hello-world-app --set image.repository=${ECR_REPOSITORY} --set image.tag=latest'
                }
            }
        }
    }
}
