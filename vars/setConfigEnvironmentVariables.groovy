def call(LinkedHashMap config=null) {
  if (config.file != null) {
    try {
      config = readYaml file: config.file
      echo "config ==> ${config}"
    }
    catch (e) {
      echo "Environment variables skipped. Configuration not found."
      return
    }
  }
  for (var in config.environment) {
    env[var.key] = var.value
  }
  return config
}