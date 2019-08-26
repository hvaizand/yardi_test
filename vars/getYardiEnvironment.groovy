def call(environment){
    //Check labels
    //def environment = [name: 'unknown', module: 'unknown']
    def countname = 0
    for (label in pullRequest.labels) {
        switch(label) {
            case 'Environment: Sandbox':
                environment.name = 'sandbox'
                environment.deploysbx = 'true'
                countname += 1
            break
            case 'Environment: Development':
                environment.name = 'dev'
                environment.deploydev = 'true'
                countname += 1
            break
            case 'Environment: Beta':
                environment.name = 'beta'
                countname += 1
            break
            case 'Environment: Test':
                environment.name = 'dev-test'
                environment.deploydev = 'true'
                environment.deploytst = 'true'
                countname += 1
            break
            case 'Environment: 2k16':
                environment.name = '2k16'
                countname += 1
            break
            case 'Environment: UAT':
                environment.name = 'dev-test-uat'
                environment.deploydev = 'true'
                environment.deploytst = 'true'
                environment.deployuat = 'true'
                countname += 1
            break
            case 'Environment: Upgrade':
                environment.name = 'upg'
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
    //Check target branch
    environment.targetBranch = getTargetEnvironment CHANGE_TARGET

    environment.listLabels.removeAll { it.toLowerCase().startsWith('ci:') }
    environment.listLabels.removeAll { it.toLowerCase().startsWith('deployed:') }
    environment.listLabels.removeAll { it.toLowerCase().startsWith('environment:') }

    echo "yardi environment ==> ${environment}"

    return environment
}
