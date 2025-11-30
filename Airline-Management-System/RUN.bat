@echo off
REM Airline Management System - Launcher
REM This script compiles and runs the application

echo ========================================
echo Airline Management System Launcher
echo ========================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java JDK and try again
    pause
    exit /b 1
)

echo Compiling project...
javac -cp lib/* -d build/classes src/airlinemanagementsystem/*.java
if errorlevel 1 (
    echo ERROR: Compilation failed
    pause
    exit /b 1
)

echo.
echo Launching Airline Management System...
echo.
java -cp build/classes;lib/* airlinemanagementsystem.Login

pause
