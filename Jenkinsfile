pipeline {
    agent any
    
    environment {
        MAVEN_HOME = tool 'Maven'
    }

    stages {
        stage('Build') {
            steps {
                bat "\"${MAVEN_HOME}\\bin\\mvn\" clean package"
            }
        }

        stage('Stop Service') {
            steps {
                bat "net stop Payroll"
                sleep time: 10, unit: 'SECONDS'
             	bat 'sc query Payroll | find "STATE" | find "STOPPED" && echo "Service stopped"'
            }
        }

 		stage('Copy Jar File') {
            steps {
                bat "copy /Y target\\Employee.jar G:\\HRMS_API\\Payroll"
            }
        }

    }
     post {
        always {
           bat "nssm start Payroll"
        }
    }
}