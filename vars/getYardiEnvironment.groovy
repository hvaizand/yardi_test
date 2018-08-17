def call(){
    //Check labels
    Map environment
    environment.module = 'unknown'
    environment.name = 'unknown'
    def countmodule = 0
    def countname = 0
    for (label in pullRequest.labels) {
        switch(label) {
            case 'Core':
                environment.module = 'core'
                countmodule += 1
            break
            case 'ABF':
                environment.module = 'abf'
                countmodule += 1
            break
            case 'Sandbox':
                environment.name = 'sandbox'
                countname += 1
            break
            case 'Development':
                environment.name = 'dev'
                countname += 1
            break
            case 'Beta':
                environment.name = 'beta'
                countname += 1
            break
            case 'Test':
                environment.name = 'test'
                countname += 1
            break
            case '2k16':
                environment.name = '2k16'
                countname += 1
            break
            case 'UAT':
                environment.name = 'uat'
                countname += 1
            break
        }
    }
    if(countmodule>1) {
        environment.module = 'multiple'
    }
    if(countname>1) {
        environment.name = 'multiple'
    }
    //Check target branch


    return environment
}
