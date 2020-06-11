pipeline {
  agent any
  stages {
    stage('Pull updates from git') {
      steps {
        sh 'git pull'
      }
    }

    stage('Build') {
      steps {
        sh '''ls -l
./mvnw clean'''
      }
    }

    stage('test') {
      steps {
        echo 'running sonar cube '
      }
    }

    stage('sonarcube') {
      steps {
        echo 'dockerizing '
      }
    }

    stage('deploy') {
      steps {
        echo 'archiving artifact'
      }
    }

  }
}