pipeline {
    agent any
    environment {
        ENCRYPTION_SECRET_PASSWORD = credentials('ENCRYPTION_SECRET_PASSWORD')
        ENCRYPTION_SECRET_SALT = credentials('ENCRYPTION_SECRET_SALT')
        ADMIN_USERNAME = credentials('ADMIN_USERNAME')
        ADMIN_PASSWORD = credentials('ADMIN_PASSWORD')
        JWT_SECRET = credentials('JWT_SECRET')
    }
    stages {

        stage('Build Project') {
            steps {
                sh './gradlew clean build'
            }
        }
    }

    post {

        success {
            junit '**/build/test-results/**/TEST-*.xml'
            jacoco buildOverBuild: true, deltaBranchCoverage: '85', deltaClassCoverage: '85', deltaComplexityCoverage: '85', deltaInstructionCoverage: '.90', deltaLineCoverage: '85', deltaMethodCoverage: '85'
        }

        failure {
            emailext body: '$DEFAULT_BODY', recipientProviders: [culprits(), upstreamDevelopers()], subject: '$DEFAULT_SUBJECT', to: '$DEFAULT_RECIPIENTS'
        }
    }
}