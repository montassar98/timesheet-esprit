pipeline {
    agent any
    //I didnt add any specifed tools
    
    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/montassar98/timesheet-esprit'

                // Run Maven on a Unix agent.
                //sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                 bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        stage('Cealn install') {
            steps {
                bat "mvn clean install"
            }
        }
        stage('Test') {
            steps {
                echo 'Testing app with Sonar'
                bat "mvn sonar:sonar"
            }
        }
        stage('Deploy to nexus'){
            steps{
                
                nexusArtifactUploader artifacts: [
                [
                    artifactId: 'timesheet', 
                    classifier: '', 
                    file: 'target/timesheet-1.2-SNAPSHOT.jar', 
                    type: 'jar']], 
                    credentialsId: 'b87cdfea-1867-45a8-9283-0062de2cf821', 
                    groupId: 'tn.esprit.spring', 
                    nexusUrl: 'localhost:8081', 
                    nexusVersion: 'nexus3', 
                    protocol: 'http', 
                    repository: 'timesheet-esprit-snaps', 
                    version: '1.2-SNAPSHOT'
            }
        }
    }
    post{
        always{
            emailext body: 'test', subject: 'test', to: 'fedi.mannoubi@esprit.tn'
        }
        failure{
            emailext body: 'test', subject: 'test', to: 'fedi.mannoubi@esprit.tn'
        }
    }
}
