def call(targetbranch){
    def targetbranch = targetbranch.toLowerCase()
    switch(targetbranch) {
        case "sandbox":
            targetbranch = 'Core Sandbox'
        break
        case "develop":
            targetbranch = 'Core UAT'
        break
        case "uat":
            targetbranch = 'Core UAT'
        break
        case "master":
            targetbranch = 'Core Production'
        break
        default:
            targetbranch = 'error'
        break
    }
    return targetbranch
}