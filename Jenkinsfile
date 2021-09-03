pipeline {
  agent any

  tools {
    jdk 'java-11-openjdk'
    maven 'mvn-3.6.0'
  }

  stages {
    stage('Build') {
      steps {
        withMaven(maven : 'mvn-3.6.0') {
          sh 'mvn package -DskipTests'
        }
      }
    }
    
    stage('SonarQube analysis') {
      steps {
        withSonarQubeEnv(installationName: 'sonarqube-server') {
          withMaven(maven : 'mvn-3.6.0') {
            sh 'mvn sonar:sonar -Dsonar.dependencyCheck.jsonReportPath=target/dependency-check-report.json -Dsonar.dependencyCheck.xmlReportPath=target/dependency-check-report.xml -Dsonar.dependencyCheck.htmlReportPath=target/dependency-check-report.html -Dsonar.login=db8b24d389706626c722536a86fd7e4af4f0ea87'
          }
        }
      }      
    }
          
    
  }
}
      