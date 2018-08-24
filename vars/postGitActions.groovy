def call(Map config) {
    withCredentials([usernamePassword(credentialsId: config.credentials, passwordVariable: 'password', usernameVariable: 'username')]) {
//        pullRequest.addLabels(config.labels)
        pullRequest.labels = config.listLabels
        def comment = pullRequest.comment(config.comment)
    }
}