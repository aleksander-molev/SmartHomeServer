@ECHO OFF
@rem ##########################################################################
@rem
@rem  Gradle startup script for Windows
@rem
@rem ##########################################################################

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem # Set local scope for the variables with windows NT shell
@rem ##########################################################################
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
 echo location of your Java installation.
goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME%"
if exist %JAVA_HOME%\bin\java.exe set JAVA_EXE=%JAVA_HOME%\bin\java.exe
if exist %JAVA_HOME%\jre\bin\java.exe set JAVA_EXE=%JAVA_HOME%\jre\bin\java.exe

if "%JAVA_EXE%" == "" goto fail

@rem Increase the maximum file descriptors if we can.
if not "%OS%" == "Windows_NT" goto findAppBaseDir
if "%PROCESSOR_ARCHITECTURE%" == "x86" goto win9xME_args
if not "%PROCESSOR_ARCHITEW6432%" == "" goto win9xME_args

:win9xME_args
set _SKIP=2
if "%OS%" == "Windows_NT" goto win9xME_args_slash2
:win9xME_args_slash2
set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar

:findAppBaseDir
set APP_BASE_NAME=%APP_BASE_NAME%
set APP_HOME=%APP_HOME%

set CMD_LINE_ARGS=
set _CP=%CLASSPATH%

:setupArgs
if """"=="%1"""" goto execute
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto setupArgs

:execute
@rem Setup the command line
set JAVA_CMD=%JAVA_EXE% %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% "-Dorg.gradle.appname=%APP_BASE_NAME%" -classpath %_CP% org.gradle.wrapper.GradleWrapperMain %CMD_LINE_ARGS%

%JAVA_CMD%
if "%ERRORLEVEL%" == "0" goto mainEnd

:fail
rem Set variable GRADLE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%GRADLE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
