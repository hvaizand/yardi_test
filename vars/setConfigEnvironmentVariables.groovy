def call(LinkedHashMap config=null, file) {
  if (config == null) {
    try {
      config = readYaml file: "${file}"
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