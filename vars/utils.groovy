


def getYardiEnvironment() {
//    utils = load 'Jenkinsfiles/utils.groovy'
    if (fileExists("./Jenkinsfiles/yardi_environments.yml")) {
        config = readYaml file: "./Jenkinsfiles/yardi_environments.yml"
        echo "config ==> ${config}"
    }
    else {
        config = []
    }
}

def get_target_name() {
    if (env.BRANCH_NAME == PROD_BRANCH_NAME) {
        return 'prod'
    }
    if (env.BRANCH_NAME == STAGE_BRANCH_NAME) {
        return 'stage'
    }
    if (env.BRANCH_NAME == STANDBY_BRANCH_NAME) {
        return 'standby'
    }
    throw new Exception(
        'Unable to determine the target name from the branch name.'
    )
}

def get_target_script() {
    if (env.BRANCH_NAME == PROD_BRANCH_NAME) {
        return 'prod'
    }
    if (env.BRANCH_NAME == STAGE_BRANCH_NAME) {
        return 'stage'
    }
    if (env.BRANCH_NAME == STANDBY_BRANCH_NAME) {
        return 'prod.mm'
    }
    throw new Exception(
        'Unable to determine the target script from the branch name.'
    )
}