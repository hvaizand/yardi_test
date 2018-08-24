def call(environment){
    //Check labels
    //def environment = [name: 'unknown', module: 'unknown']
    def countmodule = 0
    def countname = 0
    for (label in pullRequest.labels) {
        switch(label) {
            case 'Module: Core':
                environment.module = 'core'
                countmodule += 1
            break
            case 'Module: ABF':
                environment.module = 'abf'
                countmodule += 1
            break
            case 'Environment: Sandbox':
                environment.name = 'sandbox'
                countname += 1
            break
            case 'Environment: Development':
                environment.name = 'dev'
                countname += 1
            break
            case 'Environment: Beta':
                environment.name = 'beta'
                countname += 1
            break
            case 'Environment: Test':
                environment.name = 'test'
                countname += 1
            break
            case 'Environment: 2k16':
                environment.name = '2k16'
                countname += 1
            break
            case 'Environment: UAT':
                environment.name = 'uat'
                countname += 1
            break
        }
        if(environment.citype!='skip' && environment.citype!='force'){
            switch(label){
                case 'CI: Skip':
                    environment.citype = 'skip'
                break
                case 'CI: Test':
                    environment.citype = 'test'
                break
                case 'CI: Retest':
                    environment.citype = 'test'
                break
                case 'CI: Force':
                    environment.citype = 'force'
                break
            }
        }
        environment.listLabels.add(label)
    }
    if(countmodule>1) {
        environment.module = 'multiple'
    }
    if(countname>1) {
        environment.name = 'multiple'
    }
    //Check target branch
    environment.targetBranch = getTargetEnvironment CHANGE_TARGET

    environment.listLabels.removeAll { it.toLowerCase().startsWith('ci:') }

    return environment
}
