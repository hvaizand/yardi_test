def call(LinkedHashMap config=null) {
  if (config == null) {
    try {
      config = readYaml file: 'yardi_environments.yml'
    }
    catch (e) {
      echo "Environment variables skipped. Configuration not found."
      return
    }
  }
  else if (fileExists("./Jenkinsfiles/yardi_environments.yml")) {
      config = readYaml file: "./Jenkinsfiles/yardi_environments.yml"
      echo "config ==> ${config}"
  }
  else {
      config = []
  }
  return config
}