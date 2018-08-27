 //private static Pattern regexDriveLetter = Pattern.compile("^[a-zA-Z]:");
 //private static Pattern regexUnc = Pattern.compile("^//");
 
def call (String pathname) {
    Pattern regexSlashes = Pattern.compile("/");
   
    if (pathname == null) {
        return null;
    }
  
//  pathname = regexDriveLetter.matcher(pathname).replaceAll("");
    pathname = regexSlashes.matcher(pathname).replaceAll("\\\\");
//  pathname = regexUnc.matcher(pathname).replaceAll("/mnt/");
  return pathname;
 }