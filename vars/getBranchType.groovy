def call(changebranch){
    def branchtype = changebranch.substring(0, changebranch.indexOf('/')).toLowerCase()
    switch(branchtype) {
        case "feature":
            branchtype = 'feature'
        break
        case "hotfix":
            branchtype = 'hotfix'
        break
        case "datafix":
            branchtype = 'datafix'
        break
        case "release":
            branchtype = 'release'
        break
        default:
            branchtype = 'error'
        break
    }
    return branchtype
}