pipeline {
  agent none
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn clean install -Dtest.ENV=int -Dgroups=SmokeTest'
            }
        }
    }
}	
