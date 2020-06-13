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

cp ./target/surefire-reports/TEST-com.mothertongue.controller.test.NativeLexiconTest.xml ./test.xml

ls'''
        junit 'target/surefire-reports/*.xml'
      }
    }

    stage('sonarcube') {
      steps {
        echo 'dockerizing '
        nunit(testResultsPattern: './target/surefire-reports/*.xml', keepJUnitReports: true)
      }
    }

    stage('deploy') {
      steps {
        echo 'archiving artifact'
      }
    }

  }
}