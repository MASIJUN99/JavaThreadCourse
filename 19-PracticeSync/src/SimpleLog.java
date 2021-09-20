//          __   ,:`-_    /\   /\            ,d@@b,
//      ,-'W;`./WI;:.`-./W;.\/;.\_          @@@@@@@
//    ,'WWI;::.\ W;:' /WWWI;.`--'.\         `?@@P'
//   /WWI;;;:. .`.W' /WWWII;:..:;:.`-.__
// <=    Happy Mid-Autumn Festival    `=>--------------------------
//   \  ~     -  '   \  ~ _      ~  -'      _~-~~   _   ~        ~
//    `    ~   /  ~   \       ~-  /   ~     ~-_~-~        -  ~      ~
//  ~   `-.   '\     .- \ ~ /\  /~          -~_ ~-
//         ~~   `.,-~    \/   \/    -    ~  ~

import java.text.SimpleDateFormat;

class SimpleLog {
    static public void complexLog(String text, Object... vars) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[stackTrace.length - 1];
        text = "(" + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ") " + text;
        text = "[" + Thread.currentThread().getName() + "] " + text;
        text = new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + ' ' + text;

        if (vars.length > 0) {
            int i = 0;
            for (Object var : vars) {
                for (int length = text.length(); i < length - 1; i++) {
                    if (text.charAt(i) == '{' && text.charAt(i + 1) == '}') {
                        text = text.substring(0, i) + var + text.substring(i + 2, length);
                    }
                }
            }
        }
        System.out.println(text);
    }

    static public void simpleLog(String text, Object... vars) {
        text = text.replace("%date", new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
        text = text.replace("%t", Thread.currentThread().getName());
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[stackTrace.length - 1];
        text = text.replace("%logger", stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName());

        if (vars.length > 0) {
            int i = 0;
            for (Object var : vars) {
                for (int length = text.length(); i < length - 1; i++) {
                    if (text.charAt(i) == '{' && text.charAt(i + 1) == '}') {
                        text = text.substring(0, i) + var + text.substring(i + 2, length);
                    }
                }
            }
        }
        System.out.println(text);
    }
}