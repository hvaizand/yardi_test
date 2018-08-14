def call(){
    def branchtype = CHANGE_BRANCH.substring(0, CHANGE_BRANCH.indexOf('/')).toLowerCase()
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