@echo off

echo Running tests...
call gradlew.bat test
if errorlevel 1 exit /b 1

echo Building application...
call gradlew.bat build
if errorlevel 1 exit /b 1

echo CI PASSED