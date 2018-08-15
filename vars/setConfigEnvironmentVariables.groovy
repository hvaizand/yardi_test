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
  for (var in config.environment) {
    echo "Key: ${var.key} - value: ${var.value}"
    env[var.key] = var.value
  }
}