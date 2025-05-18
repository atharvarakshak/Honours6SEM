# Honours 6 th sem mini project

## Jenkins pipelining for continuous EC2 deployment

```groovy
pipeline {
    agent {
        docker {
            image 'maven:3.8.5-openjdk-17'
            args '-v /c/ProgramData/Jenkins/.jenkins/workspace/airlline-automation:/app -w /app' // Mounting Maven cache from host Windows system
        }
    }

    environment {
        GIT_REPO = 'https://github.com/atharvarakshak/Honours6SEM.git'
        BRANCH = 'main'
        EC2_USER = 'ubuntu'
        EC2_IP = '18.206.245.117'
        REMOTE_DIR = '/home/ubuntu/app'
    }

    stages {
        stage('Clone Repository') {
            steps {
                sh 'git clone -b ${BRANCH} ${GIT_REPO} .'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Detect JAR Name') {
            steps {
                script {
                    def jarFile = sh(script: "ls target/*.jar | grep -v 'original' | head -n 1", returnStdout: true).trim()
                    env.JAR_NAME = jarFile.tokenize('/').last()
                    echo "Detected JAR name: ${env.JAR_NAME}"
                }
            }
        }

        stage('Copy to EC2') {
            steps {
                sshagent (credentials: ['ec2-key']) {
                    sh """
                        ssh -o StrictHostKeyChecking=no ${EC2_USER}@${EC2_IP} "mkdir -p ${REMOTE_DIR}"
                        scp -o StrictHostKeyChecking=no target/${JAR_NAME} ${EC2_USER}@${EC2_IP}:${REMOTE_DIR}/
                    """
                }
            }
        }

        stage('Run JAR on EC2') {
            steps {
                sshagent (credentials: ['ec2-key']) {
                    sh """
                        ssh -o StrictHostKeyChecking=no ${EC2_USER}@${EC2_IP} << EOF
                            pkill -f '${JAR_NAME}' || true
                            cd ${REMOTE_DIR}
                            nohup java -jar ${JAR_NAME} > app.log 2>&1 &
                        EOF
                    """
                }
            }
        }
    }

    post {
        success {
            echo '✅ Application deployed and started on EC2 successfully!'
        }
        failure {
            echo '❌ Deployment or startup failed!'
        }
    }
}
```

## API outputs for Flight Booking System using SpringBoot


![image](https://github.com/user-attachments/assets/eea65f88-5d23-467e-b019-cfef8548f563)

![image](https://github.com/user-attachments/assets/016ca0ba-af0c-417d-b1d5-04b06628c356)

![image](https://github.com/user-attachments/assets/5aa99480-dc19-49d0-82e0-6dbed86681de)

![image](https://github.com/user-attachments/assets/1a942cd2-0c16-4205-b30b-d61b77c5e3b4)


![image](https://github.com/user-attachments/assets/97db855e-ec08-41d0-9a54-09b2dce19c23)

