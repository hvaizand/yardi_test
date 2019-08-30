import groovy.transform.Field

@Field def STEP_NAME = 'doBuild'
@Field Set STEP_CONFIG_KEYS = [
    'fileType',
    'module'
]
@Field Set PARAMETER_KEYS = STEP_CONFIG_KEYS

def call(Map parameters = [:]) {
    handlePipelineStepErrors(stepName: STEP_NAME, stepParameters: parameters) {
        if(parameters.count!=0) {
            def fileList = [:]
            def fileCount = 0
            fileList = checkFiles (fileType: parameters.fileType, environmentType: parameters.module)
            fileCount = fileList.size()

            debugMessage "doBuild - List ${parameters.fileType}", "${fileList}"
            debugMessage "doBuild - Count ${parameters.fileType}", "${fileCount}"
    
            return fileCount
        }
    }
}
