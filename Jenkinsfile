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

ls -R ./target'''
        nunit(healthScaleFactor: 1, keepJUnitReports: true, testResultsPattern: 'target/surefire-reports/TEST-com.mothertongue.controller.test.NativeLexiconTest.xml')
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