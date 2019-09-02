import groovy.transform.Field

@Field def STEP_NAME = 'doTest'
@Field Set STEP_CONFIG_KEYS = [
    'countPackage',
    'countReport',
    'countOther',
    'message'
]
@Field Set PARAMETER_KEYS = STEP_CONFIG_KEYS

def call(Map parameters = [:]) {
    handlePipelineStepErrors(stepName: STEP_NAME, stepParameters: parameters) {
        if(parameters.count!=0) {
            def testResults = true
            //Test 1: Check if at least 1 package or 1 report is part of the changes
            if (parameters.countPackage==0 && parameters.countReport==0){
                    debugMessage "Test 1 - test results", parameters.countPackage==0 && parameters.countReport==0
                    parameters.message = "Deployment could not complete as there are no packages nor reports to deploy..."
                    testResults = false
            }
            //Test 2: TODO
            if (testResults){
                return 'SUCCESS'
            }
            else {
                return 'UNSTABLE'
            }
        }
    }
}
