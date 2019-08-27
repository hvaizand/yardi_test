import com.prologis.yardi.FileUtils

import groovy.transform.Field

@Field def STEP_NAME = 'getYardiEnvironment'
@Field Set STEP_CONFIG_KEYS = [
    'name',
    'module',
    'deployType',
    'targetBranch',
    'listLabels',
    'branchType',
    'deploydev',
    'deploytst',
    'deployuat',
    'deploysbx'
]
@Field Set PARAMETER_KEYS = STEP_CONFIG_KEYS

def call(Map parameters = [:]){
    handlePipelineStepErrors(stepName: STEP_NAME, stepParameters: parameters) {
        if(parameters.count!=0) {
            //Check labels
            //def environment = [name: 'unknown', module: 'unknown']
            def countname = 0
            def environmentList = []
            for (label in pullRequest.labels) {
                switch(label) {
                    case 'Environment: Sandbox':
                        parameters.name = 'sandbox'
                        parameters.deploysbx = 'true'
                        environmentList.add(parameters.name)
                        countname += 1
                    break
                    case 'Environment: Development':
                        parameters.name = 'dev'
                        parameters.deploydev = 'true'
                        environmentList.add(parameters.name)
                        countname += 1
                    break
                    case 'Environment: Beta':
                        parameters.name = 'beta'
                        parameters.listLabels.removeAll { it.toLowerCase().startsWith('deployed: beta') }
                        environmentList.add(parameters.name)
                        countname += 1
                    break
                    case 'Environment: Test':
                        parameters.name = 'dev-test'
                        parameters.deploydev = 'true'
                        parameters.deploytst = 'true'
                        environmentList.add(parameters.name)
                        countname += 1
                    break
                    case 'Environment: 2k16':
                        parameters.name = '2k16'
                        parameters.listLabels.removeAll { it.toLowerCase().startsWith('deployed: 2k16') }
                        environmentList.add(parameters.name)
                        countname += 1
                    break
                    case 'Environment: UAT':
                        parameters.name = 'dev-test-uat'
                        parameters.deploydev = 'true'
                        parameters.deploytst = 'true'
                        parameters.deployuat = 'true'
                        environmentList.add(parameters.name)
                        countname += 1
                    break
                    case 'Environment: Upgrade':
                        parameters.name = 'upg'
                        parameters.listLabels.removeAll { it.toLowerCase().startsWith('deployed: upg') }
                        environmentList.add(parameters.name)
                        countname += 1
                    break
                    //Set deployType to skip based on Status label.
                    case 'Status: Accepted':
                    case 'Status: Abandoned':
                    case 'Status: Completed':
                    case 'Status: On Hold':
                    case 'Status: Revision Needed':
                        parameters.deployType = 'skip'
                    break
                }
                if(parameters.deployType!='skip' && parameters.deployType!='force'){
                    switch(label){
                        case 'CI: Skip':
                            parameters.deployType = 'skip'
                        break
                        case 'CI: Test':
                            parameters.deployType = 'test'
                        break
                        case 'CI: Retest':
                            parameters.deployType = 'test'
                        break
                        case 'CI: Force':
                            parameters.deployType = 'force'
                        break
                    }
                }
                parameters.listLabels.add(label)
            }
            //Check target branch
            parameters.targetBranch = getTargetEnvironment CHANGE_TARGET

            //Remove labels only if not skip
            if(parameters.deployType!='skip'){
                if(parameters.deploysbx){
                    parameters.listLabels.removeAll { it.toLowerCase().startsWith('deployed: sbx') }
                }
                if(parameters.deploydev && parameters.deploytst && parameters.deployuat){
                        parameters.listLabels.removeAll { it.toLowerCase().startsWith('deployed: dev') }
                        parameters.listLabels.removeAll { it.toLowerCase().startsWith('deployed: tst') }
                        parameters.listLabels.removeAll { it.toLowerCase().startsWith('deployed: uat') }
                }
                parameters.listLabels.removeAll { it.toLowerCase().startsWith('ci:') }
                parameters.listLabels.removeAll { it.toLowerCase().startsWith('environment:') }
            }

            echo "yardi list environments ==> ${environmentList}"
            echo "yardi environment ==> ${parameters}"

        } 
    }
}