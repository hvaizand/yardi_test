def call (String pathName) { 
    if (pathName == null) {
        return null;
    }  
    pathName = pathName.replaceAll("/", "\\\\")
  return pathName;
 }