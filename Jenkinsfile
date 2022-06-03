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
        stage('Test') {
            steps {
                echo 'Testing app with Sonar'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying app'
                bat "mvn clean"
                bat "mvn package"
                //bat "mvn deploy"
                bat "mvn sonar:sonar"
            }
        }
        stage('Upload to nexus'){
            steps{
                nexusArtifactUploader artifacts: [
                [
                    artifactId: 'timesheet-esprit', 
                    classifier: '', 
                    file: 'target/timesheet-esprit-2.2.2.RELEASE', 
                    type: 'war']], 
                    credentialsId: 'a27d902a-3811-40e2-895b-f607d25e3248', 
                    groupId: 'org.springframework.boot', 
                    nexusUrl: 'localhost:8081', 
                    nexusVersion: 'nexus2', 
                    protocol: 'http', 
                    repository: 'http://localhost:8081/repository/timesheet-esprit/', 
                    version: '2.2.2.RELEASE'
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
