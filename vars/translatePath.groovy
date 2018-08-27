 //private static Pattern regexDriveLetter = Pattern.compile("^[a-zA-Z]:");
 //private static Pattern regexUnc = Pattern.compile("^//");
 //private static Pattern regexSlashes = Pattern.compile("/")  

def call (String pathName) { 
    if (pathName == null) {
        return null;
    }  
//  pathName = regexDriveLetter.matcher(pathName).replaceAll("");
    pathName = pathName.replaceAll("/", "\\\\")
//  pathName = regexUnc.matcher(pathName).replaceAll("/mnt/");
  return pathName;
 }