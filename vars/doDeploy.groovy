import groovy.transform.Field

@Field def STEP_NAME = 'doDeploy'
@Field Set STEP_CONFIG_KEYS = [
    'environmentType',
    'environment',
    'driveLetter',
    'defPath',
    'filePackage',
    'countPackages',
    'fileReport',
    'countReports',
    'label'
]
@Field Set PARAMETER_KEYS = STEP_CONFIG_KEYS

def call(Map parameters = [:]){
    handlePipelineStepErrors(stepName: STEP_NAME, stepParameters: parameters) {
        if(parameters.count!=0) {
            echo 'Deploying....'
            script{
                //Set configuration for ${parameters.environment}
                def yardiConfig = setConfigEnvironmentVariables file: "./Jenkinsfiles/yardi_${parameters.environmentType}_${parameters.environment}.yml"
                debugMessage "doDeploy - yardiConfig", "${yardiConfig}"
                //Load packages
                loadPackage(dbo_credentials: yardiConfig.dbo_credentials, db_server: yardiConfig.db_server, db_name: yardiConfig.db_name)
                //Copy report files
                copyFiles (fileNames: parameters.fileReport, targetDrive: parameters.driveLetter, targetFolder: yardiConfig.def_path, count: parameters.countReports)
                //Copy filter files to webshare
                parameters.label.add('Deployed: ' + yardiConfig.shortname) 
            }
            debugMessage "doDeploy - Deploy changes", parameters.message
        }
    }
}