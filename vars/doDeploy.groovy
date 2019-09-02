import groovy.transform.Field

@Field def STEP_NAME = 'doDeploy'
@Field Set STEP_CONFIG_KEYS = [
    'environmentType',
    'environment',
    'driveLetter',
    'countPackages',
    'countReports',
    'countOthers',
    'message',
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
                if(parameters.countPackages!=0){
                    loadPackage(dbo_credentials: yardiConfig.dbo_credentials, db_server: yardiConfig.db_server, db_name: yardiConfig.db_name)
                }
                //Copy report files
                if(parameters.countReports!=0){
                    copyFiles (fileType: "Report", targetDrive: parameters.driveLetter, targetFolder: yardiConfig.def_path)
                }
                //Copy files to webshare - TODO
                // if(parameters.countOthers!=0){
                    // copyFiles (fileType: "Other", targetDrive: parameters.driveLetter, targetFolder: yardiConfig.def_path)
                // }
                parameters.label.add('Deployed: ' + yardiConfig.shortname) 
            }
            debugMessage "doDeploy - Deploy changes", parameters.message
        }
    }
}