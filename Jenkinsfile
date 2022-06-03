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
                echo 'Testing app'
                bat "mvn sonar:sonar"
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying app'
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
