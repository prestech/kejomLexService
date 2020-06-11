pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        echo 'Build App'
      }
    }

    stage('Test') {
      steps {
        sh 'echo running test'
      }
    }

    stage('sornar cube') {
      steps {
        echo 'running sonar cube '
      }
    }

    stage('dockerize') {
      steps {
        echo 'dockerizing '
      }
    }

    stage('archive artifact') {
      steps {
        echo 'archiving artifact'
      }
    }

  }
}