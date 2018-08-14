def call(changebranch){
    def changebranch.substring(0, changebranch.indexOf('/')).toLowerCase()
    switch(branchtype) {
        case "feature":
            'feature'
        break
        case "hotfix":
            'hotfix'
        break
        case "datafix":
            'datafix'
        break
        case "release":
            'release'
        break
        default:
            'error'
        break
    }
}