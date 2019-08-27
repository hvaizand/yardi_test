def call(environment){
    //Check labels
    //def environment = [name: 'unknown', module: 'unknown']
    def countname = 0
    for (label in pullRequest.labels) {
        switch(label) {
            case 'Environment: Sandbox':
                environment.name = 'sandbox'
                environment.deploysbx = 'true'
                environment.listLabels.removeAll { it.toLowerCase().startsWith('deployed: sbx') }
                countname += 1
            break
            case 'Environment: Development':
                environment.name = 'dev'
                environment.deploydev = 'true'
                environment.listLabels.removeAll { it.toLowerCase().startsWith('deployed: dev') }
                environment.listLabels.removeAll { it.toLowerCase().startsWith('deployed: tst') }
                environment.listLabels.removeAll { it.toLowerCase().startsWith('deployed: uat') }
                countname += 1
            break
            case 'Environment: Beta':
                environment.name = 'beta'
                environment.listLabels.removeAll { it.toLowerCase().startsWith('deployed: beta') }
                countname += 1
            break
            case 'Environment: Test':
                environment.name = 'dev-test'
                environment.deploydev = 'true'
                environment.deploytst = 'true'
                environment.listLabels.removeAll { it.toLowerCase().startsWith('deployed: dev') }
                environment.listLabels.removeAll { it.toLowerCase().startsWith('deployed: tst') }
                environment.listLabels.removeAll { it.toLowerCase().startsWith('deployed: uat') }
                countname += 1
            break
            case 'Environment: 2k16':
                environment.name = '2k16'
                environment.listLabels.removeAll { it.toLowerCase().startsWith('deployed: 2k16') }
                countname += 1
            break
            case 'Environment: UAT':
                environment.name = 'dev-test-uat'
                environment.deploydev = 'true'
                environment.deploytst = 'true'
                environment.deployuat = 'true'
                environment.listLabels.removeAll { it.toLowerCase().startsWith('deployed: dev') }
                environment.listLabels.removeAll { it.toLowerCase().startsWith('deployed: tst') }
                environment.listLabels.removeAll { it.toLowerCase().startsWith('deployed: uat') }
                countname += 1
            break
            case 'Environment: Upgrade':
                environment.name = 'upg'
                environment.listLabels.removeAll { it.toLowerCase().startsWith('deployed: upg') }
                countname += 1
            break
        }
        if(environment.deployType!='skip' && environment.deployType!='force'){
            switch(label){
                case 'CI: Skip':
                    environment.deployType = 'skip'
                break
                case 'CI: Test':
                    environment.deployType = 'test'
                break
                case 'CI: Retest':
                    environment.deployType = 'test'
                break
                case 'CI: Force':
                    environment.deployType = 'force'
                break
                default:
                    environment.deployType = 'skip'
                break
            }
            environment.listLabels.removeAll { it.toLowerCase().startsWith('environment:') }
        }
        environment.listLabels.add(label)
    }
    //Check target branch
    environment.targetBranch = getTargetEnvironment CHANGE_TARGET

    environment.listLabels.removeAll { it.toLowerCase().startsWith('ci:') }

    echo "yardi environment ==> ${environment}"

    return environment
}
