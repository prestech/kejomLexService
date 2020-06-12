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
        sh '''./mvnw compile
'''
      }
    }

    stage('test') {
      steps {
        sh '''./mvnw test

ls -l target/surefire-reports/'''
        nunit(healthScaleFactor: 1, keepJUnitReports: true, testResultsPattern: 'target/surefire-reports/*')
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