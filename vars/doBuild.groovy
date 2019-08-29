import groovy.transform.Field

@Field def STEP_NAME = 'doBuild'
@Field Set STEP_CONFIG_KEYS = [
    'fileType',
    'module',
    'fileCount'
]
@Field Set PARAMETER_KEYS = STEP_CONFIG_KEYS

def call(Map parameters = [:]) {
    handlePipelineStepErrors(stepName: STEP_NAME, stepParameters: parameters) {
        if(parameters.count!=0) {
            def fileList = [:]
            fileList = checkFiles (fileType: parameters.fileType, environmentType: parameters.module)
            parameters.fileCount = fileList.size()

            debugMessage "doBuild - List ${parameters.fileType}", "${fileList}"
            debugMessage "doBuild - Count ${parameters.fileType}", "${parameters.fileCount}"

            return fileList
        }
    }
}
