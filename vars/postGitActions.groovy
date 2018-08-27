def call(Map config) {
    withCredentials([usernamePassword(credentialsId: config.credentials, passwordVariable: 'password', usernameVariable: 'username')]) {
//        pullRequest.addLabels(config.labels)
        pullRequest.labels = config.listLabels
        pullRequest.addLabels(config.labels)
        def comment = pullRequest.comment(config.comment)
    }
}