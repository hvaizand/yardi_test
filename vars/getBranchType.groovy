def call(){
    def branchtype = CHANGE_BRANCH.substring(0, CHANGE_BRANCH.indexOf('/')).toLowerCase()
    switch(branchtype) {
        case "feature":
            BRANCHTYPE = 'feature'
        break
        case "hotfix":
            BRANCHTYPE = 'hotfix'
        break
        case "datafix":
            BRANCHTYPE = 'datafix'
        break
        case "release":
            BRANCHTYPE = 'release'
        break
        default:
            BRANCHTYPE = 'error'
        break
    }
}